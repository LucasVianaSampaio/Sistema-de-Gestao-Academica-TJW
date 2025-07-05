package com.TopicoJava.cadastro_usuario.infrastructure.repository;

import com.TopicoJava.cadastro_usuario.infrastructure.entitys.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    Optional<Professor> findByCpf(String cpf);

    Optional<Professor> findByEmail(String email);

    List<Professor> findByNomeContainingIgnoreCase(String nome);

    List<Professor> findByAreaAtuacaoContainingIgnoreCase(String areaAtuacao);

    List<Professor> findByAtivoTrue();

    @Transactional
    void deleteByCpf(String cpf);
}
