package com.TopicoJava.cadastro_usuario.infrastructure.repository;

import com.TopicoJava.cadastro_usuario.infrastructure.entitys.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {

    Optional<Disciplina> findByCodigo(String codigo);

    List<Disciplina> findByNomeContainingIgnoreCase(String nome);

    List<Disciplina> findByAtivoTrue();

    @Transactional
    void deleteByCodigo(String codigo);
}
