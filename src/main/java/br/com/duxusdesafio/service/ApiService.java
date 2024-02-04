package br.com.duxusdesafio.service;

import br.com.duxusdesafio.model.ComposicaoTime;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service que possuirá as regras de negócio para o processamento dos dados
 * solicitados no desafio!
 *
 * @author carlosau
 */
@Service
public class ApiService {

    /**
     * Retorna uma lista com os nomes dos integrantes do time na data especificada.
     *
     * @param data A data para a qual se deseja obter os integrantes do time.
     * @param todosOsTimes Lista contendo todos os times disponíveis.
     * @return Lista de nomes dos integrantes do time na data fornecida.
     */
    public List<String> timeDaData(LocalDate data, List<Time> todosOsTimes) {

        // Definindo um novo Array
        List<String> integrantesTime = new ArrayList<>();

        // Iterando em cada time
        for (Time time : todosOsTimes) {
            // Logica para ver se a data do time é igual a data definida pelo usuario
            if (time.getData().equals(data)) {
                // Iterando a composição
                for (ComposicaoTime composicaoTime : time.getComposicaoTime()) {
                    integrantesTime.add(composicaoTime.getIntegrante().getNome());
                }
            }
        }

        return integrantesTime;
    }


    /**
     * Vai retornar o integrante que tiver presente na maior quantidade de times
     * dentro do período
     */
    public Integrante integranteMaisUsado(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes) {
        // Map responsavel por armazenar a quantidade de vezes que o integrante aparece nos times
        Map<Integrante, Integer> contagemIntegrante = new HashMap<>();

        // Percorrendo todos os times
        for (Time time : todosOsTimes) {
            // condição para verificar se as datas estão entre a data inicial e final
            if(time.getData().isBefore(dataFinal) && time.getData().isAfter(dataInicial)) {
                // percorrendo os integrantes do time
                for (ComposicaoTime composicao : time.getComposicaoTime()) {
                    // Salvando cada integrante na variavel
                    Integrante integrante = composicao.getIntegrante();
                    // adicionando o integrante no mapa e contabilizando
                    contagemIntegrante.put(integrante, contagemIntegrante.getOrDefault(integrante, 0) + 1);
                }
            }
        }

        // iniciando as variaveis
        Integrante integranteMaisUsado = null;
        int maiorContagem = 0;

        // Percorrendo o mapa
        for (Map.Entry<Integrante, Integer> entry : contagemIntegrante.entrySet()) {
            // Verificando se o valor atual é maior que o anterior
            if (entry.getValue() > maiorContagem) {
                // Caso sejá, atualiza a variavel
                maiorContagem = entry.getValue();
                integranteMaisUsado = entry.getKey();
            }
        }

        // retorna o integrante mais usado
        return integranteMaisUsado;
    }

    /**
     * Vai retornar uma lista com os nomes dos integrantes do time mais comum
     * dentro do período
     */
    public List<String> timeMaisComum(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes) {

        // Mapa para armazenar os integrantes
        Map<String, Integer> integrantes = new HashMap<>();

        // Iterar por todos os times
        for (Time time : todosOsTimes) {

            // Salvar o nome dos integrantes dos times
            List<ComposicaoTime> nomesIntegrantes = time.getComposicaoTime();

            // Atualizar o mapa
            for (ComposicaoTime nome : nomesIntegrantes) {
                integrantes.put(String.valueOf(nome), integrantes.getOrDefault(nome, 0) + 1);
            }
        }

        // Encontrar o integrante com mais aparição
        String nomeMaisComum = null;
        int maiorQuantidade = 0;
        // Determinando o nome mais comum nos times
        for (Map.Entry<String, Integer> entry : integrantes.entrySet()) {
            if (entry.getValue() > maiorQuantidade) {
                nomeMaisComum = entry.getKey();
                maiorQuantidade = entry.getValue();
            }
        }

        // Criar e retornar a lista com os nomes dos integrantes do time mais comum
        List<String> timeMaisComum = new ArrayList<>();
        for (String nome : integrantes.keySet()) {
            if (integrantes.get(nome) == maiorQuantidade) {
                timeMaisComum.add(nome);
            }
        }

        return timeMaisComum;
    }


    /**
     * Vai retornar a função mais comum nos times dentro do período
     */
    public String funcaoMaisComum(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes) {
        // Criando um mapa
        Map<String, Integer> contagemFuncoes = new HashMap<>();

        // Percorrer a lista de times entre as datas
        for (Time time : todosOsTimes) {
            if (dataInicial == null || dataFinal == null || (dataInicial.compareTo(time.getData()) <= 0 && dataFinal.compareTo(time.getData()) >= 0)) {
                // Obtendo os integrantes
                List<ComposicaoTime> composicaoTime = time.getComposicaoTime();
                // Incrementando
                for (ComposicaoTime composicao : composicaoTime) {
                    // Salvando os integrantes em uma variavel
                    Integrante integrante = composicao.getIntegrante();
                    // Salvando a funcao de cada integrante
                    String funcao = integrante.getFuncao();
                    // Adicionando as funções ao mapa
                    contagemFuncoes.put(funcao, contagemFuncoes.getOrDefault(funcao, 0) + 1);
                }
            }
        }

        // Encontrando a função mais utilizadas
        String funcaoMaisComum = "";
        int maiorContagem = 0;
        // Iterando o MAPA e definindo a função mais comum
        for (Map.Entry<String, Integer> entry : contagemFuncoes.entrySet()) {
            if (entry.getValue() > maiorContagem) {
                maiorContagem = entry.getValue();
                funcaoMaisComum = entry.getKey();
            }
        }

        return funcaoMaisComum;
    }


    /**
     * Vai retornar o nome da Franquia mais comum nos times dentro do período
     */
    public String franquiaMaisFamosa(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes) {
        // Criando um mapa para contar as franquias
        Map<String, Integer> contagemFranquias = new HashMap<>();

        // Iterando todos os times
        for(Time time : todosOsTimes) {
            // Verificando algumas informações sobre as datas
            if (dataInicial == null || dataFinal == null ||
                    (dataInicial.compareTo(time.getData()) <= 0
                            && dataFinal.compareTo(time.getData()) >= 0)) {
                // Definindo uma lista com as composições dos times
                List<ComposicaoTime> composicaoTime = time.getComposicaoTime();
                // Iterando todas as composições
                for(ComposicaoTime composicao : composicaoTime) {
                    // Salvando o integrante em uma variavel
                    Integrante integrante = composicao.getIntegrante();
                    // Salvando a franquia de cada integrante
                    String franquia = integrante.getFranquia();
                    contagemFranquias.put(franquia, contagemFranquias.getOrDefault(franquia, 0) + 1);
                }
            }
        }

        String franquiaMaisFamosa = "";
        int maiorContagem = 0;
        // Definindo a franquia mais famosa com base no MAPA
        for (Map.Entry<String, Integer> entry : contagemFranquias.entrySet()) {
            if (entry.getValue() > maiorContagem) {
                maiorContagem = entry.getValue();
                franquiaMaisFamosa = entry.getKey();
            }
        }

        return franquiaMaisFamosa;
    }


    /**
     * Vai retornar o nome da Franquia mais comum nos times dentro do período
     */
    public Map<String, Long> contagemPorFranquia(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        // Criando um mapa para contar as franquias
        Map<String, Long> contagemFranquias = todosOsTimes.stream()
                .filter(time -> dataInicial == null || dataFinal == null ||
                        (dataInicial.compareTo(time.getData()) <= 0 && dataFinal.compareTo(time.getData()) >= 0))
                .flatMap(time -> time.getComposicaoTime().stream())
                .map(ComposicaoTime::getIntegrante)
                .filter(integrante -> integrante.getFranquia() != null)
                .collect(Collectors.groupingBy(Integrante::getFranquia, Collectors.counting()));

        return contagemFranquias;
    }

    /**
     * Vai retornar o número (quantidade) de Funções dentro do período
     */
    public Map<String, Long> contagemPorFuncao(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        // Criando um mapa para contar as funções
        Map<String, Long> contagemFuncoes = todosOsTimes.stream()
                // Filtrando os times dentro do período das datas
                .filter(time -> dataInicial == null || dataFinal == null ||
                        (dataInicial.compareTo(time.getData()) <= 0 && dataFinal.compareTo(time.getData()) >= 0))
                // Aplanando a lista de composições e integrantes
                .flatMap(time -> time.getComposicaoTime().stream())
                // Obtendo os integrantes
                .map(ComposicaoTime::getIntegrante)
                // Filtrando integrantes com função não nula
                .filter(integrante -> integrante.getFuncao() != null)
                // Contando as ocorrências de cada função
                .collect(Collectors.groupingBy(Integrante::getFuncao, Collectors.counting()));

        return contagemFuncoes;
    }
}
