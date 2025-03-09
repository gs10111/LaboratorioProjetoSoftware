package com.sistema_matricula.sismatricula.Services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sistema_matricula.sismatricula.Interfaces.DisciplinaRepository;
import com.sistema_matricula.sismatricula.Models.Disciplina;

/**
 * Serviço responsável pela lógica de negócio relacionada a Disciplina.
 */
@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    /**
     * Retorna todas as disciplinas.
     * @return lista de disciplinas
     */
    public List<Disciplina> listarTodas() {
        return disciplinaRepository.findAll();
    }

    /**
     * Salva uma disciplina no repositório.
     * @param disciplina objeto disciplina a ser salvo
     * @return disciplina salva
     */
    public Disciplina salvar(Disciplina disciplina) {
        return disciplinaRepository.save(disciplina);
    }

    /**
     * Busca uma disciplina por ID.
     * @param id identificador da disciplina
     * @return disciplina encontrada ou null
     */
    public Disciplina buscarPorId(Long id) {
        return disciplinaRepository.findById(id).orElse(null);
    }

    /**
     * Deleta uma disciplina por ID.
     * @param id identificador da disciplina
     */
    public void deletar(Long id) {
        disciplinaRepository.deleteById(id);
    }
    
    /**
     * Ativa uma disciplina.
     * @param id identificador da disciplina
     * @return disciplina ativada
     */
    public Disciplina ativar(Long id) {
        Disciplina disciplina = buscarPorId(id);
        if (disciplina != null) {
            disciplina.ativarDisciplina();
            return salvar(disciplina);
        }
        return null;
    }
    
    /**
     * Cancela uma disciplina.
     * @param id identificador da disciplina
     * @return disciplina cancelada
     */
    public Disciplina cancelar(Long id) {
        Disciplina disciplina = buscarPorId(id);
        if (disciplina != null) {
            disciplina.cancelarDisciplina();
            return salvar(disciplina);
        }
        return null;
    }
} 