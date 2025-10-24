package com.prova.prova.controller;

import com.prova.prova.model.Pedido;
import com.prova.prova.model.Usuario;
import com.prova.prova.repository.PedidoRepository;
import com.prova.prova.service.UsuarioClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoRepository pedidoRepository;
    private final UsuarioClient usuarioClient;

    public PedidoController(PedidoRepository pedidoRepository, UsuarioClient usuarioClient) {
        this.pedidoRepository = pedidoRepository;
        this.usuarioClient = usuarioClient;
    }

    @PostMapping
    public Pedido criarPedido(@RequestBody Pedido pedido) {
        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome("Novo Cliente");
        novoUsuario.setEmail("cliente@teste.com");

        Usuario usuarioCadastrado = usuarioClient.cadastrarUsuario(novoUsuario);
        pedido.setUsuarioId(usuarioCadastrado.getId());

        return pedidoRepository.save(pedido);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Pedido> listarPedidosPorUsuario(@PathVariable Long usuarioId) {
        return pedidoRepository.findByUsuarioId(usuarioId);
    }
}
