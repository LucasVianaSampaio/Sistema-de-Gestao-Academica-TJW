package com.TopicoJava.cadastro_usuario.infrastructure.repository;

import com.TopicoJava.cadastro_usuario.infrastructure.entitys.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    Optional<Aluno> findByEmail(String email);

    Optional<Aluno> findByMatricula(String matricula);

    List<Aluno> findByNomeContainingIgnoreCase(String nome);

    List<Aluno> findByAtivoTrue();

    @Transactional
    void deleteByMatricula(String matricula);
}
