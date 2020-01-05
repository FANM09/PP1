package Controlador;

import Modelo.Recurso;
import Modelo.Sala;
import Modelo.Usuario;
import Vista.VentanaAgregarSala;
import Vista.VentanaFuncionario;
import Vista.VentanaLogin;
import Vista.VentanaModificarSala;
import Vista.VentanaRegistroEstudiante;
import dao.UsuarioDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorFuncionarioDAO implements ActionListener {

    public UsuarioDAO dao;
    public VentanaFuncionario vista;
    public Usuario modelo;

    public ControladorFuncionarioDAO(VentanaFuncionario pVista) {
        vista = pVista;
        this.vista.btAgregarSala.addActionListener(this);
        this.vista.btAnalisis.addActionListener(this);
        this.vista.btCancelarReserva.addActionListener(this);
        this.vista.btConsultaEstudiante.addActionListener(this);
        this.vista.btConsultaReserva.addActionListener(this);
        this.vista.btConsultarSala.addActionListener(this);
        this.vista.btModificarSala.addActionListener(this);
        this.vista.btRegistroEstudiante.addActionListener(this);
        this.vista.btSalir.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Registrar Estudiante":
                añadirEstudiante();
                break;
            case "Consultar Estudiante":
                //cerrarVentanaLogin();
                break;
            case "Agregar Sala":
                agregarSala();
                break;
            case "Modificar Sala":
                modificarSala();
                break;
            case "Consultar Sala":

                break;
            case "Análisis de datos":

                break;
            case "Consultar Reserva":

                break;
            case "Cancelar Reserva":

                break;
            case "Salir":
                cerrarVentanaFuncionario();
                break;
            default:
                break;
        }
    }

    public void añadirEstudiante() {
        VentanaRegistroEstudiante vista = new VentanaRegistroEstudiante();
        ControladorEstudianteDAO controladorEstudiante = new ControladorEstudianteDAO(vista);
        controladorEstudiante.vista.setVisible(true);
        controladorEstudiante.vista.setLocationRelativeTo(null);
    }

    public void agregarSala() {
        VentanaAgregarSala vistaSala = new VentanaAgregarSala();
        Sala sala = new Sala();
        Recurso recurso = new Recurso();
        ControladorSalaDAO controladorSala = new ControladorSalaDAO(vistaSala, sala, recurso);
        controladorSala.vista.setVisible(true);
        controladorSala.vista.setLocationRelativeTo(null);
    }

    public void modificarSala() {
        VentanaModificarSala vistaModificacionSala = new VentanaModificarSala();
        Sala sala = new Sala();
        Recurso recurso = new Recurso();
        ControladorModificarSalaDAO controladorModSala = new ControladorModificarSalaDAO(vistaModificacionSala, sala, recurso);
        controladorModSala.vista.setVisible(true);
        controladorModSala.vista.setLocationRelativeTo(null);
    }

    public void cerrarVentanaFuncionario() {
        vista.cerrarVentana();
        VentanaLogin ventana = new VentanaLogin();
        ControladorLogin controladorLogin = new ControladorLogin(ventana, modelo);
        controladorLogin.vista.setVisible(true);
        controladorLogin.vista.setLocationRelativeTo(null);
    }
}
