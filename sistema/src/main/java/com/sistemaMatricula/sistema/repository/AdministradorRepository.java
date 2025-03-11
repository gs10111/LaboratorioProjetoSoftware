package com.sistemaMatricula.sistema.repository;

import com.sistemaMatricula.sistema.model.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repositório para operações de CRUD de Administradores
 */
@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
    
    /**
     * Busca um administrador pelo login
     * @param login O login do administrador
     * @return Um Optional contendo o administrador, se encontrado
     */
    Optional<Administrador> findByLogin(String login);
} 