package com.prova.prova.controller;

import com.prova.prova.model.Usuario;
import com.prova.prova.repository.UsuarioRepository;
import com.prova.prova.service.PedidoClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;
    private final PedidoClient pedidoClient;

    public UsuarioController(UsuarioRepository usuarioRepository, PedidoClient pedidoClient) {
        this.usuarioRepository = usuarioRepository;
        this.pedidoClient = pedidoClient;
    }

    @PostMapping
    public Usuario cadastrarUsuario(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @GetMapping("/pedidos")
    public List<Map<String, Object>> listarUsuariosComPedidos() {
        return usuarioRepository.findAll().stream()
                .map(usuario -> Map.of(
                        "usuario", usuario,
                        "pedidos", pedidoClient.getPedidosPorUsuario(usuario.getId())
                ))
                .toList();
    }
}
