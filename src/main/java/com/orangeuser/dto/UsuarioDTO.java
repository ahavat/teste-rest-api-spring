package com.orangeuser.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.orangeuser.domain.Endereco;
import com.orangeuser.domain.Usuario;

public class UsuarioDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message= "Preenchimento obrigatório")
	private String nome;
	
	@NotEmpty(message= "Preenchimento obrigatório")
	@Email(message= "Email inválido")
	@Column(unique=true)
	private String email;
	
	@NotEmpty(message= "Preenchimento obrigatório")
	@Column(unique=true)
	@CPF
	private String cpf;
	

	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataNascimento;
	
	private List<Endereco> enderecos = new ArrayList<>();
	
	public UsuarioDTO() {
	}

	public UsuarioDTO(Usuario obj) {
		 id = obj.getId();
		 nome = obj.getNome();
		 email = obj.getEmail();
		 cpf = obj.getCpf(); 
		 dataNascimento = obj.getdataNascimento();
		 enderecos = obj.getEnderecos();
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}
}
	
	
	
