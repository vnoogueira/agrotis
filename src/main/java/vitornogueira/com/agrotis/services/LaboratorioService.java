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
import vitornogueira.com.agrotis.entities.Laboratorio;
import vitornogueira.com.agrotis.repositories.LaboratorioRepository;
import vitornogueira.com.agrotis.services.exceptions.ResourceNotFoundException;

@Service
public class LaboratorioService {
	
	@Autowired
	private LaboratorioRepository laboratorioRepository;

	@Transactional(readOnly = true)
	public List<Laboratorio> findAll() {
		return laboratorioRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public LaboratoriosDto findLaboratorioById(Long id) {
		Optional<Laboratorio> obj = laboratorioRepository.findById(id);
		Laboratorio laboratorio = obj
				.orElseThrow(() -> new ResourceNotFoundException("Laboratorio não encontrada !"));
		return new LaboratoriosDto(laboratorio);
	}

	@Transactional
	public LaboratoriosDto insertLaboratorio(LaboratoriosDto objeto) {
		Laboratorio entity = new Laboratorio();
		copyToDtoEntity(objeto, entity);
		entity = laboratorioRepository.save(entity);
		return new LaboratoriosDto(entity);
	}

	@Transactional
	public LaboratoriosDto updateLaboratorio(Long id, LaboratoriosDto objeto) {
		try {
			Laboratorio entity = laboratorioRepository.getReferenceById(id);
			copyToDtoEntity(objeto, entity);
			return new LaboratoriosDto(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Laboratorio não encontrada: " + id);
		}
	}

	public void deleteLaboratorio(Long id) {
		try {
			laboratorioRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Integridade de dados !");
		} catch (DataIntegrityViolationException d) {

		}
	}

	private void copyToDtoEntity(LaboratoriosDto objeto, Laboratorio entity) {
		entity.setNomeLaboratorio(objeto.getNomeLaboratorio());
	}

}
