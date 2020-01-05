package Controlador;
import Conexion.ConexionJavaMySQL;
import Vista.VentanaConsultaReserva;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import Conexion.ConexionJavaMySQL;
/**
 *
 * @author Jeremy
 */
public class ControladorConsultaReserva implements ActionListener {
  VentanaConsultaReserva vista=new VentanaConsultaReserva();
  
  
      public ControladorConsultaReserva() {
        this.vista.setVisible(true);
        this.vista.botonBuscar.addActionListener(this);

    }

  @Override
  public void actionPerformed(ActionEvent ae) {
    switch (ae.getActionCommand()) {
            case "Buscar":
              buscar();

                break;
            default:
                break;
        }
  }
  
      public void buscar() {
                try {
            DefaultTableModel modelo = new DefaultTableModel();
            vista.tablaReservas.setModel(modelo);

            PreparedStatement ps = null;
            ResultSet rs = null;
            ConexionJavaMySQL conn = new ConexionJavaMySQL();
            Connection con = conn.getConexion();
            String carne;
            carne=vista.txtCarne.getText();

            String sql = "SELECT idReserva, asunto,fecha,reserva.horaInicio,reserva.horaFinalizacion,capacidadMinima,idEstado,reserva.cedula,idSala FROM reserva join persona on persona.cedula=reserva.cedula where persona.carnet='"+carne+"'";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();

            modelo.addColumn("IdReserva");
            modelo.addColumn("Asunto");
            modelo.addColumn("Fecha");
            modelo.addColumn("HoraInicio");
            modelo.addColumn("HoraFin");
            modelo.addColumn("capacidadMinima");
            modelo.addColumn("IdEstado");
            modelo.addColumn("Cédula");
            modelo.addColumn("IdSala");

            int[] anchos = {50, 200, 200, 150, 100, 100,100,100,200,50};
            for (int i = 0; i < vista.tablaReservas.getColumnCount(); i++) {
                vista.tablaReservas.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
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
    }

  
}
