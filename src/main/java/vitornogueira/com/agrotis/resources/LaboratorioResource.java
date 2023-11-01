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

import vitornogueira.com.agrotis.dto.LaboratoriosDto;
import vitornogueira.com.agrotis.entities.Laboratorio;
import vitornogueira.com.agrotis.services.LaboratorioService;

@RestController
@RequestMapping(value = "/laboratorios")
public class LaboratorioResource {
	
	@Autowired
	private LaboratorioService laboratorioService;

	@GetMapping
	public ResponseEntity<List<LaboratoriosDto>> findAllLaboratorio() {
		List<Laboratorio> list = laboratorioService.findAll();
		List<LaboratoriosDto> laboratorioDto = list.stream().map(obj -> new LaboratoriosDto(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(laboratorioDto);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<LaboratoriosDto> findLaboratorioById(@PathVariable Long id){
		LaboratoriosDto laboratoriosDto = laboratorioService.findLaboratorioById(id);
		return ResponseEntity.ok().body(laboratoriosDto);
		
	}
	
	@PostMapping
	public ResponseEntity<LaboratoriosDto> insertLaboratorio(@RequestBody LaboratoriosDto entity){
		LaboratoriosDto dto = laboratorioService.insertLaboratorio(entity);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<LaboratoriosDto> updateLaboratorio(@PathVariable Long id, @RequestBody LaboratoriosDto objeto){
		LaboratoriosDto dto = laboratorioService.updateLaboratorio(id, objeto);
		return ResponseEntity.ok().body(dto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteLaboratorio(@PathVariable Long id){
		laboratorioService.deleteLaboratorio(id);
		return ResponseEntity.noContent().build();
	}

}
