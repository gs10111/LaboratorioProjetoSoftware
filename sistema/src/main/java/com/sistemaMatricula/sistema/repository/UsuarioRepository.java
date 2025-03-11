package com.sistemaMatricula.sistema.repository;

import com.sistemaMatricula.sistema.model.UsuarioBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repositório para operações de CRUD de Usuários
 */
@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioBase, Long> {
    
    /**
     * Busca um usuário pelo login
     * @param login O login do usuário
     * @return Um Optional contendo o usuário, se encontrado
     */
    Optional<UsuarioBase> findByLogin(String login);
    
    /**
     * Verifica se um usuário com o login fornecido existe
     * @param login O login a ser verificado
     * @return true se o usuário existe, false caso contrário
     */
    boolean existsByLogin(String login);
} 