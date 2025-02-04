package org.app.transportpersoane.web.views.trasport;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.app.transportpersoane.oop.p8.web.transportpersoane.MainView;
import org.entity.Transport;

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


	@PageTitle("transport")
		@Route(value = "transport", layout = MainView.class)
	public class FormTransportView extends VerticalLayout implements HasUrlParameter<Integer>{

		
		// Definire model date
		private EntityManager em;
		private Transport transport = null;
		private Binder<Transport> binder = new BeanValidationBinder<>(Transport.class);
		// Definire componente view
		// Definire Form
		private VerticalLayout formLayoutToolbar;
		private H1 titluForm = new H1("Form Transport");
		private IntegerField id = new IntegerField("ID transport:");
		private TextField nume = new TextField("Nume transport: ");
		// Definire componente actiuni Form-Controller
		private Button cmdAdaugare = new Button("Adauga");
		private Button cmdSterge = new Button("Sterge");
		private Button cmdAbandon = new Button("Abandon");
		private Button cmdSalveaza = new Button("Salveaza");
			// … … //
			// Navigation Management:
			// URL-ul http://localhost:8080/clienti/3 asigură afișare detaliilor clientului cu ID 3
			@Override
			public void setParameter(BeforeEvent event, @OptionalParameter Integer idTransport) {
			System.out.println("Transport ID: " + idTransport);
			if (idTransport != null) {
			// EDIT Item
			this.transport = em.find(Transport.class, idTransport);
			System.out.println("Selected transport to edit:: " + transport);
			if (this.transport == null) {
			System.out.println("ADD transport:: " + transport);
			// NEW Item
			this.adaugaTransport();
			this.transport.setIdTransport(idTransport);
			this.transport.setNume("Transport NOU " + idTransport);
			}
			}
			this.refreshForm();
			}
			
			// init Data Model
			private void initDataModel(){
			System.out.println("DEBUG START FORM >>> ");
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("TransportPersoaneJPA");
			this.em = emf.createEntityManager();
			this.transport = em
			.createQuery("SELECT t FROM Transport t ORDER BY t.idTransport", Transport.class)
			.getResultStream().findFirst().get();
			//
			binder.forField(id).bind("idTransport");
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
			adaugaTransport();
			refreshForm();
			});
			cmdSterge.addClickListener(e -> {
			stergeTransport();
			// Navigate back to NavigableGridClienteForm
			this.getUI().ifPresent(ui -> ui.navigate(
			NavigableGridTransporturiView.class)
			);
			});
			cmdAbandon.addClickListener(e -> {
			// Navigate back to NavigableGridClienteForm
			this.getUI().ifPresent(ui -> ui.navigate(
			NavigableGridTransporturiView.class, this.transport.getIdTransport())
			);
			});
			cmdSalveaza.addClickListener(e -> {
			salveazaTransport();
			// refreshForm();
			// Navigate back to NavigableGridClienteForm
			this.getUI().ifPresent(ui -> ui.navigate(
			NavigableGridTransporturiView.class, this.transport.getIdTransport())
			);
			});
			}
			private void refreshForm() {
				System.out.println("Transport curent: " + this.transport);
				if (this.transport != null) {
				binder.setBean(this.transport);
				}
				}
			// CRUD actions
			private void salveazaTransport() {
			try {
			this.em.getTransaction().begin();
			this.transport = this.em.merge(this.transport);
			this.em.getTransaction().commit();
			System.out.println("Transport Salvat");
			} catch (Exception ex) {
			if (this.em.getTransaction().isActive())
			this.em.getTransaction().rollback();
			System.out.println("*** EntityManager Validation ex: " + ex.getMessage());
			throw new RuntimeException(ex.getMessage());
			}
			}
			// CRUD actions
			private void adaugaTransport() {
			this.transport = new Transport();
			this.transport.setIdTransport(999);  // ID arbitrar, inexistent în baza de date
			this.transport.setNume("Transport Nou");
			}
			// CRUD actions
			private void stergeTransport() {
			System.out.println("To remove: " + this.transport);
			if (this.em.contains(this.transport)) {
			this.em.getTransaction().begin();
			this.em.remove(this.transport);
			this.em.getTransaction().commit();
			}
			}
			// Start Form
			public FormTransportView() {
			//
			initDataModel();
			//
			initViewLayout();
			//
			initControllerActions();
			}
		}
		



