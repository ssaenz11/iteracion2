package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class ServicioProducto {
	
////Atributos

	/**
	 * Id del producto
	 */
	@JsonProperty(value="idCliente")
	private int  idCliente;

	/**
	 * id_restaurante del producto
	 */
	@JsonProperty(value="idPedido")
	private Long idPedido;
	
	
	public ServicioProducto(@JsonProperty(value="idCliente")int id,
			@JsonProperty(value="idPedido")Long idPedido)
	{
		this.idCliente = id;
		this.idPedido = idPedido;
	}


	public int getIdCliente() {
		return idCliente;
	}


	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}


	public Long getIdPedido() {
		return idPedido;
	}


	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}
	

}
