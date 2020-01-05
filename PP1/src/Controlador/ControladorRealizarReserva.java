package Controlador;
import Modelo.EnumDiaSemana;
import Vista.VentanaRealizarReserva;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
/**
 *
 * @author Jeremy
 */
public class ControladorRealizarReserva implements ActionListener{
  VentanaRealizarReserva vista = new VentanaRealizarReserva();
  
  public ControladorRealizarReserva() {
        this.vista.setVisible(true);
        this.vista.botonBuscar.addActionListener(this);
    }

  @Override
  public void actionPerformed(ActionEvent ae) {
    switch(ae.getActionCommand()){
      case "Buscar":
        if(vista.validarDatos()){
          Date date = vista.chooseFecha.getDatoFecha();
          Calendar fecha = Calendar.getInstance();
          fecha.setTime(date);
          String dia=EnumDiaSemana.get(fecha.get(Calendar.DAY_OF_WEEK));
        }else{
          JOptionPane.showMessageDialog(null, "Faltan datos.");
        }
        break;
      default:
        break;
    }
  }
  
}
