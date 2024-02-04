package br.com.duxusdesafio.service;

import br.com.duxusdesafio.model.ComposicaoTime;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.repository.ComposicaoTimeRepository;
import br.com.duxusdesafio.repository.IntegranteRepository;
import br.com.duxusdesafio.repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CadastroService {
    @Autowired
    private IntegranteRepository integranteRepository;

    @Autowired
    private TimeRepository timeRepository;

    @Autowired
    private ComposicaoTimeRepository composicaoTimeRepository;

    public Integrante cadastrarIntegrante(String franquia, String nome, String funcao) {
        Integrante integrante = new Integrante();
        integrante.setNome(nome);
        integrante.setFranquia(franquia);
        integrante.setFuncao(funcao);

        integranteRepository.save(integrante);

        return integrante;
    }

    @SuppressWarnings("null")
    public Time cadastrarTime(LocalDate data, List<Long> integrantesIds) {
        Time time = new Time();
        time.setData(data);

        timeRepository.save(time);

        for (Long integranteId : integrantesIds) {
            ComposicaoTime composicaoTime = new ComposicaoTime();
            composicaoTime.setTime(time);
            composicaoTime.setIntegrante(integranteRepository.findById(integranteId).get());

            composicaoTimeRepository.save(composicaoTime);
        }

        return time;
    }
}