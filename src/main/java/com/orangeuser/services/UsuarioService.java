package com.orangeuser.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orangeuser.domain.Endereco;
import com.orangeuser.domain.Usuario;
import com.orangeuser.dto.UsuarioDTO;
import com.orangeuser.dto.UsuarioNewDTO;
import com.orangeuser.repositories.EnderecoRepository;
import com.orangeuser.repositories.UsuarioRepository;


@Service
public class UsuarioService {
	
	@Autowired
    private CepService cepService;
	
	@Autowired
	private UsuarioRepository repo;
	
	@Autowired 
	private EnderecoRepository enderecoRepository;

	
	public Usuario find(Integer id) {
		Optional<Usuario> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName(), null));
	}
	
	@Transactional
	public Usuario insert(Usuario obj) {
		obj.setId(null);
		obj =  repo.save(obj);
	
		return obj;
	}
	
	public Endereco insertEnd(Endereco obj, Usuario objId) {
						
		Endereco endereco = cepService.buscaEnderecoPorCep(obj.getCep());
		endereco.setCep(obj.getCep());
		endereco.setNumero(obj.getNumero());
		endereco.setComplemento(obj.getComplemento());
		endereco.setUsuario(objId);

		enderecoRepository.save(endereco);	
		return endereco;
	}
	
	public Usuario update(Usuario obj) {
		Usuario newObj = find(obj.getId());
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (Exception e) {
			
		}
	}
	
	public List<Usuario> findAll() {
		return repo.findAll();
	} 
	
	public Usuario fromDTO(UsuarioDTO objDto) {
		return new Usuario(objDto.getId(), objDto.getNome(), objDto.getEmail(), 
				objDto.getCpf(), objDto.getDataNascimento());
	}
	
	public Usuario fromDTO(UsuarioNewDTO objDto) {
		Usuario usuario = new Usuario(null, objDto.getNome(), objDto.getEmail(), objDto.getCpf(), objDto.getDataNascimento());
		Endereco loc = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCidade(), objDto.getEstado(), objDto.getCep(), usuario);
	usuario.getEnderecos().add(loc);
	return usuario;
	}
}