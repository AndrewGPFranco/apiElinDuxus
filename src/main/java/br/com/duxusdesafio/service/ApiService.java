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

        List<String> integrantesTime = new ArrayList<>();

        for (Time time : todosOsTimes) {
            if (time.getData().equals(data)) {
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
    public List<String> timeMaisComum(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        return null;
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
                    Integrante integrante = composicao.getIntegrante();
                    String funcao = integrante.getFuncao();
                    contagemFuncoes.put(funcao, contagemFuncoes.getOrDefault(funcao, 0) + 1);
                }
            }
        }

        // Encontrando a função mais utilizadas
        String funcaoMaisComum = "";
        int maiorContagem = 0;
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
        // TODO Implementar método seguindo as instruções!
        return null;
    }


    /**
     * Vai retornar o nome da Franquia mais comum nos times dentro do período
     */
    public Map<String, Long> contagemPorFranquia(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        // TODO Implementar método seguindo as instruções!
        return null;
    }

    /**
     * Vai retornar o número (quantidade) de Funções dentro do período
     */
    public Map<String, Long> contagemPorFuncao(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        // TODO Implementar método seguindo as instruções!
        return null;
    }

}
