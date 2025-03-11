package com.sistemaMatricula.sistema.service;

import com.sistemaMatricula.sistema.model.UsuarioBase;
import com.sistemaMatricula.sistema.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Serviço para gerenciar operações relacionadas a usuários
 */
@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    /**
     * Autentica um usuário no sistema
     * @param login O login do usuário
     * @param senha A senha do usuário
     * @return Um Optional contendo o usuário autenticado, se a autenticação for bem-sucedida
     */
    public Optional<UsuarioBase> autenticar(String login, String senha) {
        Optional<UsuarioBase> usuarioOpt = usuarioRepository.findByLogin(login);
        
        if (usuarioOpt.isPresent() && usuarioOpt.get().validarSenha(senha)) {
            return usuarioOpt;
        }
        
        return Optional.empty();
    }
    
    /**
     * Busca um usuário pelo ID
     * @param id O ID do usuário
     * @return Um Optional contendo o usuário, se encontrado
     */
    public Optional<UsuarioBase> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }
    
    /**
     * Busca um usuário pelo login
     * @param login O login do usuário
     * @return Um Optional contendo o usuário, se encontrado
     */
    public Optional<UsuarioBase> buscarPorLogin(String login) {
        return usuarioRepository.findByLogin(login);
    }
    
    /**
     * Lista todos os usuários cadastrados
     * @return Uma lista de usuários
     */
    public List<UsuarioBase> listarTodos() {
        return usuarioRepository.findAll();
    }
    
    /**
     * Salva um usuário no sistema
     * @param usuario O usuário a ser salvo
     * @return O usuário salvo
     */
    public UsuarioBase salvar(UsuarioBase usuario) {
        return usuarioRepository.save(usuario);
    }
    
    /**
     * Remove um usuário do sistema
     * @param id O ID do usuário a ser removido
     */
    public void remover(Long id) {
        usuarioRepository.deleteById(id);
    }
    
    /**
     * Verifica se um usuário com o login fornecido já existe
     * @param login O login a ser verificado
     * @return true se o usuário existe, false caso contrário
     */
    public boolean loginExiste(String login) {
        return usuarioRepository.existsByLogin(login);
    }
} 