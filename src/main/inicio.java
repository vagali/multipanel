/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.inicioFXMLDocumentController;

/**
 *
 * @author Sergio
 */
public class inicio extends Application {
    
   @Override
    public void start(Stage stage) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/inicio.fxml"));

            Parent root = (Parent)loader.load();

       //     inicioFXMLDocumentController controller = ((inicioFXMLDocumentController)loader.getController());
        //    controller.setStage(stage);
         //   controller.initStage(root);
            Scene scene = new Scene(root);

        //Enviar scena al stage.
        stage.setScene(scene);
        //Maximizable deshabilitado.
        stage.setResizable(false);
        stage.setTitle("Registro");
        //imagenes
        
        stage.show();
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
