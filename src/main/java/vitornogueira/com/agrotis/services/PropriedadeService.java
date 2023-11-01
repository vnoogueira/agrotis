package vitornogueira.com.agrotis.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import vitornogueira.com.agrotis.dto.PropriedadesDto;
import vitornogueira.com.agrotis.entities.Propriedades;
import vitornogueira.com.agrotis.repositories.PropriedadesRepository;
import vitornogueira.com.agrotis.services.exceptions.ResourceNotFoundException;

@Service
public class PropriedadeService {

	@Autowired
	private PropriedadesRepository propriedadeRepository;

	@Transactional(readOnly = true)
	public List<Propriedades> findAll() {
		return propriedadeRepository.findAll();
	}

	@Transactional(readOnly = true)
	public PropriedadesDto findPropriedadesById(Long id) {
		Optional<Propriedades> obj = propriedadeRepository.findById(id);
		Propriedades propriedades = obj
				.orElseThrow(() -> new ResourceNotFoundException("Propriedade não encontrada !"));
		return new PropriedadesDto(propriedades);
	}

	@Transactional
	public PropriedadesDto insertPropriedade(PropriedadesDto objeto) {
		Propriedades entity = new Propriedades();
		copyToDtoEntity(objeto, entity);
		entity = propriedadeRepository.save(entity);
		return new PropriedadesDto(entity);
	}

	@Transactional
	public PropriedadesDto updatePropriedade(Long id, PropriedadesDto objeto) {
		try {
			Propriedades entity = propriedadeRepository.getReferenceById(id);
			copyToDtoEntity(objeto, entity);
			return new PropriedadesDto(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Propriedade não encontrada: " + id);
		}
	}

	public void deletePropriedade(Long id) {
		try {
			propriedadeRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Integridade de dados !");
		} catch (DataIntegrityViolationException d) {

		}
	}

	private void copyToDtoEntity(PropriedadesDto objeto, Propriedades entity) {
		entity.setNomePropriedade(objeto.getNomePropriedade());
	}
}
