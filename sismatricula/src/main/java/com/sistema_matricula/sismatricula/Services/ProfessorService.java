package com.sistema_matricula.sismatricula.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sistema_matricula.sismatricula.Interfaces.ProfessorRepository;
import com.sistema_matricula.sismatricula.Models.Professor;

/**
 * Serviço responsável pela lógica de negócio relacionada a Professor.
 */
@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public boolean autenticar(String email, String senha) {
        Optional<Professor> professorOpt = professorRepository.findByEmail(email);

        if (professorOpt.isPresent()) {
            Professor professor = professorOpt.get();
            return passwordEncoder.matches(senha, professor.getSenha());
        }

        return false;
    }

    /**
     * Retorna todos os professores.
     * @return lista de professores
     */
    public List<Professor> listarTodos() {
        return professorRepository.findAll();
    }

    /**
     * Salva um professor no repositório.
     * @param professor objeto professor a ser salvo
     * @return professor salvo
     */
    public Professor salvar(Professor professor) {
        return professorRepository.save(professor);
    }

    /**
     * Busca um professor por ID.
     * @param id identificador do professor
     * @return professor encontrado ou null
     */
    public Professor buscarPorId(Long id) {
        return professorRepository.findById(id).orElse(null);
    }

    /**
     * Deleta um professor por ID.
     * @param id identificador do professor
     */
    public void deletar(Long id) {
        professorRepository.deleteById(id);
    }
} 