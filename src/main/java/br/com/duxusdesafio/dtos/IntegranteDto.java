package br.com.duxusdesafio.dtos;

public class IntegranteDto {
    private String nome;
    private String franquia;
    private String funcao;

    public String getNome() { return this.nome; }

    public String getFranquia() { return this.franquia; }

    public String getFuncao() { return this.funcao; }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setFranquia(String franquia) {
        this.franquia = franquia;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
}