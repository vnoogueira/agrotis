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

import vitornogueira.com.agrotis.dto.PropriedadesDto;
import vitornogueira.com.agrotis.entities.Propriedades;
import vitornogueira.com.agrotis.services.PropriedadeService;

@RestController
@RequestMapping(value = "/propriedades")
public class PropriedadeResource {
	
	@Autowired
	private PropriedadeService propriedadeService;

	@GetMapping
	public ResponseEntity<List<PropriedadesDto>> findAllPropriedade() {
		List<Propriedades> list = propriedadeService.findAll();
		List<PropriedadesDto> propriedadesDto = list.stream().map(obj -> new PropriedadesDto(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(propriedadesDto);
	}

	
	@GetMapping(value = "/{id}")
	public ResponseEntity<PropriedadesDto> findPropriedadesById(@PathVariable Long id){
		PropriedadesDto propriedadesDto = propriedadeService.findPropriedadesById(id);
		return ResponseEntity.ok().body(propriedadesDto);
		
	}
	
	@PostMapping
	public ResponseEntity<PropriedadesDto> insertPropriedade(@RequestBody PropriedadesDto entity){
		PropriedadesDto dto = propriedadeService.insertPropriedade(entity);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<PropriedadesDto> updatePropriedade(@PathVariable Long id, @RequestBody PropriedadesDto objeto){
		PropriedadesDto dto = propriedadeService.updatePropriedade(id, objeto);
		return ResponseEntity.ok().body(dto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletePropriedade(@PathVariable Long id){
		propriedadeService.deletePropriedade(id);
		return ResponseEntity.noContent().build();
	}
	
	
}
