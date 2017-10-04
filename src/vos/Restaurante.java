package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Restaurante {
	
	///Atributos///
	/**
	 * nombre del restaurante
	 */
	@JsonProperty(value="nombre")
	private String nombre;
	
	/**
	 * representante del restaurante.
	 */
	@JsonProperty(value="representante")
	private String representante;
	
	/**
	 * tipo de comida que sirve el restaurante.
	 */
	@JsonProperty(value="tipo")
	private String tipo;
	
	/**
	 * nombre de la zona en la que se encuentra el restaurante.
	 */
	@JsonProperty(value="nombre_zona")
	private int nombre_zona;
	
	/**
	 * 
	 * @param nombre
	 * @param representante
	 * @param tipo
	 * @param nombre_zona
	 */
	public Restaurante(@JsonProperty(value="nombre")String nombre,
			@JsonProperty(value="representante")String representante,
			@JsonProperty(value="tipo")String tipo,
			@JsonProperty(value="nombre_zona")String nombre_zona)
	{
		this.nombre = nombre;
		this.representante = representante;
		this.tipo = tipo;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the representante
	 */
	public String getRepresentante() {
		return representante;
	}

	/**
	 * @param representante the representante to set
	 */
	public void setRepresentante(String representante) {
		this.representante = representante;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the nombre_zona
	 */
	public int getNombre_zona() {
		return nombre_zona;
	}

	/**
	 * @param nombre_zona the nombre_zona to set
	 */
	public void setNombre_zona(int nombre_zona) {
		this.nombre_zona = nombre_zona;
	}


	
}
