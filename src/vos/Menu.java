package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Menu
{


	//// Atributos

	/**
	 * Id del menú
	 */
	@JsonProperty(value="id")
	private Long id;

	/**
	 * id_entrada del menú
	 */
	@JsonProperty(value="id_entrada")
	private String id_entrada;

	/**
	 * id_postre del menú
	 */
	@JsonProperty(value="id_postre")
	private String id_postre;

	/**
	 * id_bebida del menú
	 */
	@JsonProperty(value="id_bebida")
	private String id_bebida;


	/**
	 * id_plato_fuerte del menú
	 */
	@JsonProperty(value="id_plato_fuerte")
	private String id_plato_fuerte;

	/**
	 * valor del menú
	 */
	@JsonProperty(value="valor")
	private double valor;

	public Menu(@JsonProperty(value="id")Long id,
			@JsonProperty(value="id_bebida")String id_bebida,
			@JsonProperty(value="id_entrada")String id_entrada,
			@JsonProperty(value="id_plato_fuerte")String id_plato_fuerte,
			@JsonProperty(value="id_postre")String id_postre,
			@JsonProperty(value="valor")double valor)
	{
		this.id = id;
		this.id_bebida = id_bebida;
		this.id_entrada = id_entrada;
		this.id_plato_fuerte = id_plato_fuerte;
		this.id_postre = id_postre;
		this.valor = valor;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getId_entrada() {
		return id_entrada;
	}

	public void setId_entrada(String id_entrada) {
		this.id_entrada = id_entrada;
	}

	public String getId_postre() {
		return id_postre;
	}

	public void setId_postre(String id_postre) {
		this.id_postre = id_postre;
	}

	public String getId_bebida() {
		return id_bebida;
	}

	public void setId_bebida(String id_bebida) {
		this.id_bebida = id_bebida;
	}

	public String getId_plato_fuerte() {
		return id_plato_fuerte;
	}

	public void setId_plato_fuerte(String id_plato_fuerte) {
		this.id_plato_fuerte = id_plato_fuerte;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}


}
