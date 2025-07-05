package com.TopicoJava.cadastro_usuario.infrastructure.repository;

import com.TopicoJava.cadastro_usuario.infrastructure.entitys.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface TurmaRepository extends JpaRepository<Turma, Long> {

    Optional<Turma> findByCodigo(String codigo);

    List<Turma> findByAtivoTrue();

    List<Turma> findByDisciplinaId(Long disciplinaId);

    List<Turma> findByProfessorId(Long professorId);

    List<Turma> findBySemestreAndAno(String semestre, Integer ano);

    List<Turma> findByVagasOcupadasLessThan(Integer vagasTotais);

    @Transactional
    void deleteByCodigo(String codigo);
}
