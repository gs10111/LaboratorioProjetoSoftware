package com.sistema_matricula.sismatricula.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.sistema_matricula.sismatricula.Models.Aluno;
import com.sistema_matricula.sismatricula.Services.AlunoService;

/**
 * Controller para gerenciar endpoints relacionados a Aluno.
 */
@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    /**
     * Retorna todos os alunos.
     * @return lista de alunos
     */
    @GetMapping
    public List<Aluno> listarTodos() {
        return alunoService.listarTodos();
    }

    /**
     * Salva um novo aluno.
     * @param aluno objeto aluno a ser salvo
     * @return aluno salvo
     */
    @PostMapping
    public Aluno salvar(@RequestBody Aluno aluno) {
        return alunoService.salvar(aluno);
    }

    /**
     * Busca um aluno por ID.
     * @param id identificador do aluno
     * @return aluno encontrado ou null
     */
    @GetMapping("/{id}")
    public Aluno buscarPorId(@PathVariable Long id) {
        return alunoService.buscarPorId(id);
    }

    /**
     * Deleta um aluno por ID.
     * @param id identificador do aluno
     */
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        alunoService.deletar(id);
    }
} 