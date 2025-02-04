package org.app.transportpersoane.oop.p8.web.transportpersoane;



import org.app.tansportpersoane.web.views.clienti.FormClientView;
import org.app.tansportpersoane.web.views.clienti.NavigableGridClientiView;
import org.app.transportpersoane.web.views.trasport.FormTransportView;
import org.app.transportpersoane.web.views.trasport.NavigableGridTransporturiView;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;

@Route
public class MainView extends VerticalLayout implements RouterLayout {
public MainView() {
setMenuBar();
}
private void setMenuBar() {
MenuBar mainMenu = new MenuBar();
MenuItem homeMenu = mainMenu.addItem("Home");
homeMenu.addClickListener(event -> UI.getCurrent().navigate(MainView.class));
//
MenuItem gridFormsClientiMenu = mainMenu.addItem("Calator");
SubMenu gridFormsClientiMenuBar = gridFormsClientiMenu.getSubMenu();
gridFormsClientiMenuBar.addItem("Lista Calatori...",
event -> UI.getCurrent().navigate(NavigableGridClientiView.class));
gridFormsClientiMenuBar.addItem("Form Editare Calator...",
event -> UI.getCurrent().navigate(FormClientView.class));
//
//
MenuItem gridFormsTransporturiMenu = mainMenu.addItem("Transporturi ");
SubMenu gridFormsTransporturiMenuBar = gridFormsTransporturiMenu.getSubMenu();
gridFormsTransporturiMenuBar.addItem("Lista Transporturi...",
event -> UI.getCurrent().navigate(NavigableGridTransporturiView.class));
gridFormsTransporturiMenuBar.addItem("Form Editare Transporturi...",
event -> UI.getCurrent().navigate(FormTransportView.class));
 
add(new HorizontalLayout(mainMenu));
}
}