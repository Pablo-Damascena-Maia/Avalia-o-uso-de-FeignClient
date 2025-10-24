package com.prova.prova.service;

import com.prova.prova.model.Pedido;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@FeignClient(name = "pedido-service", url = "http://localhost:8082")
public interface PedidoClient {
    @GetMapping("/pedidos/usuario/{usuarioId}")
    List<Pedido> getPedidosPorUsuario(@PathVariable Long usuarioId);
}
