package com.senac.usuario.service;

import com.senac.usuario.dto.*;
import com.senac.usuario.entity.Usuario;
import com.senac.usuario.repository.ObterPedidosPorUsuario;
import com.senac.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@EnableFeignClients
public class UsuarioService {


    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ObterPedidosPorUsuario obterPedidos;
//    @Autowired
//    private ObterUsuario obterUsuario;

    public List<UsuarioDto> listarTodos(){

        List<UsuarioDto> listaUsuarios = new ArrayList<UsuarioDto>();

        List<Usuario> usuarios = usuarioRepository.findAll();
        for(int i=0; i<usuarios.size();i++){
            UsuarioDto usuarioDto= new UsuarioDto();
            usuarioDto.setId(usuarios.get(i).getId());
            usuarioDto.setNome(usuarios.get(i).getNome());
            usuarioDto.setCpf(usuarios.get(i).getCpf());
            usuarioDto.setStatus(usuarios.get(i).getStatus());

            List<PedidoDto> pedidosDto = obterPedidos.obterPorId(usuarios.get(i).getId());
            usuarioDto.setPedidos(pedidosDto);

            listaUsuarios.add(usuarioDto);
        }
        return listaUsuarios;
    }
    public Usuario listarPorId(Integer id){

        return usuarioRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Usuário não encontrado: " + id)
        );
    }


    public UsuarioResponse criarUsuario(UsuarioRequest usuarioRequest){

        Usuario usuarioSalvo = new Usuario();

        usuarioSalvo.setCpf(usuarioRequest.getCpf());
        usuarioSalvo.setNome(usuarioRequest.getNome());
        usuarioSalvo.setStatus(usuarioRequest.getStatus());

        Usuario usuarioTemp = usuarioRepository.save(usuarioSalvo);
        UsuarioResponse usuarioResponse = new UsuarioResponse();

        usuarioSalvo.setCpf(usuarioTemp.getCpf());
        usuarioSalvo.setNome(usuarioTemp.getNome());
        usuarioSalvo.setStatus(usuarioTemp.getStatus());


        return usuarioResponse;
    }

}
