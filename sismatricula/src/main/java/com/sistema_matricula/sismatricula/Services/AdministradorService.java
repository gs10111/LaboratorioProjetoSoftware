package com.sistema_matricula.sismatricula.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sistema_matricula.sismatricula.Interfaces.UsuarioRepository;
import com.sistema_matricula.sismatricula.Models.EPerfilUsuario;
import com.sistema_matricula.sismatricula.Models.UsuarioBase;

@Service
public class AdministradorService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public boolean autenticar(String login, String senha) {
        Optional<UsuarioBase> usuarioOpt = usuarioRepository.findByLogin(login);

        if (usuarioOpt.isPresent()) {
            UsuarioBase usuario = usuarioOpt.get();
            return passwordEncoder.matches(senha, usuario.getSenha());
        }

        return false;
    }

    public void criarUsuario(UsuarioBase usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha())); // Salva a senha criptografada
        usuarioRepository.save(usuario);
    }

    public void removerUsuario(UsuarioBase usuario) {
        usuarioRepository.delete(usuario);
    }

    public void alterarPerfilUsuario(UsuarioBase usuario, EPerfilUsuario novoPerfil) {
        usuario.setTipo(novoPerfil);
        usuarioRepository.save(usuario);
    }
}
