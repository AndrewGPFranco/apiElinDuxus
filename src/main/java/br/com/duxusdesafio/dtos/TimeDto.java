package br.com.duxusdesafio.dtos;

import java.time.LocalDate;
import java.util.List;

public class TimeDto {

    private LocalDate data;
    private String nome;
    private List<Long> integrantesIds;

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Long> getIntegrantesIds() {
        return integrantesIds;
    }

    public void setIntegrantesIds(List<Long> integrantesIds) {
        this.integrantesIds = integrantesIds;
    }

}
