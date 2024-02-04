package br.com.duxusdesafio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "integrante")
public class Integrante {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Column(name = "Franquia")
	private String franquia;
	
	@NotNull
	@Column(name = "Nome")
	private String nome;
	
	@NotNull
	@Column(name = "Funcao")
	private String funcao;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "integrante")
	private List<ComposicaoTime> composicaoTime;

	public Integrante() {
	}

	public Integrante(String franquia, String nome, String funcao, List<ComposicaoTime> composicaoTime) {
		this.franquia = franquia;
		this.nome = nome;
		this.funcao = funcao;
		this.composicaoTime = composicaoTime;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFranquia() {
		return franquia;
	}

	public void setFranquia(String franquia) {
		this.franquia = franquia;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public List<ComposicaoTime> getComposicaoTime() {
		return composicaoTime;
	}

	public void setComposicaoTime(List<ComposicaoTime> composicaoTime) {
		this.composicaoTime = composicaoTime;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Integrante)) return false;
		Integrante that = (Integrante) o;
		return id == that.id && Objects.equals(franquia, that.franquia) && Objects.equals(nome, that.nome) && Objects.equals(funcao, that.funcao);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, franquia, nome, funcao);
	}

	@Override
	public String toString() {
		return "Integrante{" +
				"id=" + id +
				", franquia='" + franquia + '\'' +
				", nome='" + nome + '\'' +
				", funcao='" + funcao + '\'' +
				'}';
	}
}
