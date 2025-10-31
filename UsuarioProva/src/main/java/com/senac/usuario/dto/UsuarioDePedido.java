package com.senac.usuario.dto;

import java.util.List;

public record UsuarioDePedido (
        PedidoDto pedidoDto,
        List<UsuarioResponse> pedidoList
){}