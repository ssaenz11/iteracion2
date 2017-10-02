package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Zona {
	
	/**
	 * nombre de la zona
	 */
	@JsonProperty(value="nombre")
	private String nombre;
	
	/**
	 * capacidad de la zona
	 */
	@JsonProperty(value="capacidad")
	private int capacidad;
	
	/**
	 * boolean que indica si la zona está destapada.
	 */
	@JsonProperty(value="destapado")
	private boolean destapado;
	
	/**
	 * boolean que indica si la zona es apropiada para personas con discapacidad.
	 */
	@JsonProperty(value="apropiadoDisc")
	private boolean apropiadoDisc;
	
	/**
	 * descripcion de las condiciones tecnicas que tiene la zona.
	 */
	@JsonProperty(value="condicionesTecnicas")
	private String condicionesTecnicas;

	
	/**
	 * @param nombre
	 * @param capacidad
	 * @param destapado
	 * @param apropiadoDisc
	 * @param condicionesTecnicas
	 */
	public Zona(@JsonProperty(value="nombre")String nombre,
			@JsonProperty(value="capacidad")int capacidad,
			@JsonProperty(value="destapado")boolean destapado,
			@JsonProperty(value="apropiadoDisc")boolean apropiadoDisc,
			@JsonProperty(value="condicionesTecnicas")String condicionesTecnicas)
	{
		this.nombre = nombre;
		this.capacidad = capacidad;
		this.destapado = destapado;
		this.apropiadoDisc = apropiadoDisc;
		this.condicionesTecnicas = condicionesTecnicas;
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
	 * @return the capacidad
	 */
	public int getCapacidad() {
		return capacidad;
	}

	/**
	 * @param capacidad the capacidad to set
	 */
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	/**
	 * @return the destapado
	 */
	public boolean isDestapado() {
		return destapado;
	}

	/**
	 * @param destapado the destapado to set
	 */
	public void setDestapado(boolean destapado) {
		this.destapado = destapado;
	}

	/**
	 * @return the apropiadoDisc
	 */
	public boolean isApropiadoDisc() {
		return apropiadoDisc;
	}

	/**
	 * @param apropiadoDisc the apropiadoDisc to set
	 */
	public void setApropiadoDisc(boolean apropiadoDisc) {
		this.apropiadoDisc = apropiadoDisc;
	}

	/**
	 * @return the condicionesTecnicas
	 */
	public String getCondicionesTecnicas() {
		return condicionesTecnicas;
	}

	/**
	 * @param condicionesTecnicas the condicionesTecnicas to set
	 */
	public void setCondicionesTecnicas(String condicionesTecnicas) {
		this.condicionesTecnicas = condicionesTecnicas;
	}
	
	
}
