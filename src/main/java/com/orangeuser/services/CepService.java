package com.orangeuser.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.orangeuser.domain.Endereco;

@Service
@FeignClient(url= "https://viacep.com.br/ws/" , name = "viacep")
public interface CepService {

	    @GetMapping("{cep}/json")
	    Endereco buscaEnderecoPorCep(@PathVariable("cep") String cep);
	    
	}
	

