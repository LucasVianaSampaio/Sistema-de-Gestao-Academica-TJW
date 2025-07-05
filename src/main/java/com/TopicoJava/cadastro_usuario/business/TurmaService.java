package com.TopicoJava.cadastro_usuario.business;

import com.TopicoJava.cadastro_usuario.infrastructure.entitys.Turma;
import com.TopicoJava.cadastro_usuario.infrastructure.repository.TurmaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurmaService {

    private final TurmaRepository repository;

    public TurmaService(TurmaRepository repository) {
        this.repository = repository;
    }

    public void salvarTurma(Turma turma) {
        repository.saveAndFlush(turma);
    }

    public Turma buscarPorCodigo(String codigo) {
        return repository.findByCodigo(codigo)
                .orElseThrow(() -> new RuntimeException("Turma com código não encontrada"));
    }

    public Turma buscarPorId(Long id) {
        Turma turma =  repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Turma com ID não encontrada"));
        if (!turma.getAtivo()) {
            throw new RuntimeException("Turma inexistente");
        }
        return turma;
    }

    public void atualizarTurma(Long id, Turma turmaAtualizada) {
        Turma turmaExistente = buscarPorId(id);

        Turma novaTurma = Turma.builder()
                .id(turmaExistente.getId())
                .codigo(turmaExistente.getCodigo())
                .disciplina(turmaAtualizada.getDisciplina() != null ? turmaAtualizada.getDisciplina() : turmaExistente.getDisciplina())
                .professor(turmaAtualizada.getProfessor() != null ? turmaAtualizada.getProfessor() : turmaExistente.getProfessor())
                .semestre(turmaAtualizada.getSemestre() != null ? turmaAtualizada.getSemestre() : turmaExistente.getSemestre())
                .ano(turmaAtualizada.getAno() != null ? turmaAtualizada.getAno() : turmaExistente.getAno())
                .vagasTotais(turmaAtualizada.getVagasTotais() != null ? turmaAtualizada.getVagasTotais() : turmaExistente.getVagasTotais())
                .vagasOcupadas(turmaAtualizada.getVagasOcupadas() != null ? turmaAtualizada.getVagasOcupadas() : turmaExistente.getVagasOcupadas())
                .ativo(turmaExistente.getAtivo())
                .build();

        repository.saveAndFlush(novaTurma);
    }

    public void deletarTurmaPorCodigo(String codigo) {
        Turma turma = buscarPorCodigo(codigo);
        turma.setAtivo(false);
        repository.saveAndFlush(turma);
    }

    public List<Turma> listarTurmasAtivas() {
        return repository.findByAtivoTrue();
    }

    public List<Turma> buscarPorDisciplina(Long disciplinaId) {
        return repository.findByDisciplinaId(disciplinaId);
    }

    public List<Turma> buscarPorProfessor(Long professorId) {
        return repository.findByProfessorId(professorId);
    }

    public List<Turma> buscarPorSemestreEAno(String semestre, Integer ano) {
        return repository.findBySemestreAndAno(semestre, ano);
    }

    public List<Turma> buscarTurmasComVagasDisponiveis() {
        return repository.findByVagasOcupadasLessThan(Integer.MAX_VALUE); // ou setar a lógica desejada
    }
}
