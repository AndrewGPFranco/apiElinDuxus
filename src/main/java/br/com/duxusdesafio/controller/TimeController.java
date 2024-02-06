package br.com.duxusdesafio.controller;

import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.repository.TimeRepository;
import br.com.duxusdesafio.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/times")
public class TimeController {
    @Autowired
    private ApiService apiService;

    @Autowired
    private TimeRepository timeRepository;

    @GetMapping("/timenadata")
    public ResponseEntity<Map<String, Object>> obterTimeNaData(
            @RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {

        List<Time> todosOsTimes = timeRepository.findAll();
        List<String> timeNaData = apiService.timeDaData(data, todosOsTimes);

        Map<String, Object> response = new HashMap<>();
        response.put("data", data);
        response.put("integrantes", timeNaData);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/integrantemaisusado")
    public ResponseEntity<Map<String, Object>> obterIntegranteMaisUsado(
            @RequestParam("dataInicial") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam("dataFinal") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal) {
        List<Time> todosOsTimes = timeRepository.findAll();
        Integrante integranteMaisUsado = apiService.integranteMaisUsado(dataInicial, dataFinal, todosOsTimes);

        Map<String, Object> response = new HashMap<>();
        response.put("Integrante Mais Usado", integranteMaisUsado.getNome());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/timemaiscomum")
    public ResponseEntity<Map<String, Object>> obterTimeMaisComum(
            @RequestParam("dataInicial") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam("dataFinal") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal) {

        List<Time> todosOsTimes = timeRepository.findAll();
        List<String> timeMaisComum = apiService.timeMaisComum(dataInicial, dataFinal, todosOsTimes);

        Map<String, Object> response = new HashMap<>();
        response.put("Time Mais comum", timeMaisComum);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/funcaomaiscomum")
    public ResponseEntity<Map<String, Object>> obterFuncaoMaisComum(
            @RequestParam("dataInicial") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam("dataFinal") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal) {

        List<Time> todosOsTimes = timeRepository.findAll();
        String funcaoMaisComum = apiService.funcaoMaisComum(dataInicial, dataFinal, todosOsTimes);

        Map<String, Object> response = new HashMap<>();
        response.put("Função mais comum", funcaoMaisComum);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/franquiamaisfamosa")
    public ResponseEntity<Map<String, Object>> obterFranquiaMaisFamosa(
            @RequestParam("dataInicial") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam("dataFinal") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal) {
        List<Time> todosOsTimes = timeRepository.findAll();
        String franquiaMaisFamosa = apiService.franquiaMaisFamosa(dataInicial, dataFinal, todosOsTimes);

        Map<String, Object> response = new HashMap<>();
        response.put("Franquia mais comum", franquiaMaisFamosa);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/contagemporfranquia")
    public ResponseEntity<Map<String, Long>> obterContagemPorFranquia(
            @RequestParam("dataInicial") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam("dataFinal") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal) {
        List<Time> todosOsTimes = timeRepository.findAll();
        Map<String, Long> contagemFranquias = apiService.contagemPorFranquia(dataInicial, dataFinal, todosOsTimes);

        return ResponseEntity.ok(contagemFranquias);
    }

    @GetMapping("/contagemporfuncao")
    public ResponseEntity<Map<String, Long>> obterContagemPorFuncao(
            @RequestParam("dataInicial") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam("dataFinal") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal) {
        List<Time> todosOsTimes = timeRepository.findAll();
        Map<String, Long> contagemFuncoes = apiService.contagemPorFuncao(dataInicial, dataFinal, todosOsTimes);

        return ResponseEntity.ok(contagemFuncoes);
    }
}