/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;


import java.net.URL;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

/**
 *
 * @author Sergio
 */
public class loaderFXML{
    private static final Logger LOGGER = Logger.getLogger("reto1_escritorio.view.Registro");
    private Pane ventana;
    public Pane getPane(String ventana){
        
        try {
            URL url = getClass().getResource("/view/"+ventana+".fxml");
            if(ventana == null)
                LOGGER.info("Ventana no encontrada");
            this.ventana = new FXMLLoader().load(url);
        } catch (Exception e) {
            LOGGER.info("vaya ha pasado algo");
        }
        
        
        return this.ventana;
    }
}
