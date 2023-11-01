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

import vitornogueira.com.agrotis.entities.Propriedades;
import vitornogueira.com.agrotis.repositories.PropriedadesRepository;

@SpringBootTest
@TestMethodOrder(MethodOrderer.DisplayName.class)
public class PropriedadeResourceTest {
	
	@Autowired
    PropriedadesRepository propriedadeRepository;

    @DisplayName("1. Criar Propriedades")
    @Test
    void shouldCreateProperty(){
        Propriedades propriedade = new Propriedades();
        propriedade.setNomePropriedade("Teste de Propriedade");
        propriedadeRepository.save(propriedade);
    }

    @DisplayName("2. Listar Propriedades")
    @Test
    void shouldListProperty(){
        List<Propriedades> list = propriedadeRepository.findAll();
        assertThat(list).size().isGreaterThan(0);
    }

    @DisplayName("3. Listar Propriedade por Id")
    @Test
    void shouldListPropertyById(){
        Propriedades propriedade = propriedadeRepository.findById(1L).get();
        assertEquals("Propriedade Teste", propriedade.getNomePropriedade());
    }

    @DisplayName("4. Alterar nome do Propriedade")
    @Test
    void shouldUpdateNameProperty(){
        Propriedades propriedade = propriedadeRepository.findById(1L).get();
        propriedade.setNomePropriedade("Teste de Propriedade 2");
        propriedadeRepository.save(propriedade);
        assertEquals("Teste de Propriedade 2", propriedade.getNomePropriedade());
    }

    @DisplayName("5. Excluir Propriedade")
    @Test
    void shouldDeleteProperty(){
        propriedadeRepository.deleteById(1L);
        assertThat(propriedadeRepository.existsById(1L));
    }

}
