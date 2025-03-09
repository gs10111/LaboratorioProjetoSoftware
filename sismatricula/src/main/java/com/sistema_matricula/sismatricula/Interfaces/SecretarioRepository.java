package com.sistema_matricula.sismatricula.Interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sistema_matricula.sismatricula.Models.Secretario;

/**
 * Repositório para a entidade Secretario.
 */
@Repository
public interface SecretarioRepository extends JpaRepository<Secretario, Long> {
    // Métodos de consulta customizados, se necessário
} 