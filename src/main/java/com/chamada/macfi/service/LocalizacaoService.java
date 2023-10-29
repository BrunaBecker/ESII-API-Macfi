package com.chamada.macfi.service;

import com.chamada.macfi.exception.EntidadeNaoEncontradaException;
import com.chamada.macfi.model.Localizacao;
import com.chamada.macfi.repository.LocalizacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalizacaoService {

    @Autowired
    private LocalizacaoRepository localizacaoRepository;

    public Localizacao cadastraLocalizacao(Localizacao localizacao) { return localizacaoRepository.save(localizacao); }

    public void removerLocalizacao(Long id){
        recuperaLocalizacaoPorId(id);
        localizacaoRepository.deleteById(id);
    }

    public Localizacao recuperaLocalizacaoPorId(Long id){
        return localizacaoRepository.findByID(id);
    }

    public Localizacao recuperaLocalizacaoPorSIAPE(Integer SIAPE) {
        return localizacaoRepository.findBySIAPE(SIAPE);
    }

    public Localizacao recuperaLocalizacaoPorCEP(Integer CEP) {
        return localizacaoRepository.findByCEP(CEP);
    }

    public Localizacao atualizaLocalizacao(Localizacao localizacao){
        Localizacao umaLocalizacao = recuperaLocalizacaoPorId(localizacao.getId());
        if (!localizacao.getId().equals(umaLocalizacao.getId())){
            localizacaoRepository.findByID(localizacao.getId());
        }
        return localizacaoRepository.save(localizacao);
    }

    public List<Localizacao> recuperaLocalizacoes() { return localizacaoRepository.findAll(Sort.by("id")); }

}
