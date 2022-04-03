package frame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import conexion.Conexion;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class AdministrarPeliculas extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField nombrePeli;
	private JTextField duracionPeli;
	private JTextField anoPeli;
	private JTextField generoPeli;
	private JTextField idiomaPeli;
	private JTextField urlField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AdministrarPeliculas dialog = new AdministrarPeliculas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AdministrarPeliculas() {
		setTitle("Agregar y eliminar peliculas");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 258, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(45, 29, 46, 14);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Duracion");
		lblNewLabel_1.setBounds(45, 54, 46, 14);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("A\u00F1o");
		lblNewLabel_2.setBounds(45, 79, 46, 14);
		contentPanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Genero");
		lblNewLabel_3.setBounds(45, 104, 46, 14);
		contentPanel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Idioma");
		lblNewLabel_4.setBounds(45, 129, 46, 14);
		contentPanel.add(lblNewLabel_4);
		
		JLabel urllbl = new JLabel("Imagen URL");
		urllbl.setBounds(20, 154, 71, 14);
		contentPanel.add(urllbl);
		
		idiomaPeli = new JTextField();
		idiomaPeli.setBounds(111, 126, 86, 20);
		contentPanel.add(idiomaPeli);
		idiomaPeli.setColumns(10);
		
		generoPeli = new JTextField();
		generoPeli.setBounds(111, 101, 86, 20);
		contentPanel.add(generoPeli);
		generoPeli.setColumns(10);
		
		anoPeli = new JTextField();
		anoPeli.setBounds(111, 76, 86, 20);
		contentPanel.add(anoPeli);
		anoPeli.setColumns(10);
		
		duracionPeli = new JTextField();
		duracionPeli.setBounds(111, 51, 86, 20);
		contentPanel.add(duracionPeli);
		duracionPeli.setColumns(10);
		
		nombrePeli = new JTextField();
		nombrePeli.setBounds(111, 26, 86, 20);
		contentPanel.add(nombrePeli);
		nombrePeli.setColumns(10);
		
		urlField = new JTextField();
		urlField.setBounds(111, 151, 86, 20);
		contentPanel.add(urlField);
		urlField.setColumns(10);
		
		JButton agregarBtn = new JButton("Agregar");
		agregarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int duracion = 0;
				int ano = 0;
				//Datos de la película
				String nombre = nombrePeli.getText();
				try {
					duracion = Integer.parseInt(duracionPeli.getText());
					ano = Integer.parseInt(anoPeli.getText());
				}catch(NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Error, Introduce un valor número en el campo duración y año");
				}
				String genero =  generoPeli.getText();
				String idioma = idiomaPeli.getText();
				String url = urlField.getText();
				
				//Crea la sentencia
				String ssql = "INSERT INTO peliculas (nombre, duracion, ano, genero, idioma, url) VALUES ('"+nombre+"',"+duracion+","+ano+",'"+genero+"','"+idioma+"', '"+url+"')";
				
				//Ejecuta la sentencia y añade la película
				try {
					Conexion.getSingleton().sql.execute(ssql);
					JOptionPane.showMessageDialog(null, "Película agregada correctamente.");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		agregarBtn.setBounds(61, 191, 122, 23);
		contentPanel.add(agregarBtn);
		
		JButton eliminarBtn = new JButton("Eliminar");
		eliminarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Obtiene el nombre de la película (Respetando mayusculas)
				String nombre = JOptionPane.showInputDialog("Ingresa el nombre de la película a eliminar: ");
				
				//crea dos sentencia
				String ssql = "DELETE FROM peliculas WHERE nombre='"+nombre+"'";
				String ssql1 = "SELECT * FROM peliculas WHERE nombre='"+nombre+"'";
				try {
					//Primero comprueba que si existe la película entes de borrarla
					if(!Conexion.getSingleton().validarCredenciales(ssql1)) {
						//Si no existe una película con ese nombre, arroja error
						JOptionPane.showMessageDialog(null, "No se encontró una película con ese nombre, por tanto, no se borró ningún resgitro");
					}else {
						//Si existe la pelicula, la borra
						try {
							Conexion.getSingleton().sql.execute(ssql);
							JOptionPane.showMessageDialog(null, "Película eliminada correctamente.");
						} catch (SQLException e1) {
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null, "No se ha logrado eliminar la película correctamente.");
						}
					}
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
			}
		});
		eliminarBtn.setBounds(71, 225, 101, 23);
		contentPanel.add(eliminarBtn);
		
	}
}
