/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
/*
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
*/
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;


/**
 *
 * @author Sergio
 */
public class tiendaFXMLDocumentController{
    private static final Logger LOGGER = Logger.getLogger("-----------------------------------------ControladorTabla");
    private Stage stage;
    private Parent root;
    @FXML private AnchorPane panel;
    @FXML private TableView<PersonaBeans> tabla;
    @FXML private TableColumn columna1;
    @FXML private TableColumn columna2;
    @FXML private TableColumn columna3;
    ObservableList<PersonaBeans> personas;
    @FXML TextField txtNombre;
    @FXML TextField txtApellido;
    @FXML TextField txtEdad;
    @FXML TextField txtBuscar;
    @FXML Button btnBorrar;
    @FXML Button btnAñadir;
    @FXML Button btnRemplazar;
    @FXML Button btnAceptar;
    @FXML Button btnImprimir;
    @FXML Button btnBuscar;
    private final ContextMenu popupMenu = new ContextMenu();; //llamado tmb popup-menu, es decir lo declaramos
    private final MenuItem menu1 = new MenuItem("Añadir");
    private final Menu menu2 = new Menu("Borrar Fila");
    private final MenuItem submenu1 = new MenuItem("Fila seleccionada");
    private final MenuItem submenu2 = new MenuItem("todas las filas");
    private final MenuItem menu3 = new MenuItem("Modificar");
    
    
    public void initStage(Parent root){
        LOGGER.info("---------------------------------------");
        this.root = root;
        stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Tabla");
        
        //tabla y definicion de sus componentes(columnas)
        columna1.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columna2.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        columna3.setCellValueFactory(new PropertyValueFactory<>("edad"));
        personas = FXCollections.observableArrayList();
        tabla.setItems(personas);
        
        //popup-menu items definiendolas y añadiendolas al popup
        popupMenu.getItems().add(menu1);
        popupMenu.getItems().add(menu2);
        menu2.getItems().add(submenu1);
        menu2.getItems().add(submenu2);
        popupMenu.getItems().add(new SeparatorMenuItem());//añadimos linea separadora entre los items
        popupMenu.getItems().add(menu3);
        
        //añadimos controladores a los distintos componentes de la ventana para poder abrir y cerrar el popup-menu
        menu1.addEventHandler(ActionEvent.ACTION,this::accionDeMenus);
        submenu1.addEventHandler(ActionEvent.ACTION,this::accionDeMenus);
        submenu2.addEventHandler(ActionEvent.ACTION,this::accionDeMenus);
        menu3.addEventHandler(ActionEvent.ACTION,this::accionDeMenus);
        tabla.addEventHandler(MouseEvent.MOUSE_CLICKED, this::clicks);
        panel.addEventHandler(MouseEvent.MOUSE_CLICKED, this::clicks);
        btnBorrar.addEventHandler(MouseEvent.MOUSE_CLICKED, this::clicks);
        btnAñadir.addEventHandler(MouseEvent.MOUSE_CLICKED, this::clicks);
        btnRemplazar.addEventHandler(MouseEvent.MOUSE_CLICKED, this::clicks);
        btnAceptar.addEventHandler(MouseEvent.MOUSE_CLICKED, this::clicks);
        btnImprimir.addEventHandler(MouseEvent.MOUSE_CLICKED, this::clicks);
        txtNombre.addEventHandler(MouseEvent.MOUSE_CLICKED, this::clicks);
        txtApellido.addEventHandler(MouseEvent.MOUSE_CLICKED, this::clicks);
        txtEdad.addEventHandler(MouseEvent.MOUSE_CLICKED, this::clicks);
        txtBuscar.addEventFilter(MouseEvent.MOUSE_CLICKED, this::clicks);
        btnBuscar.addEventFilter(MouseEvent.MOUSE_CLICKED, this::clicks);
        txtNombre.addEventHandler(MouseEvent.MOUSE_CLICKED, this::deshabilitarClickDerecho);
        txtApellido.addEventHandler(MouseEvent.MOUSE_CLICKED, this::deshabilitarClickDerecho);
        txtEdad.addEventHandler(MouseEvent.MOUSE_CLICKED, this::deshabilitarClickDerecho);
        stage.setOnShowing(this::HandleWindowShowing);
        
        stage.initStyle (StageStyle.TRANSPARENT);
        stage.show();
        stage.hide();

    }         
    private void HandleWindowShowing(WindowEvent e){
        submenu1.setDisable(true);
        menu3.setDisable(true);
        btnAceptar.setVisible(false);
    }
    private void deshabilitarClickDerecho(MouseEvent e){
        if (e.getButton() == MouseButton.SECONDARY){
            popupMenu.hide();
        }        
    }
    private void clicks(MouseEvent e){
        if (e.getButton() == MouseButton.SECONDARY){
            if(tabla.getSelectionModel().getSelectedIndex()>=0 && tabla.getSelectionModel().getSelectedIndex()<personas.size()){
                submenu1.setDisable(false);
                menu3.setDisable(false);
             }
            else
                menu3.setDisable(true);
            popupMenu.show(root, e.getScreenX(),e.getScreenY());
            
        }      
        else if(e.getButton() == MouseButton.PRIMARY){
             popupMenu.hide();
             if(!btnRemplazar.isFocused()){
                 if(!(txtNombre.isFocused() || txtApellido.isFocused()|| txtEdad.isFocused())){
                    txtNombre.setText("");
                    txtApellido.setText("");
                    txtEdad.setText("");
                    btnAceptar.setVisible(false);
                 }
                 
             }
        } 
           
    }
    
    public void accionDeMenus(ActionEvent e){
        if(e.getSource().equals(menu1))
             añadirFila();
        if(e.getSource().equals(submenu1))
             borrarFila();
        if(e.getSource().equals(submenu2))
             borrarTodasLasFilas();
        if(e.getSource().equals(menu3))
            remplazarDatosFila();
    }
    @FXML
    private void borrarFila(){
        LOGGER.info("he clicado borrar");
        tabla.getItems().remove(tabla.getSelectionModel().getSelectedItem());
        tabla.refresh();
        submenu1.setDisable(true);
    }
    @FXML
    private void añadirFila(){
        String nombre = txtNombre.getText() ;
        String apellido = txtApellido.getText();
        int edad;

        if(txtNombre.getText().isEmpty() || txtApellido.getText().isEmpty()
                || txtEdad.getText().isEmpty())
            LOGGER.info("Vaya parece q no has rellenado todos los datos");
        else{
            edad = Integer.parseInt(txtEdad.getText());
            PersonaBeans persona = new PersonaBeans(nombre,apellido,edad);
            personas.add(persona);
            txtNombre.setText("");
            txtApellido.setText("");
            txtEdad.setText("");
        }
    }
    
    @FXML
    private void remplazarDatosFila(){
        LOGGER.info("he clicado remplazar fila");
        int indiceFilaSeleccionada = tabla.getSelectionModel().getSelectedIndex();

        if(tabla.getSelectionModel().getSelectedIndex()>=0 && tabla.getSelectionModel().getSelectedIndex()<personas.size()){
            
            txtNombre.setText(personas.get(indiceFilaSeleccionada).getNombre());
            txtApellido.setText(personas.get(indiceFilaSeleccionada).getApellido());
            txtEdad.setText(Integer.toString(personas.get(indiceFilaSeleccionada).getEdad()));
            
            btnAceptar.setVisible(true);
            
        }

    }
    @FXML
    private void aceptar(){
        PersonaBeans personaModificada;
        String nombre = txtNombre.getText() ;
        String apellido = txtApellido.getText();
        int edad = Integer.parseInt(txtEdad.getText());
        int indiceFilaSeleccionada = tabla.getSelectionModel().getSelectedIndex();
        
        personaModificada = new PersonaBeans(nombre, apellido, edad);
        personas.set(indiceFilaSeleccionada, personaModificada);
        txtNombre.setText("");
        txtApellido.setText("");
        txtEdad.setText("");
        
        btnAceptar.setVisible(false);
        
    }
    
    private void borrarTodasLasFilas() {
        for(int i=0;i<personas.size();i++){
            LOGGER.info("personas nº: "+i+" "+personas.get(i).getNombre());
        }
        LOGGER.info("he clicado borrar todo");
        tabla.getItems().removeAll(personas);
        //personas.clear();
        tabla.refresh();
        
    }
    @FXML
    private void imprimir(ActionEvent event){/*
        try {
            URL url = getClass().getResource("newReport1.jrxml");
            LOGGER.info("--------------------------------------"+url.toString());
            JasperReport report=
                JasperCompileManager.compileReport(getClass()
                    .getResourceAsStream("/tablas/newReport1.jrxml"));
            //Data for the report: a collection of UserBean passed as a JRDataSource 
            //implementation 
            JRBeanCollectionDataSource dataItems=
                    new JRBeanCollectionDataSource((Collection<PersonaBeans>)this.tabla.getItems());
            //Map of parameter to be passed to the report
            Map<String,Object> parameters=new HashMap<>();
            //Fill report with data
            JasperPrint jasperPrint = JasperFillManager.fillReport(report,parameters,dataItems);
            //Create and show the report window. The second parameter false value makes 
            //report window not to close app.
            JasperViewer jasperViewer = new JasperViewer(jasperPrint,false);
            jasperViewer.setVisible(true);
           // jasperViewer.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        } catch (JRException ex) {
            //If there is an error show message and
            //log it.
            
            LOGGER.log(Level.SEVERE,
                        "UI GestionUsuariosController: Error printing report: {0}",
                        ex.getMessage());
        }*/
    }

    @FXML
    private void buscar(ActionEvent event){
        LOGGER.info("He clicado buscar");
        String palabraBusqueda;
        ObservableList<PersonaBeans> arrayBusquedaPersona = FXCollections.observableArrayList();;
        if(txtBuscar.getText().isEmpty()){
            tabla.setItems(personas);
            tabla.refresh();
        }
        else{
            palabraBusqueda = txtBuscar.getText();
            for(int i = 0;i<personas.size();i++){
                if(personas.get(i).getNombre().contains(txtBuscar.getText()) ||
                        personas.get(i).getApellido().contains(txtBuscar.getText()) || 
                        Integer.toString(personas.get(i).getEdad()).contains(txtBuscar.getText())){
                    arrayBusquedaPersona.add(personas.get(i));
                    LOGGER.info(personas.get(i).getNombre());
                }
            }
            if(arrayBusquedaPersona.size()>0){
                tabla.setItems(arrayBusquedaPersona);
                tabla.refresh();
            }
            else
                LOGGER.info("No se ha encontrado conicidencias");
            
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
