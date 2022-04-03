package frame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cinema.Pedido;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		setBounds(100, 100, 450, 300);
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
		
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
		JButton okButton = new JButton("OK");
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
			
			
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
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
