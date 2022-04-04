package frame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cinema.Pedido;
import conexion.Conexion;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.GridLayout;

public class Pago extends JDialog {

	private final JPanel contentPanel = new JPanel();
	static Pago dialog;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dialog = new Pago(new Pedido("", ""));
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Pago(Pedido pedido) {
		setResizable(false);
		setModal(true);
		setAutoRequestFocus(false);
		setAlwaysOnTop(true);
		setBounds(100, 100, 509, 349);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(183, 11, 79, 14);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(183, 46, 79, 14);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Detalles del pedido");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(173, 21, 182, 14);
		contentPanel.add(lblNewLabel_2);
		
		JLabel lblusuarioNombre = new JLabel("Se\u00F1or(a):");
		lblusuarioNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblusuarioNombre.setBounds(31, 58, 62, 14);
		contentPanel.add(lblusuarioNombre);
		
		JLabel lblidentificacionUsuario = new JLabel("ID:");
		lblidentificacionUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblidentificacionUsuario.setBounds(31, 83, 62, 14);
		contentPanel.add(lblidentificacionUsuario);
		
		JLabel lblpelicula = new JLabel("Pel\u00EDcula:");
		lblpelicula.setHorizontalAlignment(SwingConstants.RIGHT);
		lblpelicula.setBounds(31, 108, 62, 14);
		contentPanel.add(lblpelicula);
		
		JLabel lblreservas = new JLabel("Reserv\u00F3:");
		lblreservas.setHorizontalAlignment(SwingConstants.RIGHT);
		lblreservas.setBounds(31, 133, 62, 14);
		contentPanel.add(lblreservas);
		
		JLabel lblubicacionesSillas = new JLabel("Ubicaciones:");
		lblubicacionesSillas.setHorizontalAlignment(SwingConstants.RIGHT);
		lblubicacionesSillas.setBounds(-15, 158, 108, 14);
		contentPanel.add(lblubicacionesSillas);
		
		JLabel lblhoraPelicula = new JLabel("Hora:");
		lblhoraPelicula.setHorizontalAlignment(SwingConstants.RIGHT);
		lblhoraPelicula.setBounds(31, 183, 62, 14);
		contentPanel.add(lblhoraPelicula);
		
		JLabel lblSnacks = new JLabel("Snacks:");
		lblSnacks.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSnacks.setBounds(331, 58, 48, 14);
		contentPanel.add(lblSnacks);
		
		JLabel nombreUsuario = new JLabel("New label");
		nombreUsuario.setBounds(96, 58, 217, 14);
		contentPanel.add(nombreUsuario);
		
		JLabel idUsuario = new JLabel("New label");
		idUsuario.setBounds(96, 83, 217, 14);
		contentPanel.add(idUsuario);
		
		JLabel nombrePelicula = new JLabel("New label");
		nombrePelicula.setBounds(96, 108, 217, 14);
		contentPanel.add(nombrePelicula);
		
		JLabel ctdSillasReservadas = new JLabel("New label");
		ctdSillasReservadas.setBounds(96, 133, 217, 14);
		contentPanel.add(ctdSillasReservadas);
		
		JLabel ubicacionesSillas = new JLabel("New label");
		ubicacionesSillas.setBounds(96, 158, 217, 14);
		contentPanel.add(ubicacionesSillas);
		
		JLabel horaPelicula = new JLabel("New label");
		horaPelicula.setBounds(96, 183, 217, 14);
		contentPanel.add(horaPelicula);
		
		JPanel snacksPanel = new JPanel();
		snacksPanel.setBounds(337, 83, 237, 167);
		contentPanel.add(snacksPanel);
		snacksPanel.setLayout(new GridLayout(0, 1, 0, 7));
		
		for(int i = 0; i < pedido.getSnacks().size() ; i++) {
			JLabel snack = new JLabel(pedido.getSnacks().get(i));
			//JLabel snack = new JLabel("eqiudte");		
			snack.setVisible(true);
			snacksPanel.add(snack);
		}
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		JButton pagarButton = new JButton("Pagar");
		pagarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Pagar XD
			}
		});
		pagarButton.setActionCommand("OK");
		buttonPane.add(pagarButton);
		getRootPane().setDefaultButton(pagarButton);
			
			
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String ssql = "UPDATE reservas set fila=('"+ pedido.getEstadoAnteriorSillas() +"') WHERE idsala=" + pedido.getSala() + "";
					System.out.println(ssql);
					Conexion.getSingleton().actualizarDatos(ssql);
					System.exit(0);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Ocurrió un error y no fue posible deshacer la operación. Intente nuevamente.");
				}
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
		
		JLabel lblTotalBoletas = new JLabel("Total Boletas:");
		lblTotalBoletas.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalBoletas.setBounds(0, 208, 93, 14);
		contentPanel.add(lblTotalBoletas);
		
		JLabel lblTotalSnacks = new JLabel("Total Snacks:");
		lblTotalSnacks.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalSnacks.setBounds(0, 233, 93, 14);
		contentPanel.add(lblTotalSnacks);
		
		JLabel totalBoletas = new JLabel("");
		totalBoletas.setBounds(96, 208, 217, 14);
		contentPanel.add(totalBoletas);
		
		JLabel totalSnacks = new JLabel((String) null);
		totalSnacks.setBounds(96, 233, 217, 14);
		contentPanel.add(totalSnacks);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotal.setBounds(0, 258, 93, 14);
		contentPanel.add(lblTotal);
		
		JLabel total = new JLabel("");
		total.setBounds(96, 258, 217, 14);
		contentPanel.add(total);
		
		//Imprimir pedido
		nombreUsuario.setText(pedido.getNombre());
		idUsuario.setText(pedido.getID());
		nombrePelicula.setText(pedido.getPelicula());
		ctdSillasReservadas.setText(pedido.getSillasNormales() + " generales, " + pedido.getSillasVIP() + " VIP, " + pedido.getSillasPref() + " preferenciales.");
		ubicacionesSillas.setText(pedido.getUbicacionSillas());
		horaPelicula.setText(pedido.getHoraPelicula());
		totalBoletas.setText(String.valueOf(pedido.getTotalSillas()));
		totalSnacks.setText(String.valueOf(pedido.getTotalSnacks()));
		total.setText(String.valueOf(pedido.getTotalSnacks() + pedido.getTotalSillas()));
	}	
}
