package vos;

import java.sql.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class Pedido {
	
	////Atributos////
	
	/**
	 * fecha del pedido.
	 */
	@JsonProperty(value="fecha")
	private Date fecha;
	
	/**
	 * hora del pedido.
	 */
	@JsonProperty(value="hora")
	private int hora;
	
	/**
	 * id del cliente que hizo el pedido.
	 */
	@JsonProperty(value="id_cliente")
	private Long id_cliente;
	
	/**
	 * nombre del restaurante al que se le hizo el pedido.
	 */
	@JsonProperty(value="nombre_Rest")
	private String nombre_Rest;
	
	/**
	 * id del producto que se solicito en el pedido.
	 */
	@JsonProperty(value="id_prod")
	private Long id_prod;

	/**
	 * @param fecha
	 * @param hora
	 * @param id_cliente
	 * @param nombre_Rest
	 * @param id_prod
	 */
	public Pedido(@JsonProperty(value="fecha")Date fecha, 
			@JsonProperty(value="hora")int hora,
			@JsonProperty(value="cliente")Long id_cliente, 
			@JsonProperty(value="nombre_Rest")String nombre_Rest,
			@JsonProperty(value="id_prod")Long id_prod) {
		this.fecha = fecha;
		this.hora = hora;
		this.id_cliente = id_cliente;
		this.nombre_Rest = nombre_Rest;
		this.id_prod = id_prod;
	}

	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the hora
	 */
	public int getHora() {
		return hora;
	}

	/**
	 * @param hora the hora to set
	 */
	public void setHora(int hora) {
		this.hora = hora;
	}

	/**
	 * @return the id_cliente
	 */
	public Long getId_cliente() {
		return id_cliente;
	}

	/**
	 * @param id_cliente the id_cliente to set
	 */
	public void setId_cliente(Long id_cliente) {
		this.id_cliente = id_cliente;
	}

	/**
	 * @return the nombre_Rest
	 */
	public String getNombre_Rest() {
		return nombre_Rest;
	}

	/**
	 * @param nombre_Rest the nombre_Rest to set
	 */
	public void setNombre_Rest(String nombre_Rest) {
		this.nombre_Rest = nombre_Rest;
	}

	/**
	 * @return the id_prod
	 */
	public Long getId_prod() {
		return id_prod;
	}

	/**
	 * @param id_prod the id_prod to set
	 */
	public void setId_prod(Long id_prod) {
		this.id_prod = id_prod;
	}
	
	
	
}
