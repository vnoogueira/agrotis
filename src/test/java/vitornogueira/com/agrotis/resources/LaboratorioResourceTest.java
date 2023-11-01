package vitornogueira.com.agrotis.resources;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import vitornogueira.com.agrotis.entities.Laboratorio;
import vitornogueira.com.agrotis.repositories.LaboratorioRepository;

@SpringBootTest
@TestMethodOrder(MethodOrderer.DisplayName.class)
@DisplayName("Teste de unidade para o package Resource de laboratorios")
public class LaboratorioResourceTest {

	@Autowired
	LaboratorioRepository laboratorioRepository;

	@DisplayName("1. Criou Laboratório !")
	@Test
	void shouldCreateLaboratory() {
		Laboratorio laboratorio = new Laboratorio();
		laboratorio.setNomeLaboratorio("Teste unitario JUnit");
		laboratorioRepository.save(laboratorio);
	}

	@DisplayName("2. Listou Laboratórios !")
	@Test
	void shouldListLaboratory() {
		List<Laboratorio> list = laboratorioRepository.findAll();
		assertThat(list).size().isGreaterThan(0);
	}

	@DisplayName("3. Listou Laboratório por Id")
	@Test
	void shouldListLaboratoryById() {
		Laboratorio laboratorio = laboratorioRepository.findById(1L).get();
		assertEquals("Laboratorio Teste", laboratorio.getNomeLaboratorio());
	}

	@DisplayName("4. Alterou nome do Laboratório")
	@Test
	void shouldUpdateLaboratoryName() {
		Laboratorio laboratorio = laboratorioRepository.findById(1L).get();
		laboratorio.setNomeLaboratorio("Teste de Laboratório 2");
		laboratorioRepository.save(laboratorio);
		assertEquals("Teste de Laboratório 2", laboratorio.getNomeLaboratorio());
	}

	@DisplayName("5. Excluiu Laboratório")
	@Test
	void shouldDeleteLaboratory() {
		laboratorioRepository.deleteById(1L);
		assertThat(laboratorioRepository.existsById(1L));
	}

}
