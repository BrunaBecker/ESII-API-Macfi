package com.chamada.macfi.controller;

import com.chamada.macfi.model.Localizacao;
import com.chamada.macfi.service.LocalizacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("localizacao")
public class LocalizacaoController {

    @Autowired
    private LocalizacaoService localizacaoService;

    @GetMapping
    public List<Localizacao> recuperaLocalizacoes() { return localizacaoService.recuperaLocalizacoes(); }

    @PostMapping
    public Localizacao cadastrarLocalizacao(@RequestBody Localizacao localizacao) { return localizacaoService.cadastraLocalizacao(localizacao); }

    @DeleteMapping("{idLocalizacao}")
    public void remnoverLocalizacao(@PathVariable("idLocalizacao") Long id){
        localizacaoService.removerLocalizacao(id);
    }


    @PutMapping
    public Localizacao atualizarLocalizacao(@RequestBody Localizacao localizacao){
        return localizacaoService.atualizaLocalizacao(localizacao);
    }
    
    @GetMapping("{id}")
    public Localizacao recuperaLocalizacaoPorId(@PathVariable("id") Long id){
        return localizacaoService.recuperaLocalizacaoPorId(id);
    }

    @GetMapping("{siape}")
    public Localizacao recuperaLocalizacaoPorId(@PathVariable("siape") Integer siape){
        return localizacaoService.recuperaLocalizacaoPorSIAPE(siape);
    }

    @GetMapping("{cep}")
    public Localizacao recuperaLocalizacaoPorCEP(@PathVariable("cep") Integer CEP) {
        return localizacaoService.recuperaLocalizacaoPorCEP(CEP);
    }

}
