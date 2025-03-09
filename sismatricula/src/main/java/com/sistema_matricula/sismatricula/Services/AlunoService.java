package com.sistema_matricula.sismatricula.Services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sistema_matricula.sismatricula.Interfaces.AlunoRepository;
import com.sistema_matricula.sismatricula.Models.Aluno;

/**
 * Serviço responsável pela lógica de negócio relacionada a Aluno.
 */
@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    /**
     * Retorna todos os alunos.
     * @return lista de alunos
     */
    public List<Aluno> listarTodos() {
        return alunoRepository.findAll();
    }

    /**
     * Salva um aluno no repositório.
     * @param aluno objeto aluno a ser salvo
     * @return aluno salvo
     */
    public Aluno salvar(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    /**
     * Busca um aluno por ID.
     * @param id identificador do aluno
     * @return aluno encontrado ou null
     */
    public Aluno buscarPorId(Long id) {
        return alunoRepository.findById(id).orElse(null);
    }

    /**
     * Deleta um aluno por ID.
     * @param id identificador do aluno
     */
    public void deletar(Long id) {
        alunoRepository.deleteById(id);
    }
} 