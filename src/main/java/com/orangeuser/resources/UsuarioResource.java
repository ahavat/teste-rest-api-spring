package com.orangeuser.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.orangeuser.domain.Endereco;
import com.orangeuser.domain.Usuario;
import com.orangeuser.dto.UsuarioDTO;
import com.orangeuser.repositories.EnderecoRepository;
import com.orangeuser.services.CepService;
import com.orangeuser.services.UsuarioService;

@RestController
@RequestMapping(value="/usuarios")
public class UsuarioResource {
	
	@Autowired
	private UsuarioService service;
	
	@Autowired
	private CepService cepService;

	@Autowired
	private EnderecoRepository endRepo;
	
		
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Usuario> find (@PathVariable Integer id) {
		Usuario obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
		
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<UsuarioDTO>> findAll() {
		List<Usuario> list = service.findAll();
		List<UsuarioDTO> listDto = list.stream().map(obj -> new UsuarioDTO(obj)).collect(Collectors.toList());  
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<UsuarioDTO> insert(@Valid @RequestBody UsuarioDTO objDTO) {
		Usuario obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
				
		return ResponseEntity.status(201).build();
	}
	
	@RequestMapping(value="/{id}/enderecos", method = RequestMethod.POST)
	public ResponseEntity<Endereco> insert(@PathVariable(value = "id") Integer id, @Valid @RequestBody Endereco objDTO) {
		try {
		Usuario objId = service.find(id);

		service.insertEnd(objDTO, objId);		
		} catch (Exception e){
			e.getMessage();
		}
		return ResponseEntity.status(201).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody UsuarioDTO objDTO, @PathVariable Integer id) {
		Usuario obj = service.fromDTO(objDTO);
		obj.setId(id);
		obj = service.update(obj);
		
		return ResponseEntity.noContent().build();
	}
		
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
