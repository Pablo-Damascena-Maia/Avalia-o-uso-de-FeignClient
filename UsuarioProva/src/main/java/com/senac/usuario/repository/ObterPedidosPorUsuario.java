package com.senac.usuario.repository;

import com.senac.usuario.dto.PedidoDto;
import com.senac.usuario.entity.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Component
@FeignClient(name = "ObterPedidos", url = "10.136.36.210:8080", path = "/api/pedido")
public interface ObterPedidosPorUsuario {

    @GetMapping(value = "/buscar/usuario/{id}")
    public List<PedidoDto> obterPorId(@PathVariable Integer id);

}
