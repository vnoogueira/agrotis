package vitornogueira.com.agrotis.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import vitornogueira.com.agrotis.dto.PessoaDto;
import vitornogueira.com.agrotis.entities.Pessoa;
import vitornogueira.com.agrotis.services.PessoaService;

@RestController
@RequestMapping(value = "/pessoa")
public class PessoaResource {

	@Autowired
	private PessoaService pessoaService;

	@GetMapping
	public ResponseEntity<List<PessoaDto>> findall() {
		List<Pessoa> list = pessoaService.findAllPessoa();
		List<PessoaDto> pessoaDto = list.stream().map(obj -> new PessoaDto(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(pessoaDto);

	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<PessoaDto> findPessoaById(@PathVariable Long id) {
		PessoaDto pessoaDto = pessoaService.findPessoaById(id);
		return ResponseEntity.ok().body(pessoaDto);

	}

	@PostMapping
	public ResponseEntity<PessoaDto> insertPessoa(@RequestBody PessoaDto entity) {
		PessoaDto dto = pessoaService.insertPessoa(entity);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<PessoaDto> updatePessoa(@PathVariable Long id, @RequestBody PessoaDto objeto) {
		PessoaDto dto = pessoaService.updatePessoa(id, objeto);
		return ResponseEntity.ok().body(dto);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletePessoa(@PathVariable Long id) {
		pessoaService.deletePessoa(id);
		return ResponseEntity.noContent().build();
	}

}
