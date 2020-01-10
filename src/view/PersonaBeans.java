/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sergio
 */
@XmlRootElement(name="user")
public class PersonaBeans {

    private SimpleStringProperty nombre;
    private SimpleStringProperty apellido;
    private SimpleIntegerProperty edad;


    public PersonaBeans(String nombre,String apellido,Integer edad){
        this.nombre = new SimpleStringProperty(nombre);
        this.apellido = new SimpleStringProperty(apellido);
        this.edad = new SimpleIntegerProperty(edad);
    }
    @XmlElement(name="nombre")
    public String getNombre(){
        return this.nombre.get();
    }
    @XmlElement(name="apellido")
     public String getApellido(){
        return this.apellido.get();
    }
     @XmlElement(name="edad")
      public int getEdad(){
        return this.edad.get();
    }

}
