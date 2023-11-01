package vitornogueira.com.agrotis.resources;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import vitornogueira.com.agrotis.entities.Laboratorio;
import vitornogueira.com.agrotis.entities.Pessoa;
import vitornogueira.com.agrotis.entities.Propriedades;
import vitornogueira.com.agrotis.repositories.LaboratorioRepository;
import vitornogueira.com.agrotis.repositories.PessoaRepository;
import vitornogueira.com.agrotis.repositories.PropriedadesRepository;

@SpringBootTest
@TestMethodOrder(MethodOrderer.DisplayName.class)
public class PessoaResourceTest {

	@Autowired
	PessoaRepository pessoaRepository;

	@Autowired
	PropriedadesRepository propriedadeRepository;

	@Autowired
	LaboratorioRepository laboratorioRepository;

	@DisplayName("1. Criar Propriedades")
	@Test
	void shouldCreateProperty() {
		Propriedades propriedade = new Propriedades();
		propriedade.setNomePropriedade("Teste de Propriedade");
		propriedadeRepository.save(propriedade);
	}

	@DisplayName("2. Criou Laboratório !")
	@Test
	void shouldCreateLaboratory() {
		Laboratorio laboratorio = new Laboratorio();
		laboratorio.setNomeLaboratorio("Teste unitario JUnit");
		laboratorioRepository.save(laboratorio);
	}

	@DisplayName("3. Criou Usuario")
	@Test
	void deveCriarUsuario() {
		Date data = new Date();
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Jon Doe");
		pessoa.setDataInicial(data);
		pessoa.setDataFinal(data);
		pessoa.setObservacoes("Observacao exemplo de teste");
		pessoaRepository.save(pessoa);
	}

	@DisplayName("4. Listou Usuários")
	@Test
	void shouldListPeople() {
		List<Pessoa> list = pessoaRepository.findAll();
		assertThat(list).size().isGreaterThan(0);
	}

	@DisplayName("5. Listou Usuario por Id")
	@Test
	void shouldListPeopleById() {
		Pessoa pessoa = pessoaRepository.findById(1L).get();
		assertEquals("Jon Doe", pessoa.getNome());
	}

	@DisplayName("6. Alterou nome do Usuário")
	@Test
	void shouldUpdatePeople() {
		Pessoa pessoa = pessoaRepository.findById(1L).get();
		pessoa.setNome("Jon Doe 2");
		pessoaRepository.save(pessoa);
		assertEquals("Jon Doe 2", pessoa.getNome());
	}

	@DisplayName("7. Excluiu Usuário")
	@Test
	void shouldDeletePeople() {
		pessoaRepository.deleteById(1L);
		assertThat(pessoaRepository.existsById(1L));
	}
}
