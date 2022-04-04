package frame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cinema.Pedido;
import conexion.Conexion;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	boolean access = false;
	
	/**
	 * Launch the application.
	 * @throws SQLException 
	 */
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
	}
	private JTextField idField;
	private JTextField nombreField;
	public Main() throws SQLException {
		
		setResizable(false);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 247, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, 241, 221);
		contentPane.add(mainPanel);
		mainPanel.setLayout(null);
		
		
		
		JButton btnUsuario = new JButton("Entrar");
		btnUsuario.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					entrar();
				}
			
		});
		btnUsuario.setBounds(70, 131, 89, 23);
		mainPanel.add(btnUsuario);
		
		JButton btnAdmin = new JButton("Entrar como administrador");
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usr = JOptionPane.showInputDialog("Ingresa el usuario: ");
				String pass = JOptionPane.showInputDialog("Ingresa la contraseña: ");
				
				String ssql = "SELECT * FROM usuarios WHERE nombre='"+ usr +"' AND identificacion='"+ pass +"'";
				try {
					if(Conexion.getSingleton().validarCredenciales(ssql)) {
						AdministrarPeliculas adminPelis = new AdministrarPeliculas();
						adminPelis.setVisible(true);
						
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAdmin.setBounds(10, 165, 209, 23);
		mainPanel.add(btnAdmin);
		
		idField = new JTextField();
		idField.setBounds(70, 91, 89, 20);
		mainPanel.add(idField);
		idField.setColumns(10);
		
		nombreField = new JTextField();
		nombreField.setBounds(70, 33, 86, 20);
		mainPanel.add(nombreField);
		nombreField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(70, 11, 86, 14);
		mainPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Identificaci\u00F3n");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(70, 66, 89, 14);
		mainPanel.add(lblNewLabel_1);

	}
	
	private void entrar() {
		//Primero verifica que los campos de texto no estén vacíos
		if(!nombreField.getText().isBlank() && !idField.getText().isBlank()) {
			//Crea las sentencias a ejecutar en la base de datos
			
			try {
				String ssql1 = "INSERT INTO usuarios (nombre, identificacion) VALUES ('"+ nombreField.getText() +"', '"+ Integer.parseInt(idField.getText()) +"')";
				String ssql = "SELECT * FROM usuarios WHERE identificacion='"+ Integer.parseInt(idField.getText()) +"'";
				//Primero verifica solamente que no exista un registro con ese documento de identidad (No consulta por nombre)
				if(!Conexion.getSingleton().validarCredenciales(ssql)) {
					//Si no existe el registro, lo añade a la base de datos
					Conexion.getSingleton().sql.execute(ssql1);
				}
				//Si ya existe, simplemente crea un pedido e inica el frame para escoger la película
				Pedido pedido = new Pedido(nombreField.getText(), idField.getText());
				Cinema cinema = new Cinema(pedido);
				cinema.setVisible(true);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}catch(NumberFormatException e2) {
				JOptionPane.showMessageDialog(null, "Error. Debe introducir un Id numérico.");
			}
		}else {
			JOptionPane.showMessageDialog(null, "No puede haber algún campo vacío.");
		}
	}
}
