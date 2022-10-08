/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Conexion;
import Vista.VistaConsola;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;


/**
 *
 * @author juald
 */
public class ControladorLogin 
{
     private Conexion conexion_bd;
     private VistaConsola vista = new VistaConsola();
     private boolean conexionOK = false;
     private boolean desconexionOK = false;
    
     public ControladorLogin() 
     {
        this.conexionOK = conectar();

        try {

            this.vista.vistaMetadatos(this.recuperar_metadatos());

        } catch (SQLException e) {
        }

        this.desconexionOK = this.desconectar();
    }
     private boolean conectar() {

        boolean resultado = false;

        try {
            this.conexion_bd = new Conexion();
            this.vista.mensajeConsola("Conexion realizada con exito!!!");
            resultado = true;
        } catch (SQLException e) {

            this.vista.mensajeConsola("Error en la conexion", e.getMessage());
        }
        return resultado;
    }

    private boolean desconectar() {

        boolean resultado = false;

        try {
            this.conexion_bd.desconexion();
            this.vista.mensajeConsola("Desconexion de la BD efectuada con exito!");
            resultado = true;
        } catch (Exception e) {
            this.vista.mensajeConsola("Error al desconectarse de la BD", e.getMessage());
        }
        return resultado;
    }  
    public DatabaseMetaData recuperar_metadatos() throws SQLException {

        return this.conexion_bd.informacionBD();
    }   
}
