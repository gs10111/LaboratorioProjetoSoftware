package com.sistemaMatricula.sistema.repository;

import com.sistemaMatricula.sistema.model.Secretaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repositório para operações de CRUD de Secretarias
 */
@Repository
public interface SecretariaRepository extends JpaRepository<Secretaria, Long> {
    
    /**
     * Busca uma secretaria pelo login
     * @param login O login da secretaria
     * @return Um Optional contendo a secretaria, se encontrada
     */
    Optional<Secretaria> findByLogin(String login);
} 