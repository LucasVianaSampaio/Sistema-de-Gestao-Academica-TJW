package com.TopicoJava.cadastro_usuario.infrastructure.repository;

import com.TopicoJava.cadastro_usuario.infrastructure.entitys.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Long> {

    List<Matricula> findByAlunoId(Long alunoId);

    List<Matricula> findByTurmaId(Long turmaId);

    List<Matricula> findByStatus(Matricula.StatusMatricula status);

    Optional<Matricula> findByAlunoIdAndTurmaId(Long alunoId, Long turmaId);

    long countByTurmaIdAndStatus(Long turmaId, Matricula.StatusMatricula status);

    @Transactional
    void deleteByAlunoIdAndTurmaId(Long alunoId, Long turmaId);
}
