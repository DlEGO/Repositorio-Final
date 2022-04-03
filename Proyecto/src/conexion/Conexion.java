package conexion;

import java.sql.*;
import java.util.ArrayList;

import cinema.Pelicula;

public class Conexion {

	
	static private Conexion conex = null;
	 
	//singleton
    static public Conexion getSingleton() throws SQLException {

        if (conex == null) {
            conex = new Conexion();
        }
        return conex;
    }

    public String metodo() {
        return "Singleton instanciado bajo demanda";
    }
	
	public String url = "jdbc:mysql://localhost:3306/proyecto";
	public String usuario = "root";
	public String contrasena = ""; 
	
	public Statement sql = null;
	public Connection conn = null;
	public ResultSet rs = null;
	
	public Conexion() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection(url, usuario, contrasena);
			if(conn != null) {
				sql = conn.createStatement();						
			}
			
			
		}catch(ClassNotFoundException e) {
			System.out.println("Class Not Found Exception");

		}catch(SQLException e) {
			System.out.println("SQL Exception");
		}
	}
	
	public ArrayList<Pelicula> cargarPeliculas() {
		ArrayList<Pelicula> peliculas = new ArrayList<Pelicula>();
		try {
			rs = sql.executeQuery("SELECT * FROM peliculas");
			boolean r = rs.next();
			int i = 0;
			while (r) {
				peliculas.add(new Pelicula(rs.getString("nombre")
						, Integer.parseInt(rs.getString("duracion"))
						, Integer.parseInt(rs.getString("ano"))
						, rs.getString("genero")
						, rs.getString("idioma")
						, rs.getString("url")));
			
				r=rs.next();
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return peliculas;
		
	}
	
	public String cargarDatos(String ssql, String nombreColumna) {
		String resultado = "";
		try {
			rs = sql.executeQuery(ssql);
			boolean r = rs.next();
			int i = 0;
			while (r) {
				resultado = rs.getString(nombreColumna);
			
				r=rs.next();
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
		
	}
	
	public void actualizarDatos(String ssql) throws SQLException {
		
		sql.executeUpdate(ssql);
		
		
	}
	
	public boolean validarCredenciales(String ssql) {
		int resultado = 0;
		boolean access = false;
		try {
			rs = sql.executeQuery(ssql);
			if(rs.next()) {
				resultado = 1;
				if(resultado == 1) {
					access = true;
				}else {
					access = false;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return access;
	}
	
	public Connection getConection() {
		return conn; 
	}
	
	public void desconectar() throws SQLException {
		conn.close();
		System.out.println("CERRADA LA CONEXION");
	}

}
