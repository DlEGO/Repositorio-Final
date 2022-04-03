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

public class Pago extends JDialog {

	private final JPanel contentPanel = new JPanel();
	static Pago dialog;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dialog = new Pago(null);
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
		setBounds(100, 100, 307, 443);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(173, 10, 79, 14);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(173, 45, 79, 14);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Detalles del pedido");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(51, 20, 182, 14);
		contentPanel.add(lblNewLabel_2);
		
		JLabel usuarioNombre = new JLabel("Se\u00F1or(a)");
		usuarioNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		usuarioNombre.setBounds(21, 57, 62, 14);
		contentPanel.add(usuarioNombre);
		
		JLabel identificacionUsuario = new JLabel("ID");
		identificacionUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		identificacionUsuario.setBounds(21, 82, 62, 14);
		contentPanel.add(identificacionUsuario);
		
		JLabel pelicula = new JLabel("Pel\u00EDcula");
		pelicula.setHorizontalAlignment(SwingConstants.RIGHT);
		pelicula.setBounds(21, 107, 62, 14);
		contentPanel.add(pelicula);
		
		JLabel reservas = new JLabel("Reserv\u00F3");
		reservas.setHorizontalAlignment(SwingConstants.RIGHT);
		reservas.setBounds(21, 132, 62, 14);
		contentPanel.add(reservas);
		
		JLabel ubicacionesSillas = new JLabel("Ubicaciones");
		ubicacionesSillas.setHorizontalAlignment(SwingConstants.RIGHT);
		ubicacionesSillas.setBounds(21, 157, 62, 14);
		contentPanel.add(ubicacionesSillas);
		
		JLabel horaPelicula = new JLabel("Hora");
		horaPelicula.setHorizontalAlignment(SwingConstants.RIGHT);
		horaPelicula.setBounds(21, 182, 62, 14);
		contentPanel.add(horaPelicula);
		
		JLabel lblSnacks = new JLabel("Snacks");
		lblSnacks.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSnacks.setBounds(21, 207, 62, 14);
		contentPanel.add(lblSnacks);
		
		
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
		
		//Imprimir pedido
		System.out.println();System.out.println();System.out.println();
		System.out.println("Señor(a) " + pedido.getNombre());
		System.out.println("Con ID: " + pedido.getID());
		System.out.println("Para la película " + pedido.getPelicula());
		System.out.println("Reservó: " + pedido.getSillasNormales() + " Sillas normales, " + pedido.getSillasVIP() + " Sillas VIP, " + pedido.getSillasPref() + " Sillas preferenciales.");
		System.out.println("En las ubicaciones: " + pedido.getUbicacionSillas());
		System.out.println("Para la hora: " + pedido.getHoraPelicula());
		System.out.println("Con los siguientes snacks:");
		for(int i = 0; i < pedido.getSnacks().size() ; i++) {
			System.out.println(pedido.getSnacks().get(i));
		}
		System.out.println("Para un total de: " + pedido.getTotalSnacks());

	}
}
