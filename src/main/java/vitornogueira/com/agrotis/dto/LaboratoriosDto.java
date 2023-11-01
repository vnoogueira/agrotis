package vitornogueira.com.agrotis.dto;

import java.io.Serializable;

import vitornogueira.com.agrotis.entities.Laboratorio;

public class LaboratoriosDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nomeLaboratorio;

	public LaboratoriosDto() {

	}

	public LaboratoriosDto(Long id, String nomeLaboratorio) {
		super();
		this.id = id;
		this.nomeLaboratorio = nomeLaboratorio;
	}

	public LaboratoriosDto(Laboratorio entity) {
		this.id = entity.getId();
		this.nomeLaboratorio = entity.getNomeLaboratorio();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeLaboratorio() {
		return nomeLaboratorio;
	}

	public void setNomeLaboratorio(String nomeLaboratorio) {
		this.nomeLaboratorio = nomeLaboratorio;
	}

}
