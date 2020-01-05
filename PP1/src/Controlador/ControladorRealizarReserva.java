package Controlador;
import Conexion.ConexionJavaMySQL;
import Modelo.EnumDiaSemana;
import Vista.VentanaRealizarReserva;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
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
        buscar();
        break;
      default:
        break;
    }
  }
  public void buscar() {
    if(vista.validarDatos()){
          Date date = vista.chooseFecha.getDatoFecha();
          Calendar fecha = Calendar.getInstance();
          fecha.setTime(date);
          String dia=EnumDiaSemana.get(fecha.get(Calendar.DAY_OF_WEEK));
          System.out.println(dia);
          String horaInicio;
          String capacidad;
          String horaFin;
          horaInicio=vista.txtHoraInicio.getText();
          horaFin=vista.txtHoraFin.getText();
          capacidad=vista.txtCapacidad.getText();
            try {
            DefaultTableModel modelo = new DefaultTableModel();
            vista.tablaSalas.setModel(modelo);
            PreparedStatement ps = null;
            ResultSet rs = null;
            ConexionJavaMySQL conn = new ConexionJavaMySQL();
            Connection con = conn.getConexion();
            String sql = "select sala.identificador from sala join horario on sala.idHorario=horario.idHorario where horario.diasDisponible like \"%"+dia+"%\" and sala.capacidad>="+capacidad+" and horario.horaApertura between '"+horaInicio+"' and '"+horaFin+"' and '"+horaInicio+"'>=horario.horaApertura and horario.horaCierre >='"+horaFin+"'";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();
            modelo.addColumn("idSala");
  
            int[] anchos = {50,50};
            for (int i = 0; i < vista.tablaSalas.getColumnCount(); i++) {
                vista.tablaSalas.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
            }
            while (rs.next()) {
                Object[] filas = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    filas[i] = rs.getObject(i + 1);
                }
                modelo.addRow(filas);
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
        }else{
          JOptionPane.showMessageDialog(null, "Faltan datos.");
        }
    

    }
  
}
