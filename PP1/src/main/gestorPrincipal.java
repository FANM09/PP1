/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Controlador.ControladorLogin;
import Modelo.Usuario;
import Vista.VentanaLogin;
import javax.mail.MessagingException;
import mailer.Mail;

/**
 *
 * @author Fabian Navarro
 */
public class gestorPrincipal {

    public static void main(String[] args) throws MessagingException {
        //Mail.enviarCorreo();
        Usuario modelo = new Usuario();
        VentanaLogin vista = new VentanaLogin();
        //AgregarSalaForm vista2 = new AgregarSalaForm();
        //Sala modelo2 = new Sala();

        ControladorLogin controladorUsuario = new ControladorLogin(vista, modelo);

        //ControladorSalaDAO controladorSala = new ControladorSalaDAO(vista2, modelo2);
        controladorUsuario.vista.setVisible(true);
        controladorUsuario.vista.setLocationRelativeTo(null);

        //controladorSala.vista.setVisible(true);
        //controladorSala.vista.setLocationRelativeTo(null);
    }
}
