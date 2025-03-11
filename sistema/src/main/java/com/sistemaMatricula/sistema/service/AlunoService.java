package com.sistemaMatricula.sistema.service;

import com.sistemaMatricula.sistema.enums.EStatusDisciplina;
import com.sistemaMatricula.sistema.enums.EStatusMatricula;
import com.sistemaMatricula.sistema.enums.ETipoDisciplina;
import com.sistemaMatricula.sistema.model.Aluno;
import com.sistemaMatricula.sistema.model.Disciplina;
import com.sistemaMatricula.sistema.model.Matricula;
import com.sistemaMatricula.sistema.model.PeriodoMatricula;
import com.sistemaMatricula.sistema.repository.AlunoRepository;
import com.sistemaMatricula.sistema.repository.DisciplinaRepository;
import com.sistemaMatricula.sistema.repository.MatriculaRepository;
import com.sistemaMatricula.sistema.repository.PeriodoMatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Serviço para gerenciar operações relacionadas a alunos
 */
@Service
public class AlunoService {
    
    @Autowired
    private AlunoRepository alunoRepository;
    
    @Autowired
    private DisciplinaRepository disciplinaRepository;
    
    @Autowired
    private MatriculaRepository matriculaRepository;
    
    @Autowired
    private PeriodoMatriculaRepository periodoMatriculaRepository;
    
    /**
     * Busca um aluno pelo ID
     * @param id O ID do aluno
     * @return Um Optional contendo o aluno, se encontrado
     */
    public Optional<Aluno> buscarPorId(Long id) {
        return alunoRepository.findById(id);
    }
    
    /**
     * Busca um aluno pelo login
     * @param login O login do aluno
     * @return Um Optional contendo o aluno, se encontrado
     */
    public Optional<Aluno> buscarPorLogin(String login) {
        return alunoRepository.findByLogin(login);
    }
    
    /**
     * Lista todos os alunos cadastrados
     * @return Uma lista de alunos
     */
    public List<Aluno> listarTodos() {
        return alunoRepository.findAll();
    }
    
    /**
     * Salva um aluno no sistema
     * @param aluno O aluno a ser salvo
     * @return O aluno salvo
     */
    public Aluno salvar(Aluno aluno) {
        return alunoRepository.save(aluno);
    }
    
    /**
     * Remove um aluno do sistema
     * @param id O ID do aluno a ser removido
     */
    public void remover(Long id) {
        alunoRepository.deleteById(id);
    }
    
    /**
     * Realiza a matrícula de um aluno em uma disciplina
     * @param alunoId O ID do aluno
     * @param disciplinaId O ID da disciplina
     * @param tipo O tipo da disciplina (obrigatória ou optativa)
     * @return true se a matrícula foi realizada com sucesso, false caso contrário
     */
    @Transactional
    public boolean matricular(Long alunoId, Long disciplinaId, ETipoDisciplina tipo) {
        Optional<Aluno> alunoOpt = alunoRepository.findById(alunoId);
        Optional<Disciplina> disciplinaOpt = disciplinaRepository.findById(disciplinaId);
        Optional<PeriodoMatricula> periodoOpt = periodoMatriculaRepository.findPeriodoMatriculaAtual(LocalDate.now());
        
        if (alunoOpt.isPresent() && disciplinaOpt.isPresent() && periodoOpt.isPresent()) {
            Aluno aluno = alunoOpt.get();
            Disciplina disciplina = disciplinaOpt.get();
            PeriodoMatricula periodoMatricula = periodoOpt.get();
            
            if (!periodoMatricula.estaAberto()) {
                return false;
            }
            
            if (!disciplina.getTipo().equals(tipo)) {
                return false;
            }
            
            if (!disciplina.getStatus().equals(EStatusDisciplina.ATIVA)) {
                return false;
            }
            
            if (matriculaRepository.findByAlunoAndDisciplina(aluno, disciplina).isPresent()) {
                return false;
            }
            
            if (matriculaRepository.countAlunosMatriculadosByDisciplina(disciplina) >= Disciplina.NUMERO_MAX_ALUNOS) {
                return false;
            }
            
            int disciplinasObrigatorias = contarDisciplinasPorTipo(aluno, ETipoDisciplina.OBRIGATORIA);
            int disciplinasOptativas = contarDisciplinasPorTipo(aluno, ETipoDisciplina.OPTATIVA);
            
            if (tipo.equals(ETipoDisciplina.OBRIGATORIA) && disciplinasObrigatorias >= Aluno.getMaxDisciplinasObrigatorias()) {
                return false;
            }
            
            if (tipo.equals(ETipoDisciplina.OPTATIVA) && disciplinasOptativas >= Aluno.getMaxDisciplinasOptativas()) {
                return false;
            }
            
            Matricula matricula = new Matricula();
            matricula.setAluno(aluno);
            matricula.setDisciplina(disciplina);
            matricula.setPeriodoMatricula(periodoMatricula);
            matricula.setStatus(EStatusMatricula.EFETIVADA);
            
            matriculaRepository.save(matricula);
            
            matricula.notificarSistemaCobranca();
            
            return true;
        }
        
        return false;
    }
    
    /**
     * Cancela a matrícula de um aluno em uma disciplina
     * @param alunoId O ID do aluno
     * @param disciplinaId O ID da disciplina
     * @return true se o cancelamento foi realizado com sucesso, false caso contrário
     */
    @Transactional
    public boolean cancelarMatricula(Long alunoId, Long disciplinaId) {
        Optional<Aluno> alunoOpt = alunoRepository.findById(alunoId);
        Optional<Disciplina> disciplinaOpt = disciplinaRepository.findById(disciplinaId);
        Optional<PeriodoMatricula> periodoOpt = periodoMatriculaRepository.findPeriodoMatriculaAtual(LocalDate.now());
        
        if (alunoOpt.isPresent() && disciplinaOpt.isPresent() && periodoOpt.isPresent()) {
            Aluno aluno = alunoOpt.get();
            Disciplina disciplina = disciplinaOpt.get();
            PeriodoMatricula periodoMatricula = periodoOpt.get();
            
            if (!periodoMatricula.estaAberto()) {
                return false;
            }
            
            Optional<Matricula> matriculaOpt = matriculaRepository.findByAlunoAndDisciplina(aluno, disciplina);
            
            if (matriculaOpt.isPresent()) {
                Matricula matricula = matriculaOpt.get();
                
                matricula.setStatus(EStatusMatricula.CANCELADA);
                matriculaRepository.save(matricula);
                
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Lista todas as disciplinas em que um aluno está matriculado
     * @param alunoId O ID do aluno
     * @return Uma lista de disciplinas em que o aluno está matriculado
     */
    @Transactional(readOnly = true)
    public List<Disciplina> listarDisciplinasMatriculadas(Long alunoId) {
        Optional<Aluno> alunoOpt = alunoRepository.findById(alunoId);
        
        if (alunoOpt.isPresent()) {
            Aluno aluno = alunoOpt.get();
            
            List<Matricula> matriculas = matriculaRepository.findByAluno(aluno);
            
            return matriculas.stream()
                    .filter(m -> m.getStatus().equals(EStatusMatricula.EFETIVADA))
                    .map(Matricula::getDisciplina)
                    .collect(Collectors.toList());
        }
        
        return List.of();
    }
    
    /**
     * Conta o número de disciplinas de um tipo específico em que um aluno está matriculado
     * @param aluno O aluno
     * @param tipo O tipo da disciplina
     * @return O número de disciplinas do tipo especificado
     */
    private int contarDisciplinasPorTipo(Aluno aluno, ETipoDisciplina tipo) {
        List<Matricula> matriculas = matriculaRepository.findByAluno(aluno);
        
        return (int) matriculas.stream()
                .filter(m -> m.getStatus().equals(EStatusMatricula.EFETIVADA))
                .filter(m -> m.getDisciplina().getTipo().equals(tipo))
                .count();
    }
} 