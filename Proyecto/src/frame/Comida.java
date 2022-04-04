package frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

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
		
		JPanel cantidadesPanel = new JPanel();
		cantidadesPanel.setBounds(39, 71, 403, 384);
		cantidadesPanel.setOpaque(false);
		contentPanel.add(cantidadesPanel);
		cantidadesPanel.setLayout(new GridLayout(3, 3, 30, 150));
		
		JPanel preciosPanel = new JPanel();
		preciosPanel.setBounds(0, 0, 484, 530);
		preciosPanel.setOpaque(false);
		contentPanel.add(preciosPanel);
		preciosPanel.setLayout(null);
		
		PapasPollo = new JTextField();
		PapasPollo.setBounds(57, 150, 80, 20);
		preciosPanel.add(PapasPollo);
		PapasPollo.setColumns(10);
		PapasPollo.setName("Papas de pollo");
		productos.add(PapasPollo);
		
		PapasNatural = new JTextField();
		PapasNatural.setBounds(199, 150, 80, 20);
		preciosPanel.add(PapasNatural);
		PapasNatural.setColumns(10);
		PapasNatural.setName("Papas natural");
		productos.add(PapasNatural);
		
		DeTodito = new JTextField();
		DeTodito.setBounds(342, 150, 80, 20);
		preciosPanel.add(DeTodito);
		DeTodito.setColumns(10);
		DeTodito.setName("De Todito");
		productos.add(DeTodito);
	
		
		Doritos = new JTextField();
		Doritos.setColumns(10);
		Doritos.setBounds(57, 314, 80, 20);
		preciosPanel.add(Doritos);
		Doritos.setName("Doritos");
		productos.add(Doritos);
		
		JetJumbo = new JTextField();
		JetJumbo.setColumns(10);
		JetJumbo.setBounds(199, 314, 80, 20);
		preciosPanel.add(JetJumbo);
		JetJumbo.setName("Jet Jumbo");
		productos.add(JetJumbo);
		
		JetPequena = new JTextField();
		JetPequena.setColumns(10);
		JetPequena.setBounds(342, 314, 80, 20);
		preciosPanel.add(JetPequena);
		JetPequena.setName("Jet Pequeña");
		productos.add(JetPequena);
		
		Chokis = new JTextField();
		Chokis.setColumns(10);
		Chokis.setBounds(57, 486, 80, 20);
		preciosPanel.add(Chokis);
		Chokis.setName("Chokis");
		productos.add(Chokis);	
		
		CocaCola = new JTextField();
		CocaCola.setColumns(10);
		CocaCola.setBounds(199, 486, 80, 20);
		preciosPanel.add(CocaCola);
		CocaCola.setName("Coca-Cola");
		productos.add(CocaCola);
		
		Colombiana = new JTextField();
		Colombiana.setColumns(10);
		Colombiana.setBounds(342, 486, 80, 20);
		preciosPanel.add(Colombiana);
		Colombiana.setName("Colombiana");
		productos.add(Colombiana);
		
		//-----------------------------------------
		JLabel lDetodito = new JLabel("7500");
		lDetodito.setHorizontalAlignment(SwingConstants.CENTER);
		lDetodito.setBounds(343, 11, 80, 28);
		preciosPanel.add(lDetodito);
		
		JLabel lPapasNat = new JLabel("3000");
		lPapasNat.setHorizontalAlignment(SwingConstants.CENTER);
		lPapasNat.setBounds(199, 11, 80, 28);
		preciosPanel.add(lPapasNat);
		
		JLabel lPapaspollo = new JLabel("3100");
		lPapaspollo.setHorizontalAlignment(SwingConstants.CENTER);
		lPapaspollo.setBounds(57, 11, 80, 28);
		preciosPanel.add(lPapaspollo);
		
		JLabel lJetp = new JLabel("1500");
		lJetp.setHorizontalAlignment(SwingConstants.CENTER);
		lJetp.setBounds(343, 181, 80, 28);
		preciosPanel.add(lJetp);
		
		JLabel lJetg = new JLabel("5000");
		lJetg.setHorizontalAlignment(SwingConstants.CENTER);
		lJetg.setBounds(199, 181, 80, 28);
		preciosPanel.add(lJetg);
		
		JLabel lDoritos = new JLabel("3000");
		lDoritos.setHorizontalAlignment(SwingConstants.CENTER);
		lDoritos.setBounds(57, 181, 80, 28);
		preciosPanel.add(lDoritos);
		
		JLabel lColombiana = new JLabel("3000");
		lColombiana.setHorizontalAlignment(SwingConstants.CENTER);
		lColombiana.setBounds(342, 355, 80, 28);
		preciosPanel.add(lColombiana);
		
		JLabel lCocacola = new JLabel("3500");
		lCocacola.setHorizontalAlignment(SwingConstants.CENTER);
		lCocacola.setBounds(198, 355, 80, 28);
		preciosPanel.add(lCocacola);
		
		JLabel lChokis = new JLabel("2000");
		lChokis.setHorizontalAlignment(SwingConstants.CENTER);
		lChokis.setBounds(56, 355, 80, 28);
		preciosPanel.add(lChokis);
		borrarSeleccion();

		
		JPanel productosPanel = new JPanel();
		productosPanel.setBackground(UIManager.getColor("Button.darkShadow"));
		productosPanel.setBounds(39, 26, 403, 471);
		productosPanel.setOpaque(false);
		contentPanel.add(productosPanel);
		productosPanel.setLayout(new GridLayout(3, 3, 30, 50));
		
		JLabel papasNatural = new JLabel("");
		papasNatural.setHorizontalAlignment(SwingConstants.CENTER);
		papasNatural.setIcon(new ImageIcon(Comida.class.getResource("/images/pmp.png")));
		productosPanel.add(papasNatural);
		
		JLabel papasPollo = new JLabel("");
		papasPollo.setHorizontalAlignment(SwingConstants.CENTER);
		papasPollo.setIcon(new ImageIcon(Comida.class.getResource("/images/pmn.png")));
		productosPanel.add(papasPollo);
		
		JLabel detoditoFamiliar = new JLabel("");
		detoditoFamiliar.setHorizontalAlignment(SwingConstants.CENTER);
		detoditoFamiliar.setIcon(new ImageIcon(Comida.class.getResource("/images/dtf.png")));
		productosPanel.add(detoditoFamiliar);
		
		JLabel doritos = new JLabel("");
		doritos.setHorizontalAlignment(SwingConstants.CENTER);
		doritos.setIcon(new ImageIcon(Comida.class.getResource("/images/dad.png")));
		productosPanel.add(doritos);
		
		JLabel chocolateG = new JLabel("");
		chocolateG.setHorizontalAlignment(SwingConstants.CENTER);
		chocolateG.setIcon(new ImageIcon(Comida.class.getResource("/images/cjj.png")));
		productosPanel.add(chocolateG);
		
		JLabel chocolateP = new JLabel("");
		chocolateP.setHorizontalAlignment(SwingConstants.CENTER);
		chocolateP.setIcon(new ImageIcon(Comida.class.getResource("/images/cjp.png")));
		productosPanel.add(chocolateP);
		
		JLabel galletasChokis = new JLabel("");
		galletasChokis.setHorizontalAlignment(SwingConstants.CENTER);
		galletasChokis.setIcon(new ImageIcon(Comida.class.getResource("/images/gcn.png")));
		productosPanel.add(galletasChokis);
		
		JLabel cocacola = new JLabel("");
		cocacola.setHorizontalAlignment(SwingConstants.CENTER);
		cocacola.setIcon(new ImageIcon(Comida.class.getResource("/images/ccp.png")));
		productosPanel.add(cocacola);
		
		JLabel colombiana = new JLabel("");
		colombiana.setHorizontalAlignment(SwingConstants.CENTER);
		colombiana.setIcon(new ImageIcon(Comida.class.getResource("/images/cpc.png")));
		productosPanel.add(colombiana);
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
		
		for(int i = 0; i < 9 ; i++) {
			JLabel cantidad = new JLabel("x" + String.valueOf(cantidadItemsInventario[i]));
			cantidad.setVisible(true);
			cantidad.setHorizontalAlignment(SwingConstants.CENTER);
			cantidad.setFont(new Font("Arial", 1, 25));
			cantidad.setForeground(Color.black);
			cantidad.setOpaque(true);
			cantidad.setBackground(new Color(211, 211, 211, 150));
			cantidadesPanel.add(cantidad);
			System.out.println(productos.get(i).getText() + "., "+ cantidadItemsInventario[i] + "; " + productos.get(i).getName());

		}
		
	}
	
	void continuar(Pedido pedido) {
		boolean cantidadSuficiente = true;
		pedido.setEstadoAnteriorSnacks(cantidadItemsInventario);
		//En este arraylist van los productos comprados
		ArrayList<String> productoComprado = new ArrayList<String>();
		
		//Envía los productos comprados y la cantidad por separado por medio de un arrayList
		//Primero se escogen aquellos producto donde se ingresó una cantidad diferente de 0
		for(int i = 0; i < productos.size() ; i++) {

			if(!productos.get(i).getText().equals("")) {
				//System.out.println(Integer.parseInt(productos.get(i).getText()) + "., "+ cantidadItemsInventario[i]);
				if(Integer.parseInt(productos.get(i).getText()) > cantidadItemsInventario[i]) {
					JOptionPane.showMessageDialog(this, "Error. No hay sufiente cantidad de " + productos.get(i).getName());
					cantidadSuficiente = false;
				}else {
					cantidadItemsInventario[i] -= Integer.parseInt(productos.get(i).getText());
					String producto = productos.get(i).getName() + ": " + productos.get(i).getText();
					productoComprado.add(producto);
				}
				
			}
		}
		
		if(cantidadSuficiente) {
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
		
	}
	
	//Setea el total a 0 para volver a ser calculado según los valores ingresados en los campos de texto
	void borrarSeleccion() {
		total = 0;
		for(int i = 0; i < productos.size() ; i++) {
			productos.get(i).setText("");
		}
	}
}
