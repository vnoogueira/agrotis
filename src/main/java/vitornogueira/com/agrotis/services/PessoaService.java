package vitornogueira.com.agrotis.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import vitornogueira.com.agrotis.dto.LaboratoriosDto;
import vitornogueira.com.agrotis.dto.PessoaDto;
import vitornogueira.com.agrotis.dto.PropriedadesDto;
import vitornogueira.com.agrotis.entities.Laboratorio;
import vitornogueira.com.agrotis.entities.Pessoa;
import vitornogueira.com.agrotis.entities.Propriedades;
import vitornogueira.com.agrotis.repositories.LaboratorioRepository;
import vitornogueira.com.agrotis.repositories.PessoaRepository;
import vitornogueira.com.agrotis.repositories.PropriedadesRepository;
import vitornogueira.com.agrotis.services.exceptions.ResourceNotFoundException;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private PropriedadesRepository propriedadeRepository;
	
	@Autowired
	private LaboratorioRepository laboratorioRepository;

	/*@Transactional(readOnly = true)
	public Page<PessoaDto> findAll(PageRequest pageRequest) {
		Page<Pessoa> lista = pessoaRepository.findAll(pageRequest);
		return lista.map(x -> new PessoaDto(x));
	}*/
	
	public List<Pessoa> findAllPessoa() {
		return pessoaRepository.findAll();
	}

	@Transactional(readOnly = true)
	public PessoaDto findPessoaById(Long id) {
		Optional<Pessoa> obj = pessoaRepository.findById(id);
		Pessoa pessoa = obj.orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada !"));
		return new PessoaDto(pessoa, pessoa.getPropriedades(), pessoa.getLaboratorio());
	}
	
	@Transactional
	public PessoaDto insertPessoa(PessoaDto objeto) {
		Pessoa entity = new Pessoa();
		copyToDtoEntity(objeto, entity);
		entity = pessoaRepository.save(entity);
		return new PessoaDto(entity);
	}
	
	@Transactional
	public PessoaDto updatePessoa(Long id, PessoaDto objeto) {
		try {
			Pessoa entity = pessoaRepository.getReferenceById(id);
			copyToDtoEntity(objeto, entity);
			return new PessoaDto(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Pessoa não encontrada: " + id);
		}
	}
	
	public void deletePessoa(Long id) {
		try {
			pessoaRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e){
			throw new ResourceNotFoundException("Integridade de dados !");
		} catch (DataIntegrityViolationException d) {
			
		}
	}
	
	private void copyToDtoEntity(PessoaDto objeto, Pessoa entity) {
		entity.setNome(objeto.getNome());
		entity.setDataInicial(objeto.getDataInicial());
		entity.setDataFinal(objeto.getDataFinal());
		entity.setObservacoes(objeto.getObservacoes());
		entity.getPropriedades().clear();
		entity.getLaboratorio().clear();
		
		for (PropriedadesDto proDto : objeto.getPropriedades()) {
			Propriedades propriedades = propriedadeRepository.getReferenceById(proDto.getId());
			entity.getPropriedades().add(propriedades);
		}
		
		for (LaboratoriosDto labDto : objeto.getLaboratorio()) {
			Laboratorio laboratorio = laboratorioRepository.getReferenceById(labDto.getId());
			entity.getLaboratorio().add(laboratorio);
		}
	}

	

}
