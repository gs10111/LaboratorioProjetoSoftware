package com.sistemaMatricula.sistema.service;

import com.sistemaMatricula.sistema.enums.EStatusDisciplina;
import com.sistemaMatricula.sistema.enums.ETipoDisciplina;
import com.sistemaMatricula.sistema.model.*;
import com.sistemaMatricula.sistema.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Serviço para gerenciar operações relacionadas a secretarias
 */
@Service
public class SecretariaService {
    
    @Autowired
    private SecretariaRepository secretariaRepository;
    
    @Autowired
    private CursoRepository cursoRepository;
    
    @Autowired
    private DisciplinaRepository disciplinaRepository;
    
    @Autowired
    private CurriculoRepository curriculoRepository;
    
    @Autowired
    private ProfessorRepository professorRepository;
    
    /**
     * Busca uma secretaria pelo ID
     * @param id O ID da secretaria
     * @return Um Optional contendo a secretaria, se encontrada
     */
    public Optional<Secretaria> buscarPorId(Long id) {
        return secretariaRepository.findById(id);
    }
    
    /**
     * Busca uma secretaria pelo login
     * @param login O login da secretaria
     * @return Um Optional contendo a secretaria, se encontrada
     */
    public Optional<Secretaria> buscarPorLogin(String login) {
        return secretariaRepository.findByLogin(login);
    }
    
    /**
     * Lista todas as secretarias cadastradas
     * @return Uma lista de secretarias
     */
    public List<Secretaria> listarTodas() {
        return secretariaRepository.findAll();
    }
    
    /**
     * Salva uma secretaria no sistema
     * @param secretaria A secretaria a ser salva
     * @return A secretaria salva
     */
    public Secretaria salvar(Secretaria secretaria) {
        return secretariaRepository.save(secretaria);
    }
    
    /**
     * Remove uma secretaria do sistema
     * @param id O ID da secretaria a ser removida
     */
    public void remover(Long id) {
        secretariaRepository.deleteById(id);
    }
    
    /**
     * Cadastra um curso no sistema
     * @param curso O curso a ser cadastrado
     * @return O curso cadastrado, se o cadastro for bem-sucedido
     */
    @Transactional
    public Optional<Curso> cadastrarCurso(Curso curso) {
        if (curso.validarCreditos() && !cursoRepository.existsByNome(curso.getNome())) {
            return Optional.of(cursoRepository.save(curso));
        }
        return Optional.empty();
    }
    
    /**
     * Cadastra uma disciplina no sistema
     * @param disciplina A disciplina a ser cadastrada
     * @param cursoId O ID do curso ao qual a disciplina pertence
     * @param professorId O ID do professor responsável pela disciplina
     * @return A disciplina cadastrada, se o cadastro for bem-sucedido
     */
    @Transactional
    public Optional<Disciplina> cadastrarDisciplina(Disciplina disciplina, Long cursoId, Long professorId) {
        Optional<Curso> cursoOpt = cursoRepository.findById(cursoId);
        Optional<Professor> professorOpt = professorRepository.findById(professorId);
        
        if (cursoOpt.isPresent() && professorOpt.isPresent()) {
            disciplina.setCurso(cursoOpt.get());
            disciplina.setProfessorResponsavel(professorOpt.get());
            disciplina.setStatus(EStatusDisciplina.ATIVA);
            
            return Optional.of(disciplinaRepository.save(disciplina));
        }
        
        return Optional.empty();
    }
    
    /**
     * Cancela uma disciplina no sistema
     * @param disciplinaId O ID da disciplina a ser cancelada
     * @return true se o cancelamento for bem-sucedido, false caso contrário
     */
    @Transactional
    public boolean cancelarDisciplina(Long disciplinaId) {
        Optional<Disciplina> disciplinaOpt = disciplinaRepository.findById(disciplinaId);
        
        if (disciplinaOpt.isPresent()) {
            Disciplina disciplina = disciplinaOpt.get();
            disciplina.setStatus(EStatusDisciplina.CANCELADA);
            disciplinaRepository.save(disciplina);
            return true;
        }
        
        return false;
    }
    
    /**
     * Gera um currículo no sistema
     * @param curriculo O currículo a ser gerado
     * @return O currículo gerado, se a geração for bem-sucedida
     */
    @Transactional
    public Optional<Curriculo> gerarCurriculo(Curriculo curriculo) {
        if (!curriculoRepository.existsByNome(curriculo.getNome())) {
            return Optional.of(curriculoRepository.save(curriculo));
        }
        return Optional.empty();
    }
    
    /**
     * Adiciona uma disciplina a um currículo
     * @param curriculoId O ID do currículo
     * @param disciplinaId O ID da disciplina
     * @return true se a adição for bem-sucedida, false caso contrário
     */
    @Transactional
    public boolean adicionarDisciplinaAoCurriculo(Long curriculoId, Long disciplinaId) {
        Optional<Curriculo> curriculoOpt = curriculoRepository.findById(curriculoId);
        Optional<Disciplina> disciplinaOpt = disciplinaRepository.findById(disciplinaId);
        
        if (curriculoOpt.isPresent() && disciplinaOpt.isPresent()) {
            Curriculo curriculo = curriculoOpt.get();
            Disciplina disciplina = disciplinaOpt.get();
            
            return curriculo.adicionarDisciplina(disciplina);
        }
        
        return false;
    }
    
    /**
     * Remove uma disciplina de um currículo
     * @param curriculoId O ID do currículo
     * @param disciplinaId O ID da disciplina
     * @return true se a remoção for bem-sucedida, false caso contrário
     */
    @Transactional
    public boolean removerDisciplinaDoCurriculo(Long curriculoId, Long disciplinaId) {
        Optional<Curriculo> curriculoOpt = curriculoRepository.findById(curriculoId);
        Optional<Disciplina> disciplinaOpt = disciplinaRepository.findById(disciplinaId);
        
        if (curriculoOpt.isPresent() && disciplinaOpt.isPresent()) {
            Curriculo curriculo = curriculoOpt.get();
            Disciplina disciplina = disciplinaOpt.get();
            
            return curriculo.removerDisciplina(disciplina);
        }
        
        return false;
    }
    
    /**
     * Lista todas as disciplinas cadastradas
     * @return Uma lista de disciplinas
     */
    public List<Disciplina> listarDisciplinas() {
        return disciplinaRepository.findAll();
    }
    
    /**
     * Lista todas as disciplinas por tipo
     * @param tipo O tipo da disciplina
     * @return Uma lista de disciplinas do tipo especificado
     */
    public List<Disciplina> listarDisciplinasPorTipo(ETipoDisciplina tipo) {
        return disciplinaRepository.findByTipo(tipo);
    }
    
    /**
     * Lista todos os cursos cadastrados
     * @return Uma lista de cursos
     */
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public List<Curso> listarCursos() {
        return cursoRepository.findAll();
    }
} 