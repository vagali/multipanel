/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Sergio
 */
public class inicioFXMLDocumentController{
    private static final Logger LOGGER = Logger.getLogger("reto1_escritorio.view.Registro");
    private Stage stage;
    @FXML private BorderPane mainPane;
    
    
    public void setStage(Stage stage){
        this.stage = stage;
    }
    @FXML public void clickPerfil(MouseEvent e) throws IOException{
        if(e.getButton() == MouseButton.PRIMARY){
          LOGGER.info("Perfil");
          Pane pane;
          URL url = getClass().getResource("/view/perfil.fxml");
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/perfil.fxml"));
          Parent root = (Parent)loader.load();
          perfilFXMLDocumentController controller = ((perfilFXMLDocumentController)loader.getController());
          controller.setStage(stage);
          controller.initStage(root);
          pane = new FXMLLoader().load(url);
          mainPane.setCenter(root);
        }                
    }
    @FXML public void clickTienda(MouseEvent e) throws IOException{
        if(e.getButton() == MouseButton.PRIMARY){
          LOGGER.info("Perfil");
          Pane pane;
          URL url = getClass().getResource("/view/tienda.fxml");
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/tienda.fxml"));
          Parent root = (Parent)loader.load();
          tiendaFXMLDocumentController controller = ((tiendaFXMLDocumentController)loader.getController());
          controller.setStage(stage);
          controller.initStage(root);
          pane = new FXMLLoader().load(url);
          mainPane.setCenter(root);
        }
    }
    @FXML public void clickBiblioteca(MouseEvent e){
        if(e.getButton() == MouseButton.PRIMARY){
          LOGGER.info("Biblio");
        }                
    }
}
