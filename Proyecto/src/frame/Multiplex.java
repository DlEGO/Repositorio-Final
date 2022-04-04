package frame;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cinema.Pedido;
import cinema.Pelicula;
import cinema.Sala;
import conexion.Conexion;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Insets;

public class Multiplex extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private ArrayList<Pelicula> peliculas = new ArrayList<Pelicula>();
	private ArrayList<JButton> peliculasButtons = new ArrayList<JButton>();
	private ArrayList<JLabel> caratulasLabels = new ArrayList<JLabel>();
	private Sala[][] salas;
	public int precio = 0;
	public int salaX = 0, salaY = 0;
	//Matriz sillas es la matriz de botones, donde se asignan en la misma posición según como se ve en la distribución al ejecutar
	//la app
	private JButton sillas[][] = new JButton[5][10];
	private String[][] nombrePosicionSilla = {{"A0","A1","A2","A3","A4","A5","A6","A7","A8","A9"}
										 ,{"B0","B1","B2","B3","B4","B5","B6","B7","B8","B9"}
										 ,{"C0","C1","C2","C3","C4","C5","C5","C6","C8","C9"}
										 ,{"D0","D1","D2","D3","D4","D5","D6","D7","D8","D9"}
										 ,{"E0","E1","E2","E3","E4","E5","E6","E7","E8","E9"}};

	public static void main(String[] args) {
		try {
			Multiplex dialog = new Multiplex(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Multiplex(Pedido pedido) throws SQLException {
				
		crearSalas();
			
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 1049, 454);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel peliculasPanel = new JPanel();
		peliculasPanel.setBounds(10, 39, 151, 375);
		contentPanel.add(peliculasPanel);
		peliculasPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btn0 = new JButton("btn1");
		peliculasPanel.add(btn0);
		
		JButton btn1 = new JButton("New button");
		peliculasPanel.add(btn1);
		
		JButton btn2 = new JButton("New button");
		peliculasPanel.add(btn2);
		
		JButton btn3 = new JButton("New button");
		peliculasPanel.add(btn3);
		
		JButton btn4 = new JButton("New button");
		peliculasPanel.add(btn4);
		
		JButton btn5 = new JButton("New button");
		peliculasPanel.add(btn5);

		JButton btn6 = new JButton("New button");
		peliculasPanel.add(btn6);

		JPanel caratulasPanel = new JPanel();
		caratulasPanel.setBounds(171, 39, 37, 375);
		contentPanel.add(caratulasPanel);
		caratulasPanel.setLayout(null);
		
		JLabel caratula1 = new JLabel("New label");
		caratula1.setBounds(0, 2, 37, 53);
		caratulasPanel.add(caratula1);
		
		JLabel caratula2 = new JLabel("New label");
		caratula2.setBounds(0, 55, 37, 53);
		caratulasPanel.add(caratula2);
		
		JLabel caratula3 = new JLabel("New label");
		caratula3.setBounds(0, 108, 37, 53);
		caratulasPanel.add(caratula3);
		
		JLabel caratula4 = new JLabel("New label");
		caratula4.setBounds(0, 161, 37, 53);
		caratulasPanel.add(caratula4);
		
		JLabel caratula5 = new JLabel("New label");
		caratula5.setBounds(0, 214, 37, 53);
		caratulasPanel.add(caratula5);
		
		JLabel caratula6 = new JLabel("New label");
		caratula6.setBounds(0, 267, 37, 53);
		caratulasPanel.add(caratula6);
		
		JLabel caratula7 = new JLabel("New label");
		caratula7.setBounds(0, 320, 37, 53);
		caratulasPanel.add(caratula7);
		
		JPanel salasPanel = new JPanel();
		salasPanel.setBounds(218, 39, 171, 166);
		contentPanel.add(salasPanel);
		salasPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel sillasPanel = new JPanel();
		sillasPanel.setBounds(433, 64, 565, 237);
		contentPanel.add(sillasPanel);
		sillasPanel.setLayout(new GridLayout(5, 10, 5, 15));
		
		//-----------------------------------------------------
		//Instancia los botones, los añade a un arreglo de botones y según el botón al que se le de clic
		//ambia el estado en las sillas disponibles de la sala seleccionada, aumenta el precio según el tipo de silla
		//Y lo cambia de color para tener feedback de que se está escogiendo esa silla y lo deshabilita para que no se le
		//de clic más veces
		for(int i = 0; i < 5 ;i++) {
			for(int j = 0 ; j < 10 ; j++) {
				JButton button = new JButton();
				button.setBackground(Color.GREEN);
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						button.setBackground(Color.ORANGE);
							
						JButton aux = (JButton) e.getSource();
						for(int i = 0; i < 5 ;i++) {
							for(int j = 0 ; j < 10 ; j++) {
								if(aux == sillas[i][j]) {
									//Compara la matriz de distribución donde dice cuales sillas son de qué tipo y va sumando el precio
									if(salas[salaX - 1][salaY - 1].getDisposicionSillas()[i][j] == 2) {												
										salas[salaX - 1][salaY - 1].setSillasReservadas(i, j, 6);
										precio += 15000;
										pedido.setSillasVIP(pedido.getSillasVIP() + 1);
									}else if(salas[salaX - 1][salaY - 1].getDisposicionSillas()[i][j] == 3) {
										salas[salaX - 1][salaY - 1].setSillasReservadas(i, j, 7);
										precio += 10000;
										pedido.setSillasNormales(pedido.getSillasNormales() + 1);

									}else if(salas[salaX - 1][salaY - 1].getDisposicionSillas()[i][j] == 4) {
										salas[salaX - 1][salaY - 1].setSillasReservadas(i, j, 8);
										precio += 6000;
										pedido.setSillasPref(pedido.getSillasPref() + 1);
									}
									sillas[i][j].setEnabled(false);
									pedido.setTotalSillas(precio);
								}
							}
									
						}
								
					}
				});
				
				//Añade el boton a la matriz después de instanciarlo
				sillas[i][j] = button;
				//Los añade todos a un panel para que se vean en cuadrícula bien melo
				sillasPanel.add(button);
				
				if(salaX != 0 && salaY != 0) {
					if(salas[salaX - 1][salaY - 1].getSillasReservadas()[i][j] == 5) {
						sillas[i][j].setEnabled(false);
						sillas[i][j].setBackground(Color.red);
					}
				}
			}
		}
		//-----------------------------------------------------		
			
		JButton borrarBtn = new JButton("Borrar Selecci\u00F3n");
		borrarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Crea un estado provisional de las sillas reservadas para poder revertir el cambio y poder hacer una nueva selección
				//Los botones que se pusieron en estado provisional vuelven a ser disponibles para hacer una nueva selección de sillas
				for(int i = 0; i < 5 ;i++) {
					for(int j = 0 ; j < 10 ; j++) {
						precio = 0;
						//el estado provisional es de 6, para sillas VIP, 7 para normales y 8 para preferenciales
						if(salas[salaX - 1][salaY - 1].getSillasReservadas()[i][j] == 6 || salas[salaX - 1][salaY - 1].getSillasReservadas()[i][j] == 7 || salas[salaX - 1][salaY - 1].getSillasReservadas()[i][j] == 8) {
							salas[salaX - 1][salaY - 1].setSillasReservadas(i, j, 1);
							//Activa los botones y lo vuelve verdes para er que están disponibles esas sillas nuevamente
							sillas[i][j].setEnabled(true);
							sillas[i][j].setBackground(Color.GREEN);
							pedido.setSillasNormales(0);
							pedido.setSillasVIP(0);
							pedido.setSillasPref(0);
						}
					}
					
				}
			}
		});
		borrarBtn.setBounds(784, 364, 122, 23);
		contentPanel.add(borrarBtn);
		
		//------------------------------------------------------------------
		//Tiene un evento al hacerle clic.
		//Recorre las sillas reservadas para pasarlas del estado provisional al estado definitivo y al string ubicacion le va
		//concatenando las posiciones de dichas sillas
		//Después de cambiar al estado definitivo crea la cadena final para ser enviada a la base de datos
		JButton continuarBtn = new JButton("Continuar");
		continuarBtn.addActionListener(new ActionListener() {
			//Crea un string distribucion donde coge el array de sillas reservadas de cierta sala y los concatena para tenerlo en cadena de texto
			//Y luego hacer el update en la base de datos
			public void actionPerformed(ActionEvent e) {
				try {
					continuar(pedido);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		continuarBtn.setBounds(909, 364, 89, 23);
		contentPanel.add(continuarBtn);
		//---------------------------------------------------------------------
		
		
		
		JButton horario1 = new JButton("Sala");
		horario1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salaY = 1;							
				try {
					cargarDistribucion(salas[salaX - 1][salaY - 1].getIdSala());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				actualizarSillas();
				
			}
		});
		salasPanel.add(horario1);
		
		JButton horario2 = new JButton("Sala");
		horario2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salaY = 2;							
				try {
					cargarDistribucion(salas[salaX - 1][salaY - 1].getIdSala());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				actualizarSillas();
			}
		});
		salasPanel.add(horario2);
		
		JButton horario3 = new JButton("Sala");
		horario3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salaY = 3;							
				try {
					cargarDistribucion(salas[salaX - 1][salaY - 1].getIdSala());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				actualizarSillas()	;				
			}
			
		});
		salasPanel.add(horario3);
		
		JPanel numerosPanel = new JPanel();
		numerosPanel.setBackground(Color.LIGHT_GRAY);
		numerosPanel.setBounds(433, 39, 565, 22);
		contentPanel.add(numerosPanel);
		numerosPanel.setLayout(new GridLayout(1, 10, 5, 0));
		
		JLabel lblNewLabel = new JLabel("0");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		numerosPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("1");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		numerosPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("2");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		numerosPanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("3");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		numerosPanel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("4");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		numerosPanel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("5");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		numerosPanel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_7 = new JLabel("6");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		numerosPanel.add(lblNewLabel_7);
		
		JLabel lblNewLabel_6 = new JLabel("7");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		numerosPanel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_8 = new JLabel("8");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		numerosPanel.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("9");
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		numerosPanel.add(lblNewLabel_9);
		
		JPanel letrasPanel = new JPanel();
		letrasPanel.setBackground(Color.LIGHT_GRAY);
		letrasPanel.setBounds(409, 64, 17, 237);
		contentPanel.add(letrasPanel);
		letrasPanel.setLayout(new GridLayout(0, 1, 10, 15));
		
		JLabel lblNewLabel_10 = new JLabel("A");
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.CENTER);
		letrasPanel.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("B");
		lblNewLabel_11.setHorizontalAlignment(SwingConstants.CENTER);
		letrasPanel.add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel("C");
		lblNewLabel_12.setHorizontalAlignment(SwingConstants.CENTER);
		letrasPanel.add(lblNewLabel_12);
		
		JLabel lblNewLabel_13 = new JLabel("D");
		lblNewLabel_13.setHorizontalAlignment(SwingConstants.CENTER);
		letrasPanel.add(lblNewLabel_13);
		
		JLabel lblNewLabel_14 = new JLabel("E");
		lblNewLabel_14.setHorizontalAlignment(SwingConstants.CENTER);
		letrasPanel.add(lblNewLabel_14);
		
		//-------------------------------------------------------
		int i = 0;
		//Añade los botones de las películas a un arraylist y los label de las caratulas a otro
		for(i = 0; i < peliculasPanel.getComponentCount() ; i++) {
			peliculasButtons.add((JButton) peliculasPanel.getComponent(i));
			caratulasLabels.add((JLabel) caratulasPanel.getComponent(i));
		}
		
		//Asigna la caratula al label que está a la izquierda de cada película según la película
		for(i = 0; i < peliculas.size() ; i++) {
			peliculasButtons.get(i).setText(peliculas.get(i).getNombre());
			asignarCaratula(peliculas.get(i).getCaratula(), i);
			peliculasButtons.get(i).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JButton aux = (JButton) e.getSource();
					for(int i = 0 ; i < peliculas.size() ; i++) {
						
						if(aux.getText() == peliculas.get(i).getNombre()) {
							salaX = i+1;
							horario1.setText("Horario 1 - Sala 1: " + salas[i][0].getHorario());
							horario2.setText("Horario 2 - Sala 2: " + salas[i][1].getHorario());
							horario3.setText("Horario 3 - Sala 3: " + salas[i][2].getHorario());
						}
					}
				}
			});
		}
		
		//mira la longitud de las peliculas y desactiva el resto de botones y labels para que no quede feito c:
		for(i = peliculas.size(); i < peliculasButtons.size() ; i++) {
			peliculasButtons.get(i).setVisible(false);
			caratulasLabels.get(i).setVisible(false);
		}
		//--------------------------------------------------------
		
	}
	
	private void continuar(Pedido pedido) throws SQLException {
		String distribucion = "";
		String ubicacion = "";
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 10 ; j++) {
				if(salas[salaX - 1][salaY - 1].getSillasReservadas()[i][j] != 5 && salas[salaX - 1][salaY - 1].getSillasReservadas()[i][j] != 1) {
					salas[salaX - 1][salaY - 1].setSillasReservadas(i, j, 5);
					ubicacion = ubicacion + nombrePosicionSilla[i][j] + " ";

				}
				distribucion = distribucion + String.valueOf(salas[salaX - 1][salaY - 1].getSillasReservadas()[i][j]);
			}						
		}
		
		///----------
		
		//Primero se guarda la distribución actual, con las sillas reservadas en una sentencia para ejecutar el update en la BD
		//Esto porque hay un plazo de tiempo para que se ejecute el pago, si no se hace el pago, la BD vuelve al estado anterior
		//Entonces, se hace el update con el estado anterior guardado en el pedido. Si se cancela el pago, se vuelve a actualizar
		//la BD, pero esta vez con el estado anterior para que las sillas queden disponibles nuevamente. Si el pago se realiza
		//lo único que se hace es actualizar los ingresos del cinema en la BD.
		
		//obtiene las sillas reservadas de la BD y lo guarda en forma de "estado anterior"
		String ssql1 = "SELECT * FROM reservas WHERE idsala="+salas[salaX - 1][salaY - 1].getIdSala()+"";
		String estadoAnterior = Conexion.getSingleton().cargarDatos(ssql1, "fila");
		
		//Primero hace la consulta y luego el update por obvias razones
		String ssql = "UPDATE reservas set fila=('"+distribucion+"') WHERE idsala="+salas[salaX - 1][salaY - 1].getIdSala()+"";
		Conexion.getSingleton().actualizarDatos(ssql);
		pedido.setEstadoAnteriorSillas(estadoAnterior);
		pedido.setSala(salas[salaX - 1][salaY - 1].getIdSala());

		///----------
		//Setea los parámetros del pedido
		pedido.setUbicacionSillas(ubicacion);
		pedido.setPelicula(salas[salaX - 1][salaY - 1].getNombrePelicula());
		pedido.setHoraPelicula(salas[salaX - 1][salaY - 1].getHorario());
		
		//Y lo envía al siguiente paso, que es escoger la comida
		Snacks comida = null;		
		try {
			comida = new Snacks(pedido);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		comida.setVisible(true);

		
		actualizarSillas();
	}
	
	//Recorre el arreglo de peliculas y para cada película crea 3 salas y le asigna un horario partiendo de las 12 del día
	private void crearSalas() throws SQLException {
		//Carga las peliculas de la base de datos al arraylist
		peliculas = Conexion.getSingleton().cargarPeliculas();
		//Parar cada película, genera 3 salas, donde películas.size(), es la cantidad de peliculas
		//y las salas las añade a una matriz, aquí solamente inicializa la matriz.
				salas = new Sala[peliculas.size()][3];
		for(int i = 0; i < peliculas.size() ; i++) {
			int horario = 0;
			for(int j = 0 ; j < 3 ; j++) {
				
				if(j == 0) {
					horario = 1200;
				}else {
					horario += peliculas.get(i).getDuracion() + 30;
				}
				salas[i][j] = new Sala((j + 1) + (i*3), peliculas.get(i).getNombre(), horario);
			}
		}		
	}

	//Convierte la imagen obtenida desde una url en la base de datos a un ícono para poder asignarlo al label
	private void asignarCaratula(String _url, int labelIndex) {
		Image image;
		try {
			URL url = new URL(_url);
			image = ImageIO.read(url).getScaledInstance(caratulasLabels.get(labelIndex).getWidth(), caratulasLabels.get(labelIndex).getHeight(), Image.SCALE_DEFAULT);
			caratulasLabels.get(labelIndex).setIcon(new ImageIcon(image));
		}catch(MalformedURLException e) {
		}catch(IOException ex) {			
		}
	}
	
	//Carga la distribución de las sillas desde la base de datos para cada sala teniendo en cuenta el índice de la sala, dado por la película
	private void cargarDistribucion(int index) throws SQLException {
		//obtiene la cadena con las sillas reservadas
		String ssql = "SELECT * FROM reservas WHERE idsala="+index+"";
		
		System.out.println(Conexion.getSingleton().cargarDatos(ssql, "fila"));
		//Obtiene las sillas reservadas
		String reserva = Conexion.getSingleton().cargarDatos(ssql, "fila");
		//Separa la cadena y la convierte en array
		char[] separar = new char[50];
		separar = reserva.toCharArray();
		//Por último la convierte en una matriz para ser usada nuevamente
		for(int i = 0 ; i < 5 ; i++) {
			for(int j = 0; j < 10; j++) {
				switch(i) {
					case 0:
						salas[salaX - 1][salaY - 1].setSillasReservadas(0, j, Character.getNumericValue(separar[j]));
						break;						
					case 1:
						salas[salaX - 1][salaY - 1].setSillasReservadas(1, j, Character.getNumericValue(separar[j+10]));
						break;
					case 2:
						salas[salaX - 1][salaY - 1].setSillasReservadas(2, j, Character.getNumericValue(separar[j+20]));
						break;
					case 3:
						salas[salaX - 1][salaY - 1].setSillasReservadas(3, j, Character.getNumericValue(separar[j+30]));
						break;
					case 4:
						salas[salaX - 1][salaY - 1].setSillasReservadas(4, j, Character.getNumericValue(separar[j+40]));
						break;
				}
			}
		}
	}
		
	//Cambia el color de la silla y la activa y desactiva según la elección
	private void actualizarSillas() {
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 10 ; j++) {
				if(salaX != 0 && salaY != 0) {
					if(salas[salaX - 1][salaY - 1].getSillasReservadas()[i][j] == 5) {
						sillas[i][j].setEnabled(false);
						sillas[i][j].setBackground(Color.red);
					}else {
						sillas[i][j].setEnabled(true);
						sillas[i][j].setBackground(Color.GREEN);
					}
				}
			}						
		}
	}
}
