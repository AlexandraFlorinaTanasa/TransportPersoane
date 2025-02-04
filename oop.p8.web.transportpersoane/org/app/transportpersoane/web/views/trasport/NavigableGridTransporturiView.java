package org.app.transportpersoane.web.views.trasport;
import java.util.ArrayList;
	import java.util.Collections;
	import java.util.List;

	import javax.persistence.EntityManager;
	import javax.persistence.EntityManagerFactory;
	import javax.persistence.Persistence;

import org.app.transportpersoane.oop.p8.web.transportpersoane.MainView;
import org.entity.Transport;

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

	

		@PageTitle("transporturi")
		@Route(value = "transporturi", layout = MainView.class)

		public class NavigableGridTransporturiView  extends VerticalLayout implements HasUrlParameter<Integer>{
			
			
			// Definire model date
			private EntityManager em;
			private List<Transport> transporturi = new ArrayList<>();
			private Transport transport = null;
			private Binder<Transport> binder = new BeanValidationBinder<>(Transport.class);
			
			// Definire componente view
			private H1 titluForm = new H1("Lista Transporturi");
			
			// Definire componente suport navigare
			private VerticalLayout gridLayoutToolbar;
			private TextField filterText = new TextField();
			private Button cmdEditTransport = new Button("Editeaza transport...");
			private Button cmdAdaugaTransport = new Button("Adauga transport...");
			private Button cmdStergeTransport = new Button("Sterge transport...");
			private Grid<Transport> grid = new Grid<>(Transport.class);
			
			// init Data Model
			private void initDataModel(){
			System.out.println("DEBUG START FORM >>> ");
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("TransportPersoaneJPA");
			em = emf.createEntityManager();
			List<Transport> lst = em
			.createQuery("SELECT t FROM Transport t ORDER BY t.idTransport", Transport.class)
			.getResultList();
			transporturi.addAll(lst);
			if (lst != null && !lst.isEmpty()){
			Collections.sort(this.transporturi, (t1, t2) ->  t1.getIdTransport().compareTo(t2.getIdTransport()));
			this.transport = transporturi.get(0);
			System.out.println("DEBUG: transport init >>> " + transport.getIdTransport());
			}
			//
			grid.setItems(this.transporturi);
			binder.setBean(this.transport);
			grid.asSingleSelect().setValue(this.transport);
			}
			// init View Model
			private void initViewLayout() {
			// Layout navigare -------------------------------------//
			// Toolbar navigare
			filterText.setPlaceholder("Filter by nume...");
			filterText.setClearButtonVisible(true);
			filterText.setValueChangeMode(ValueChangeMode.LAZY);
			HorizontalLayout gridToolbar = new HorizontalLayout(filterText,
			cmdEditTransport, cmdAdaugaTransport, cmdStergeTransport);
			// Grid navigare
			grid.setColumns("idTransport", "nume");
			grid.addComponentColumn(item -> createGridActionsButtons(item)).setHeader("Actiuni");
			// Init Layout navigare
			gridLayoutToolbar = new VerticalLayout(gridToolbar, grid);
			// ---------------------------
			this.add(titluForm, gridLayoutToolbar);
			//
			}
			private Component createGridActionsButtons(Transport item) {
				//
				Button cmdEditItem = new Button("Edit");
				cmdEditItem.addClickListener(e -> {
				grid.asSingleSelect().setValue(item);
				editTransport();
				});
				Button cmdDeleteItem = new Button("Sterge");
				cmdDeleteItem.addClickListener(e -> {
				System.out.println("Sterge item: " + item);
				grid.asSingleSelect().setValue(item);
				stergeTransport();
				refreshForm();
				} );
				//
				return new HorizontalLayout(cmdEditItem, cmdDeleteItem);
				}
			
			// init Controller components
			private void initControllerActions() {
			// Navigation Actions
			filterText.addValueChangeListener(e -> updateList());
			cmdEditTransport.addClickListener(e -> {
			editTransport();
			});
			cmdAdaugaTransport.addClickListener(e -> {
			adaugaTransport();
			});
			cmdStergeTransport.addClickListener(e -> {
			stergeTransport();
			refreshForm();
			});
			}
			// CRUD actions
			// Adaugare: delegare catre Formular detalii transporturi
			private void adaugaTransport() {
			this.getUI().ifPresent(ui -> ui.navigate(FormTransportView.class, 999));
			}
			// Editare: delegare catre Formular detalii transporturi
			private void editTransport() {
			this.transport = this.grid.asSingleSelect().getValue();
			System.out.println("Selected transport:: " + transport);
			if (this.transport != null) {
			this.getUI().ifPresent(ui -> ui.navigate(
			FormTransportView.class, this.transport.getIdTransport())
			);
			}
			}
			// CRUD actions
			// Stergere: tranzactie locala cu EntityManager
			private void stergeTransport() {
			this.transport = this.grid.asSingleSelect().getValue();
			System.out.println("To remove: " + this.transport);
			this.transporturi.remove(this.transport);
			if (this.em.contains(this.transport)) {
			this.em.getTransaction().begin();
			this.em.remove(this.transport);
			this.em.getTransaction().commit();
			}
			if (!this.transporturi.isEmpty())
			this.transport = this.transporturi.get(0);
			else
			this.transport = null;
			}
			// Start Form
			public NavigableGridTransporturiView() {
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
			List<Transport> lstTransporturiFiltered = this.transporturi;
			if (filterText.getValue() != null) {
			lstTransporturiFiltered = this.transporturi.stream()
			.filter(t -> t.getNume().contains(filterText.getValue()))
			.toList();
			grid.setItems(lstTransporturiFiltered);
			}
			} catch (Exception e) {
			e.printStackTrace();
			}
			}
			// Resincronizare componente-view cu modelul de date
			private void refreshForm() {
			System.out.println("Transport curent: " + this.transport);
			if (this.transport != null) {
			grid.setItems(this.transporturi);
			binder.setBean(this.transport);
			grid.select(this.transport);
			}
			}
			
			// … … //
			// Navigation Management:
			// URL-ul http://localhost:8080/clienti/3 asigură selecția clientului cu ID 3
			@Override
			public void setParameter(BeforeEvent event, @OptionalParameter Integer idTransport) {
			if (idTransport != null) {
			this.transport = em.find(Transport.class, idTransport);
			System.out.println("Back transport: " + transport);
			if (this.transport == null) {
			// DELETED Item
			if (!this.transporturi.isEmpty())
			this.transport = this.transporturi.get(0);
			}
			// else: EDITED or NEW Item
			}
			this.refreshForm();
			}
			// … … //
			}
		 



