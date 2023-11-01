package vitornogueira.com.agrotis.dto;

import java.io.Serializable;

import vitornogueira.com.agrotis.entities.Propriedades;

public class PropriedadesDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nomePropriedade;

	public PropriedadesDto() {

	}

	public PropriedadesDto(Long id, String nomePropriedade) {
		super();
		this.id = id;
		this.nomePropriedade = nomePropriedade;
	}

	public PropriedadesDto(Propriedades entity) {
		this.id = entity.getId();
		this.nomePropriedade = entity.getNomePropriedade();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomePropriedade() {
		return nomePropriedade;
	}

	public void setNomePropriedade(String nomePropriedade) {
		this.nomePropriedade = nomePropriedade;
	}

}
