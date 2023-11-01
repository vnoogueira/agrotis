package vitornogueira.com.agrotis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import vitornogueira.com.agrotis.entities.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
