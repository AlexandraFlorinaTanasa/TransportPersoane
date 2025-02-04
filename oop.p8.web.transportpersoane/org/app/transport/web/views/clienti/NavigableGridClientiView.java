package org.app.tansportpersoane.web.views.clienti;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.app.transportpersoane.oop.p8.web.transportpersoane.MainView;
import org.entity.Calatori;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;



@PageTitle("calatori")
@Route(value = "calatori", layout = MainView.class)

public class NavigableGridClientiView extends VerticalLayout implements HasUrlParameter<Integer>{
	
	
	// Definire model date
	private EntityManager em;
	private List<Calatori> calatori = new ArrayList<>();
	private Calatori calator = null;
	private Binder<Calatori> binder = new BeanValidationBinder<>(Calatori.class);
	
	// Definire componente view
	private H1 titluForm = new H1("Lista Calatori");
	
	// Definire componente suport navigare
	private VerticalLayout gridLayoutToolbar;
	private TextField filterText = new TextField();
	private Button cmdEditCalator = new Button("Editeaza calator...");
	private Button cmdAdaugaCalator = new Button("Adauga calator...");
	private Button cmdStergeCalator = new Button("Sterge calator...");
	private Grid<Calatori> grid = new Grid<>(Calatori.class);
	
	// init Data Model
	private void initDataModel(){
	System.out.println("DEBUG START FORM >>> ");
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("TransportPersoaneJPA");
	em = emf.createEntityManager();
	List<Calatori> lst = em
	.createQuery("SELECT c FROM Calatori c ORDER BY c.id", Calatori.class)
	.getResultList();
	calatori.addAll(lst);
	if (lst != null && !lst.isEmpty()){
	Collections.sort(this.calatori, (c1, c2) ->  c1.getId().compareTo(c2.getId()));
	this.calator = calatori.get(0);
	System.out.println("DEBUG: calator init >>> " + calator.getId());
	}
	//
	grid.setItems(this.calatori);
	binder.setBean(this.calator);
	grid.asSingleSelect().setValue(this.calator);
	}
	// init View Model
	private void initViewLayout() {
	// Layout navigare -------------------------------------//
	// Toolbar navigare
	filterText.setPlaceholder("Filter by nume...");
	filterText.setClearButtonVisible(true);
	filterText.setValueChangeMode(ValueChangeMode.LAZY);
	HorizontalLayout gridToolbar = new HorizontalLayout(filterText,
	cmdEditCalator, cmdAdaugaCalator, cmdStergeCalator);
	// Grid navigare
	grid.setColumns("id", "nume");
	grid.addComponentColumn(item -> createGridActionsButtons(item)).setHeader("Actiuni");
	// Init Layout navigare
	gridLayoutToolbar = new VerticalLayout(gridToolbar, grid);
	// ---------------------------
	this.add(titluForm, gridLayoutToolbar);
	//
	}
	private Component createGridActionsButtons(Calatori item) {
		//
		Button cmdEditItem = new Button("Edit");
		cmdEditItem.addClickListener(e -> {
		grid.asSingleSelect().setValue(item);
		editCalator();
		});
		Button cmdDeleteItem = new Button("Sterge");
		cmdDeleteItem.addClickListener(e -> {
		System.out.println("Sterge item: " + item);
		grid.asSingleSelect().setValue(item);
		stergeCalator();
		refreshForm();
		} );
		//
		return new HorizontalLayout(cmdEditItem, cmdDeleteItem);
		}
	
	// init Controller components
	private void initControllerActions() {
	// Navigation Actions
	filterText.addValueChangeListener(e -> updateList());
	cmdEditCalator.addClickListener(e -> {
	editCalator();
	});
	cmdAdaugaCalator.addClickListener(e -> {
	adaugaCalator();
	});
	cmdStergeCalator.addClickListener(e -> {
	stergeCalator();
	refreshForm();
	});
	}
	// CRUD actions
	// Adaugare: delegare catre Formular detalii calator
	private void adaugaCalator() {
	this.getUI().ifPresent(ui -> ui.navigate(FormClientView.class, 999));
	}
	// Editare: delegare catre Formular detalii calator
	private void editCalator() {
	this.calator = this.grid.asSingleSelect().getValue();
	System.out.println("Selected calator:: " + calator);
	if (this.calator != null) {
	this.getUI().ifPresent(ui -> ui.navigate(
	FormClientView.class, this.calator.getId())
	);
	}
	}
	// CRUD actions
	// Stergere: tranzactie locala cu EntityManager
	private void stergeCalator() {
	this.calator = this.grid.asSingleSelect().getValue();
	System.out.println("To remove: " + this.calator);
	this.calatori.remove(this.calator);
	if (this.em.contains(this.calator)) {
	this.em.getTransaction().begin();
	this.em.remove(this.calator);
	this.em.getTransaction().commit();
	}
	if (!this.calatori.isEmpty())
	this.calator = this.calatori.get(0);
	else
	this.calator = null;
	}
	// Start Form
	public NavigableGridClientiView() {
	//
	initDataModel();
	//
	initViewLayout();
	//
	initControllerActions();
	}
	// Populare grid cu set de date din model - filtrare
	private void updateList() {
	try {
	List<Calatori> lstCalatoriFiltered = this.calatori;
	if (filterText.getValue() != null) {
	lstCalatoriFiltered = this.calatori.stream()
	.filter(c -> c.getNume().contains(filterText.getValue()))
	.toList();
	grid.setItems(lstCalatoriFiltered);
	}
	} catch (Exception e) {
	e.printStackTrace();
	}
	}
	// Resincronizare componente-view cu modelul de date
	private void refreshForm() {
	System.out.println("Calator curent: " + this.calator);
	if (this.calator != null) {
	grid.setItems(this.calatori);
	binder.setBean(this.calator);
	grid.select(this.calator);
	}
	}
	
	// … … //
	// Navigation Management:
	// URL-ul http://localhost:8080/clienti/3 asigură selecția clientului cu ID 3
	@Override
	public void setParameter(BeforeEvent event, @OptionalParameter Integer id) {
	if (id != null) {
	this.calator = em.find(Calatori.class, id);
	System.out.println("Back calator: " + calator);
	if (this.calator == null) {
	// DELETED Item
	if (!this.calatori.isEmpty())
	this.calator = this.calatori.get(0);
	}
	// else: EDITED or NEW Item
	}
	this.refreshForm();
	}
	// … … //
	}
 




