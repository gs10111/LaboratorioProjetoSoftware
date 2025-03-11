package com.sistemaMatricula.sistema.service;

import com.sistemaMatricula.sistema.enums.EPerfilUsuario;
import com.sistemaMatricula.sistema.model.Administrador;
import com.sistemaMatricula.sistema.model.Disciplina;
import com.sistemaMatricula.sistema.model.Professor;
import com.sistemaMatricula.sistema.model.UsuarioBase;
import com.sistemaMatricula.sistema.repository.AdministradorRepository;
import com.sistemaMatricula.sistema.repository.DisciplinaRepository;
import com.sistemaMatricula.sistema.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Serviço para gerenciar operações relacionadas a administradores
 */
@Service
public class AdministradorService {
    
    @Autowired
    private AdministradorRepository administradorRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private DisciplinaRepository disciplinaRepository;
    
    /**
     * Busca um administrador pelo ID
     * @param id O ID do administrador
     * @return Um Optional contendo o administrador, se encontrado
     */
    public Optional<Administrador> buscarPorId(Long id) {
        return administradorRepository.findById(id);
    }
    
    /**
     * Busca um administrador pelo login
     * @param login O login do administrador
     * @return Um Optional contendo o administrador, se encontrado
     */
    public Optional<Administrador> buscarPorLogin(String login) {
        return administradorRepository.findByLogin(login);
    }
    
    /**
     * Lista todos os administradores cadastrados
     * @return Uma lista de administradores
     */
    public List<Administrador> listarTodos() {
        return administradorRepository.findAll();
    }
    
    /**
     * Salva um administrador no sistema
     * @param administrador O administrador a ser salvo
     * @return O administrador salvo
     */
    public Administrador salvar(Administrador administrador) {
        return administradorRepository.save(administrador);
    }
    
    /**
     * Remove um administrador do sistema
     * @param id O ID do administrador a ser removido
     */
    public void remover(Long id) {
        administradorRepository.deleteById(id);
    }
    
    /**
     * Cria um usuário no sistema
     * @param usuario O usuário a ser criado
     * @return O usuário criado, se a criação for bem-sucedida
     */
    @Transactional
    public Optional<UsuarioBase> criarUsuario(UsuarioBase usuario) {
        if (!usuarioRepository.existsByLogin(usuario.getLogin())) {
            return Optional.of(usuarioRepository.save(usuario));
        }
        return Optional.empty();
    }
    
    /**
     * Remove um usuário do sistema
     * @param id O ID do usuário a ser removido
     * @return true se a remoção for bem-sucedida, false caso contrário
     */
    @Transactional
    public boolean removerUsuario(Long id) {
        try {
            if (usuarioRepository.existsById(id)) {
                Optional<UsuarioBase> usuarioOpt = usuarioRepository.findById(id);
                if (usuarioOpt.isPresent() && usuarioOpt.get().getTipo() == EPerfilUsuario.PROFESSOR) {
                    Professor professor = (Professor) usuarioOpt.get();
                    List<Disciplina> disciplinas = disciplinaRepository.findByProfessorResponsavel(professor);
                    for (Disciplina disciplina : disciplinas) {
                        disciplina.setProfessorResponsavel(null);
                        disciplinaRepository.save(disciplina);
                    }
                }
                
                usuarioRepository.deleteById(id);
                return true;
            }
            return false;
        } catch (Exception e) {
            System.err.println("Erro ao remover usuário: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Lista todos os usuários do sistema
     * @return Uma lista de usuários
     */
    public List<UsuarioBase> listarUsuarios() {
        return usuarioRepository.findAll();
    }
} 