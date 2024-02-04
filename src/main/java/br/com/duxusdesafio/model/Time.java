package br.com.duxusdesafio.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "time")
public class Time {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "Data")
    private LocalDate data;

	@Column(name = "Nome")
	private String nome;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "time", cascade = CascadeType.ALL)
	private List<ComposicaoTime> composicaoTime;

	public Time() {
	}

	public Time(LocalDate data, List<ComposicaoTime> composicaoTime, String nome) {
		this.data = data;
		this.composicaoTime = composicaoTime;
		this.nome = nome;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public List<ComposicaoTime> getComposicaoTime() {
		return composicaoTime;
	}

	public void setComposicaoTime(List<ComposicaoTime> composicaoTime) {
		this.composicaoTime = composicaoTime;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	@Override
	public final boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Time)) return false;
		Time time = (Time) o;
		return id == time.id && Objects.equals(data, time.data);
	}

	@Override
	public final int hashCode() {
		return Objects.hash(id, data);
	}

	@Override
	public String toString() {
		return "Time{" +
				"id=" + id +
				", data=" + data +
				'}';
	}
}
