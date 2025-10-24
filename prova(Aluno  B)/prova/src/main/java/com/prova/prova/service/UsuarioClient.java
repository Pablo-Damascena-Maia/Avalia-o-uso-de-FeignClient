package com.prova.prova.service;

import com.prova.prova.model.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "usuario-service", url = "http://localhost:8081")
public interface UsuarioClient {
    @PostMapping("/usuarios")
    Usuario cadastrarUsuario(@RequestBody Usuario usuario);
}
