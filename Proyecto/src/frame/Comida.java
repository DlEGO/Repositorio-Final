package frame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import cinema.Pedido;
import conexion.Conexion;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Comida extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField DeTodito;
	private JTextField PapasPollo;
	private JTextField PapasNatural;
	private JTextField JetPequena;
	private JTextField Doritos;
	private JTextField JetJumbo;
	private JTextField Colombiana;
	private JTextField Chokis;
	private JTextField CocaCola;
	
	private int[] cantidadItemsInventario = new int[9];
	private int[] precios = {7500,3100,3000,1500,3000,5000,3000,2000,3500};
	private int total = 0;
	
	ArrayList<JTextField> productos = new ArrayList<JTextField>();
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Comida dialog = new Comida(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @throws SQLException 
	 * @throws NumberFormatException 
	 * @throws IOException 
	 */
	public Comida(Pedido pedido)throws SQLException {
		//Obtiene la cantidad de cada ítem desde la base de datos y lo añade a un array
		for(int i = 0; i < cantidadItemsInventario.length ; i++) {		
			try {	
				//el i + 1 es porque los índices en la bd empiezan desde 1
				String ssql = "SELECT * FROM inventariocomida WHERE idproducto="+ (i + 1) +"";
				cantidadItemsInventario[i] = Integer.parseInt(Conexion.getSingleton().cargarDatos(ssql, "cantidad"));
				System.out.println(cantidadItemsInventario[i]);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
		}
		
		
		//Cada JTextField es añadido a un arraylist para simplificar codigo adelante
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setModal(true);
		setAutoRequestFocus(false);
		setAlwaysOnTop(true);
		setBounds(100, 100, 490, 629);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 484, 530);
		panel_1.setOpaque(false);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		DeTodito = new JTextField();
		DeTodito.setBounds(342, 150, 80, 20);
		panel_1.add(DeTodito);
		DeTodito.setColumns(10);
		DeTodito.setName("De Todito");
		productos.add(DeTodito);
		
		PapasPollo = new JTextField();
		PapasPollo.setBounds(57, 150, 80, 20);
		panel_1.add(PapasPollo);
		PapasPollo.setColumns(10);
		PapasPollo.setName("Papas de pollo");
		productos.add(PapasPollo);
		
		PapasNatural = new JTextField();
		PapasNatural.setBounds(199, 150, 80, 20);
		panel_1.add(PapasNatural);
		PapasNatural.setColumns(10);
		PapasNatural.setName("Papas natural");
		productos.add(PapasNatural);
		
		JetPequena = new JTextField();
		JetPequena.setColumns(10);
		JetPequena.setBounds(342, 314, 80, 20);
		panel_1.add(JetPequena);
		JetPequena.setName("Jet Pequeña");
		productos.add(JetPequena);
		
		Doritos = new JTextField();
		Doritos.setColumns(10);
		Doritos.setBounds(57, 314, 80, 20);
		panel_1.add(Doritos);
		Doritos.setName("Doritos");
		productos.add(Doritos);
		
		JetJumbo = new JTextField();
		JetJumbo.setColumns(10);
		JetJumbo.setBounds(199, 314, 80, 20);
		panel_1.add(JetJumbo);
		JetJumbo.setName("Jet Jumbo");
		productos.add(JetJumbo);
		
		Colombiana = new JTextField();
		Colombiana.setColumns(10);
		Colombiana.setBounds(342, 486, 80, 20);
		panel_1.add(Colombiana);
		Colombiana.setName("Colombiana");
		productos.add(Colombiana);
		
		Chokis = new JTextField();
		Chokis.setColumns(10);
		Chokis.setBounds(57, 486, 80, 20);
		panel_1.add(Chokis);
		Chokis.setName("Chokis");
		productos.add(Chokis);
		
		CocaCola = new JTextField();
		CocaCola.setColumns(10);
		CocaCola.setBounds(199, 486, 80, 20);
		panel_1.add(CocaCola);
		CocaCola.setName("Coca-Cola");
		productos.add(CocaCola);
		
		//-----------------------------------------
		JLabel lDetodito = new JLabel("7500");
		lDetodito.setHorizontalAlignment(SwingConstants.CENTER);
		lDetodito.setBounds(343, 11, 80, 28);
		panel_1.add(lDetodito);
		
		JLabel lPapasNat = new JLabel("3000");
		lPapasNat.setHorizontalAlignment(SwingConstants.CENTER);
		lPapasNat.setBounds(199, 11, 80, 28);
		panel_1.add(lPapasNat);
		
		JLabel lPapaspollo = new JLabel("3100");
		lPapaspollo.setHorizontalAlignment(SwingConstants.CENTER);
		lPapaspollo.setBounds(57, 11, 80, 28);
		panel_1.add(lPapaspollo);
		
		JLabel lJetp = new JLabel("1500");
		lJetp.setHorizontalAlignment(SwingConstants.CENTER);
		lJetp.setBounds(343, 181, 80, 28);
		panel_1.add(lJetp);
		
		JLabel lJetg = new JLabel("5000");
		lJetg.setHorizontalAlignment(SwingConstants.CENTER);
		lJetg.setBounds(199, 181, 80, 28);
		panel_1.add(lJetg);
		
		JLabel lDoritos = new JLabel("3000");
		lDoritos.setHorizontalAlignment(SwingConstants.CENTER);
		lDoritos.setBounds(57, 181, 80, 28);
		panel_1.add(lDoritos);
		
		JLabel lColombiana = new JLabel("3000");
		lColombiana.setHorizontalAlignment(SwingConstants.CENTER);
		lColombiana.setBounds(342, 355, 80, 28);
		panel_1.add(lColombiana);
		
		JLabel lCocacola = new JLabel("3500");
		lCocacola.setHorizontalAlignment(SwingConstants.CENTER);
		lCocacola.setBounds(198, 355, 80, 28);
		panel_1.add(lCocacola);
		
		JLabel lChokis = new JLabel("2000");
		lChokis.setHorizontalAlignment(SwingConstants.CENTER);
		lChokis.setBounds(56, 355, 80, 28);
		panel_1.add(lChokis);
		borrarSeleccion();

		
		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("Button.darkShadow"));
		panel.setBounds(39, 26, 403, 471);
		panel.setOpaque(false);
		contentPanel.add(panel);
		panel.setLayout(new GridLayout(3, 3, 30, 50));
		
		JLabel papasNatural = new JLabel("");
		papasNatural.setHorizontalAlignment(SwingConstants.CENTER);
		papasNatural.setIcon(new ImageIcon(Comida.class.getResource("/images/pmp.png")));
		panel.add(papasNatural);
		
		JLabel papasPollo = new JLabel("");
		papasPollo.setHorizontalAlignment(SwingConstants.CENTER);
		papasPollo.setIcon(new ImageIcon(Comida.class.getResource("/images/pmn.png")));
		panel.add(papasPollo);
		
		JLabel detoditoFamiliar = new JLabel("");
		detoditoFamiliar.setHorizontalAlignment(SwingConstants.CENTER);
		detoditoFamiliar.setIcon(new ImageIcon(Comida.class.getResource("/images/dtf.png")));
		panel.add(detoditoFamiliar);
		
		JLabel doritos = new JLabel("");
		doritos.setHorizontalAlignment(SwingConstants.CENTER);
		doritos.setIcon(new ImageIcon(Comida.class.getResource("/images/dad.png")));
		panel.add(doritos);
		
		JLabel chocolateG = new JLabel("");
		chocolateG.setHorizontalAlignment(SwingConstants.CENTER);
		chocolateG.setIcon(new ImageIcon(Comida.class.getResource("/images/cjj.png")));
		panel.add(chocolateG);
		
		JLabel chocolateP = new JLabel("");
		chocolateP.setHorizontalAlignment(SwingConstants.CENTER);
		chocolateP.setIcon(new ImageIcon(Comida.class.getResource("/images/cjp.png")));
		panel.add(chocolateP);
		
		JLabel galletasChokis = new JLabel("");
		galletasChokis.setHorizontalAlignment(SwingConstants.CENTER);
		galletasChokis.setIcon(new ImageIcon(Comida.class.getResource("/images/gcn.png")));
		panel.add(galletasChokis);
		
		JLabel cocacola = new JLabel("");
		cocacola.setHorizontalAlignment(SwingConstants.CENTER);
		cocacola.setIcon(new ImageIcon(Comida.class.getResource("/images/ccp.png")));
		panel.add(cocacola);
		
		JLabel colombiana = new JLabel("");
		colombiana.setHorizontalAlignment(SwingConstants.CENTER);
		colombiana.setIcon(new ImageIcon(Comida.class.getResource("/images/cpc.png")));
		panel.add(colombiana);
		//----------------------------------------------------------
		
		JButton btnNewButton = new JButton("Continuar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				continuar(pedido);
			}
		});
		btnNewButton.setBounds(366, 541, 89, 23);
		contentPanel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Borrar selecci\u00F3n");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrarSeleccion();
			}
		});
		btnNewButton_1.setBounds(202, 541, 154, 23);
		contentPanel.add(btnNewButton_1);
	}
	
	void continuar(Pedido pedido) {
		//En este arraylist van los productos comprados
		ArrayList<String> productoComprado = new ArrayList<String>();
		
		//Envía los productos comprados y la cantidad por separado por medio de un arrayList
		//Primero se escogen aquellos producto donde se ingresó una cantidad diferente de 0
		for(int i = 0; i < productos.size() ; i++) {
			if(!productos.get(i).getText().equals("")) {
				String producto = productos.get(i).getName() + ": " + productos.get(i).getText();
				productoComprado.add(producto);
			}
		}
		
		//después pone ceros donde hay espacios vacíos, para evitar un error en la suma
		for(int i = 0; i < 9 ; i++){
			if(productos.get(i).getText().equals("")) {
				productos.get(i).setText("0");
			}
		}
		
		//Calcula el total
		//Hace la sumatoria del producto de cada ítem por su precio
		//*Se agregan los precios a un arreglo en el mismo orden en el que se hacen los JTextField para simplificar
		for(int i = 0 ; i < 9 ; i++) {
			total += Integer.parseInt(productos.get(i).getText()) * precios[i];
		}
		//Se setean los parámetros dentro del objeto pedido enviado desde Multiplex
		pedido.setTotalSnacks(total);	
		pedido.setSnacks(productoComprado);
		//Y se abre la ventana de pago
		Pago pago = new Pago(pedido);
		pago.setVisible(true);
	}
	
	//Setea el total a 0 para volver a ser calculado según los valores ingresados en los campos de texto
	void borrarSeleccion() {
		total = 0;
		for(int i = 0; i < productos.size() ; i++) {
			productos.get(i).setText("");
		}
	}
}
