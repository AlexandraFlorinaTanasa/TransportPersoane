package org.app.tansportpersoane.web.views.clienti;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.app.transportpersoane.oop.p8.web.transportpersoane.MainView;
import org.entity.Calatori;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@PageTitle("calator")
	@Route(value = "calator", layout = MainView.class)
	public class FormClientView extends VerticalLayout implements HasUrlParameter<Integer>{

	
	// Definire model date
	private EntityManager em;
	private Calatori calator = null;
	private Binder<Calatori> binder = new BeanValidationBinder<>(Calatori.class);
	// Definire componente view
	// Definire Form
	private VerticalLayout formLayoutToolbar;
	private H1 titluForm = new H1("Form Calator");
	private IntegerField id = new IntegerField("ID calator:");
	private TextField nume = new TextField("Nume calator: ");
	// Definire componente actiuni Form-Controller
	private Button cmdAdaugare = new Button("Adauga");
	private Button cmdSterge = new Button("Sterge");
	private Button cmdAbandon = new Button("Abandon");
	private Button cmdSalveaza = new Button("Salveaza");
		// … … //
		// Navigation Management:
		// URL-ul http://localhost:8080/clienti/3 asigură afișare detaliilor clientului cu ID 3
		@Override
		public void setParameter(BeforeEvent event, @OptionalParameter Integer id) {
		System.out.println("Calator ID: " + id);
		if (id != null) {
		// EDIT Item
		this.calator = em.find(Calatori.class, id);
		System.out.println("Selected calator to edit:: " + calator);
		if (this.calator == null) {
		System.out.println("ADD calator:: " + calator);
		// NEW Item
		this.adaugaCalator();
		this.calator.setId(id);
		this.calator.setNume("Calator NOU " + id);
		}
		}
		this.refreshForm();
		}
		
		// init Data Model
		private void initDataModel(){
		System.out.println("DEBUG START FORM >>> ");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("TransportPersoaneJPA");
		this.em = emf.createEntityManager();
		this.calator = em
		.createQuery("SELECT c FROM Calatori c ORDER BY c.id", Calatori.class)
		.getResultStream().findFirst().get();
		//
		binder.forField(id).bind("id");
		binder.forField(nume).bind("nume");
		//
		refreshForm();
		}
		// init View Model
		private void initViewLayout() {
		// Form-Master-Details -----------------------------------//
		// Form-Master
		FormLayout formLayout = new FormLayout();
		formLayout.add(id, nume);
		formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1));
		formLayout.setMaxWidth("400px");
		// Toolbar-Actions-Master
		HorizontalLayout actionToolbar =
		new HorizontalLayout(cmdAdaugare, cmdSterge, cmdAbandon, cmdSalveaza);
		actionToolbar.setPadding(false);
		//
		this.formLayoutToolbar = new VerticalLayout(formLayout, actionToolbar);
		// ---------------------------
		this.add(titluForm, formLayoutToolbar);
		//
		}
		// init Controller components
		private void initControllerActions() {
		// Transactional Master Actions
		cmdAdaugare.addClickListener(e -> {
		adaugaCalator();
		refreshForm();
		});
		cmdSterge.addClickListener(e -> {
		stergeCalator();
		// Navigate back to NavigableGridClienteForm
		this.getUI().ifPresent(ui -> ui.navigate(
		NavigableGridClientiView.class)
		);
		});
		cmdAbandon.addClickListener(e -> {
		// Navigate back to NavigableGridClienteForm
		this.getUI().ifPresent(ui -> ui.navigate(
		NavigableGridClientiView.class, this.calator.getId())
		);
		});
		cmdSalveaza.addClickListener(e -> {
		salveazaClient();
		// refreshForm();
		// Navigate back to NavigableGridClienteForm
		this.getUI().ifPresent(ui -> ui.navigate(
		NavigableGridClientiView.class, this.calator.getId())
		);
		});
		}
		private void refreshForm() {
			System.out.println("Calator curent: " + this.calator);
			if (this.calator != null) {
			binder.setBean(this.calator);
			}
			}
		// CRUD actions
		private void salveazaClient() {
		try {
		this.em.getTransaction().begin();
		this.calator = this.em.merge(this.calator);
		this.em.getTransaction().commit();
		System.out.println("Calator Salvat");
		} catch (Exception ex) {
		if (this.em.getTransaction().isActive())
		this.em.getTransaction().rollback();
		System.out.println("*** EntityManager Validation ex: " + ex.getMessage());
		throw new RuntimeException(ex.getMessage());
		}
		}
		// CRUD actions
		private void adaugaCalator() {
		this.calator = new Calatori();
		this.calator.setId(999);  // ID arbitrar, inexistent în baza de date
		this.calator.setNume("Calator Nou");
		}
		// CRUD actions
		private void stergeCalator() {
		System.out.println("To remove: " + this.calator);
		if (this.em.contains(this.calator)) {
		this.em.getTransaction().begin();
		this.em.remove(this.calator);
		this.em.getTransaction().commit();
		}
		}
		// Start Form
		public FormClientView() {
		//
		initDataModel();
		//
		initViewLayout();
		//
		initControllerActions();
		}
	}
	





