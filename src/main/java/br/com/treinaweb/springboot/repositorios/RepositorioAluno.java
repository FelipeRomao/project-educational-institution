package br.com.treinaweb.springboot.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.treinaweb.springboot.entidades.Aluno;

public interface RepositorioAluno extends JpaRepository<Aluno, Long> {

	List<Aluno> findByNomeContaining(String nome);
}
