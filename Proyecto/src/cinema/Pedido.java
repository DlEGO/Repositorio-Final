package cinema;

import java.util.ArrayList;

public class Pedido {

	private String nombre;
	private String ID;
	private String pelicula;
	private String horaPelicula;
	private int sillasNormales = 0;
	private int sillasVIP = 0;
	private int sillasPref = 0;
	private int sala;
	private int totalSillas = 0;
	private String ubicacionSillas;
	private String estadoAnteriorSillas;
	private ArrayList<String> snacks = new ArrayList<String>();
	private int[] estadoAnteriorSnacks = new int[9];
	private int totalSnacks = 0;

	
	public Pedido(String nombre, String ID) {
		this.nombre = nombre;
		this.ID = ID;
	}
	
	//getters & setters
	public String getPelicula() {
		return pelicula;
	}
	public void setPelicula(String pelicula) {
		this.pelicula = pelicula;
	}
	public int getSillasNormales() {
		return sillasNormales;
	}
	public void setSillasNormales(int sillasNormales) {
		this.sillasNormales = sillasNormales;
	}
	public int getSillasVIP() {
		return sillasVIP;
	}
	public void setSillasVIP(int sillasVIP) {
		this.sillasVIP = sillasVIP;
	}
	public int getSillasPref() {
		return sillasPref;
	}
	public void setSillasPref(int sillasPref) {
		this.sillasPref = sillasPref;
	}
	public ArrayList<String> getSnacks() {
		return snacks;
	}
	public void setSnacks(ArrayList<String> snacks) {
		this.snacks = snacks;
	}
	public String getNombre() {
		return nombre;
	}
	public String getID() {
		return ID;
	}
	public String getHoraPelicula() {
		return horaPelicula;
	}
	public void setHoraPelicula(String horaPelicula) {
		this.horaPelicula = horaPelicula;
	}
	public String getUbicacionSillas() {
		return ubicacionSillas;
	}
	public void setUbicacionSillas(String ubicacionSillas) {
		this.ubicacionSillas = ubicacionSillas;
	}
	public int getTotalSnacks() {
		return totalSnacks;
	}
	public void setTotalSnacks(int totalSnacks) {
		this.totalSnacks = totalSnacks;
	}
	public int getTotalSillas() {
		return totalSillas;
	}
	public void setTotalSillas(int totalSillas) {
		this.totalSillas = totalSillas;
	}
	public String getEstadoAnteriorSillas() {
		return estadoAnteriorSillas;
	}
	public void setEstadoAnteriorSillas(String estadoSillas) {
		this.estadoAnteriorSillas = estadoSillas;
	}

	public int[] getEstadoAnteriorSnacks() {
		return estadoAnteriorSnacks;
	}

	public void setEstadoAnteriorSnacks(int[] estadoAnteriorSnacks) {
		this.estadoAnteriorSnacks = estadoAnteriorSnacks;
	}

	public int getSala() {
		return sala;
	}

	public void setSala(int sala) {
		this.sala = sala;
	}
	
	
	
	
}
