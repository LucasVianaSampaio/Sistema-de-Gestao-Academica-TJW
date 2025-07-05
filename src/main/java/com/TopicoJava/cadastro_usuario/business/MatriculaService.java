package com.TopicoJava.cadastro_usuario.business;

import com.TopicoJava.cadastro_usuario.infrastructure.entitys.Aluno;
import com.TopicoJava.cadastro_usuario.infrastructure.entitys.Matricula;
import com.TopicoJava.cadastro_usuario.infrastructure.entitys.Turma;
import com.TopicoJava.cadastro_usuario.infrastructure.repository.AlunoRepository;
import com.TopicoJava.cadastro_usuario.infrastructure.repository.MatriculaRepository;
import com.TopicoJava.cadastro_usuario.infrastructure.repository.TurmaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MatriculaService {

    private final MatriculaRepository matriculaRepository;
    private final AlunoRepository alunoRepository;
    private final TurmaRepository turmaRepository;

    @Transactional
    public Matricula realizarMatricula(Matricula matricula) {
        Aluno aluno = alunoRepository.findById(matricula.getAluno().getId())
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        Turma turma = turmaRepository.findById(matricula.getTurma().getId())
                .orElseThrow(() -> new RuntimeException("Turma não encontrada"));

        if (turma.getVagasOcupadas() >= turma.getVagasTotais()) {
            throw new RuntimeException("Turma sem vagas disponíveis");
        }

        turma.setVagasOcupadas(turma.getVagasOcupadas() + 1);
        turmaRepository.save(turma);

        matricula.setAluno(aluno);
        matricula.setTurma(turma);
        matricula.setDataMatricula(LocalDateTime.now());
        matricula.setStatus(Matricula.StatusMatricula.ATIVA);

        return matriculaRepository.save(matricula);
    }

    public Matricula buscarPorId(Long id) {
        return matriculaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matrícula não encontrada"));
    }

    public List<Matricula> listarTodas() {
        return matriculaRepository.findAll();
    }

    public List<Matricula> listarPorAluno(Long alunoId) {
        return matriculaRepository.findByAlunoId(alunoId);
    }

    public List<Matricula> listarPorTurma(Long turmaId) {
        return matriculaRepository.findByTurmaId(turmaId);
    }

    public List<Matricula> listarPorStatus(Matricula.StatusMatricula status) {
        return matriculaRepository.findByStatus(status);
    }

    @Transactional
    public void cancelarMatricula(Long id) {
        Matricula matricula = buscarPorId(id);

        if (matricula.getStatus() != Matricula.StatusMatricula.ATIVA) {
            throw new RuntimeException("A matrícula não está ativa.");
        }

        matricula.setStatus(Matricula.StatusMatricula.CANCELADA);
        matriculaRepository.save(matricula);

        Turma turma = matricula.getTurma();
        turma.setVagasOcupadas(Math.max(0, turma.getVagasOcupadas() - 1));
        turmaRepository.save(turma);
    }

    @Transactional
    public Matricula atualizarMatricula(Long id, Matricula dadosAtualizados) {
        Matricula matricula = buscarPorId(id);

        if (dadosAtualizados.getNota() != null)
            matricula.setNota(dadosAtualizados.getNota());

        if (dadosAtualizados.getFrequencia() != null)
            matricula.setFrequencia(dadosAtualizados.getFrequencia());

        if (dadosAtualizados.getStatus() != null)
            matricula.setStatus(dadosAtualizados.getStatus());

        return matriculaRepository.save(matricula);
    }

    @Transactional
    public Matricula alterarTurma(Long id, Long novaTurmaId) {
        Matricula matricula = buscarPorId(id);

        Turma novaTurma = turmaRepository.findById(novaTurmaId)
                .orElseThrow(() -> new RuntimeException("Nova turma não encontrada"));

        if (novaTurma.getVagasOcupadas() >= novaTurma.getVagasTotais()) {
            throw new RuntimeException("Nova turma sem vagas disponíveis");
        }

        Turma antigaTurma = matricula.getTurma();
        antigaTurma.setVagasOcupadas(Math.max(0, antigaTurma.getVagasOcupadas() - 1));
        turmaRepository.save(antigaTurma);

        novaTurma.setVagasOcupadas(novaTurma.getVagasOcupadas() + 1);
        turmaRepository.save(novaTurma);

        matricula.setTurma(novaTurma);

        return matriculaRepository.save(matricula);
    }
}
