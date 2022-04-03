package cinema;

public class Sala  {
	
	private int idSala;
	private String nombrePelicula;
	private String horario;
	//2 - premium, 3 - estándar, 4 - discapacitados, 1 - espacio sin silla
	/*private int[][] disposicionSillas = {{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
										,{1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1}
										,{2,2,2,0,1,1,1,1,1,1,1,1,1,1,1,1,1,0,2,2,2}
										,{2,2,2,0,1,1,1,1,1,1,1,1,1,1,1,1,1,0,2,2,2}
										,{2,2,2,0,2,2,2,2,2,2,2,2,2,2,2,2,2,0,2,2,2}
										,{2,2,2,0,2,2,2,2,2,2,2,2,2,2,2,2,2,0,2,2,2}
										,{2,2,2,0,2,2,2,2,2,2,2,2,2,2,2,2,2,0,2,2,2}
										,{2,2,2,0,2,2,2,2,2,2,2,2,2,2,2,2,2,0,2,2,2}
										,{2,2,2,0,2,2,2,2,2,2,2,2,2,2,2,2,2,0,2,2,2}
	                                    ,{2,2,3,0,3,2,2,2,3,2,2,2,3,2,2,2,3,0,3,2,2}};
	
	private int[][] sillasReservadas =  {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
										,{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
										,{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
										,{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
										,{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
										,{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
										,{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
										,{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
										,{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
										,{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};; */
	
	private int[][] disposicionSillas = {{2,2,2,2,2,2,2,2,2,2}
										,{2,2,2,2,2,2,2,2,2,2}
										,{3,3,3,3,3,3,3,3,3,3}
										,{3,3,3,3,3,3,3,3,3,3}
										,{3,4,3,4,3,3,4,3,4,3}};										

	private int[][] sillasReservadas =  {{1,1,1,1,1,1,1,1,1,1}
										,{1,1,1,1,1,1,1,1,1,1}
										,{1,1,1,1,1,1,1,1,1,1}
										,{1,1,1,1,1,1,1,1,1,1}
										,{1,1,1,1,1,1,1,1,1,1}};
	
	public Sala(int ID, String nombrePelicula, int horario) {
		this.idSala = ID;
		this.nombrePelicula = nombrePelicula;
		this.horario = String.valueOf(horario);
	}

	public int[][] getSillasReservadas() {
		return sillasReservadas;
	}

	public void setSillasReservadas(int x , int y, int valor) {
		this.sillasReservadas[x][y] = valor;
	}

	public String getNombrePelicula() {
		return nombrePelicula;
	}

	public String getHorario() {
		return horario;
	}

	public int[][] getDisposicionSillas() {
		return disposicionSillas;
	}
	public int getIdSala() {
		return idSala;
	}
}
