package vitornogueira.com.agrotis.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import vitornogueira.com.agrotis.entities.Laboratorio;
import vitornogueira.com.agrotis.entities.Pessoa;
import vitornogueira.com.agrotis.entities.Propriedades;

public class PessoaDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private Date dataInicial;
	private Date dataFinal;
	private String observacoes;

	private List<PropriedadesDto> propriedades = new ArrayList<>();
	private List<LaboratoriosDto> laboratorio = new ArrayList<>();

	public PessoaDto() {

	}

	public PessoaDto(Long id, String nome, Date dataInicial, Date dataFinal, String observacoes) {
		super();
		this.id = id;
		this.setNome(nome);
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
		this.observacoes = observacoes;
	}

	public PessoaDto(Pessoa entity) {
		this.id = entity.getId();
		this.setNome(entity.getNome());
		this.dataInicial = entity.getDataInicial();
		this.dataFinal = entity.getDataFinal();
		this.observacoes = entity.getObservacoes();
	}

	public PessoaDto(Pessoa entity, Set<Propriedades> propriedades, Set<Laboratorio> laboratorios) {
		this(entity);
		propriedades.forEach(propriedade -> this.propriedades.add(new PropriedadesDto(propriedade)));
		laboratorios.forEach(laboratorio -> this.laboratorio.add(new LaboratoriosDto(laboratorio)));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public List<PropriedadesDto> getPropriedades() {
		return propriedades;
	}

	public void setPropriedades(List<PropriedadesDto> propriedades) {
		this.propriedades = propriedades;
	}
	
	public List<LaboratoriosDto> getLaboratorio() {
		return laboratorio;
	}

	public void setLaboratorios(List<LaboratoriosDto> laboratorio) {
		this.laboratorio = laboratorio;
	}

}
