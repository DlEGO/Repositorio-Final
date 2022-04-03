package cinema;

public class Pelicula {
	
	private String nombre;
	private int duracion;
	private int ano;
	private String genero;
	private String idioma;
	private String caratula;
	
	public Pelicula() {
		
	}
	
	public Pelicula(String nombre, int duracion, int ano, String genero, String idioma, String caratula) {
		
		this.nombre = nombre;
		this.duracion = duracion;
		this.ano = ano;
		this.genero = genero;
		this.idioma = idioma;
		this.caratula = caratula;
		
	}

	public String getNombre() {
		return nombre;
	}

	public int getDuracion() {
		return duracion;
	}

	public int getAno() {
		return ano;
	}

	public String getGenero() {
		return genero;
	}

	public String getIdioma() {
		return idioma;
	}

	public String getCaratula() {
		return caratula;
	}
}
