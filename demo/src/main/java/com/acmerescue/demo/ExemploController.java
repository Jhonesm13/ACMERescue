package com.acmerescue.demo;

import java.util.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/acmerescue")
public class ExemploController {
    private IEmpresaEquipamentosRepository equipamentos;
    private IEmpresaAtendimentosRepository atendimentos;
    private IEquipeRepository equipeRepository;
    private IEmpresaEventoRepository eventos;

    @Autowired
    public ExemploController(IEmpresaEquipamentosRepository equipamentos, IEmpresaAtendimentosRepository atendimentos, IEquipeRepository equipeRepository, IEmpresaEventoRepository eventos) {
        this.equipamentos = equipamentos;            
        this.atendimentos = atendimentos;        
        this.equipeRepository = equipeRepository;
        this.eventos = eventos;
    }

    @GetMapping("")
    public String getMensagemInicial() {
        return "Aplicacao Spring-Boot funcionando!";
    }

    @GetMapping("/cadastro/listaequipamentos")
    public List<Equipamento> getEquipamentos() {
        return equipamentos.getEquipamentos();
    }

    @PostMapping("/cadastro/novoequipamento")
    public ResponseEntity<String> cadastraEquipamentoNovo(@RequestBody final Equipamento equipamento) {
        boolean existeEquipamento = equipamentos.getEquipamentos().stream()
                .anyMatch(e -> e.getId() == equipamento.getId()); 
        if (existeEquipamento) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Equipamento já cadastrado.");
        }

        boolean result = equipamentos.cadastraEquipamentoNovo(equipamento);
        if (result) {
            return ResponseEntity.ok("Equipamento cadastrado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar equipamento.");
        }
    }

    @GetMapping("/cadastro/removeequipamento")
    public boolean removeEquipamento(@RequestParam(value = "id") int id) {
        return equipamentos.removeEquipamento(id);
    }



    @PostMapping("/validaequipamento")
    public ResponseEntity<Boolean> validaEquipamento(@RequestBody Map<String, Integer> payload) {
        Integer id = payload.get("idEquipamento");
        boolean valido = equipamentos.getEquipamentos().stream()
                        .anyMatch(e -> e.getId() == id);
        return ResponseEntity.ok(valido);
    }

    @PostMapping("/cadastro/cadvinculo")
    public ResponseEntity<Boolean> vincularEquipamento(@RequestBody Map<String, Integer> payload) {
        int idEquipamento = payload.get("idEquipamento");
        int numeroEquipe = payload.get("numeroEquipe");

        boolean sucesso = equipamentos.vincularEquipamentoEquipe(idEquipamento, numeroEquipe);
        return ResponseEntity.ok(sucesso);
    }

    @GetMapping("/equipe/equipamento/{numero}")
    public ResponseEntity<List<Equipamento>> listarEquipamentosPorEquipe(@PathVariable int numero) {
        List<Equipamento> equipamentosDaEquipe = equipamentos.listarEquipamentosPorEquipe(numero);
        if (equipamentosDaEquipe.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
        return ResponseEntity.ok(equipamentosDaEquipe);
    }

    @GetMapping("/cadastro/listaequipes")
    public List<Equipe> listarEquipes() {
        return equipeRepository.listarEquipes();
    }

    @GetMapping("/cadastro/listaeventos")
        public List<Evento> listarEventos() {
        return eventos.listarEventos();
    }

    @GetMapping("/cadastro/listaatendimentos")
    public List<Atendimento> getAtendimentos() {
        return atendimentos.getAtendimentos();
    }

    @PostMapping("/cadastro/cadevento")
    public boolean cadastrarEvento(@RequestBody Evento evento) {
        return eventos.cadastrarEvento(evento);
    }


    @DeleteMapping("/cadastro/removerevento")
    public boolean removerEvento(@RequestParam("codigo") long codigo) {
        return eventos.removerEvento(codigo);
    }

    @PostMapping("/cadastro/cadatendimento")
    public boolean cadastrarAtendimento(@RequestBody final Atendimento atendimento, Long codigoEvento) {
        return atendimentos.cadastrarAtendimento(atendimento, codigoEvento);
    }

    @GetMapping("/atendimento")
    public List<Atendimento> getAtendimentosPorStatus(@RequestParam(value = "status") String status) {
        return atendimentos.listaAtendimentosPorStatus(status);
    }

    @PostMapping("/validaatendimento")
    public ResponseEntity<Boolean> validaAtendimento(@RequestBody Map<String, Integer> payload) {
        Integer id = payload.get("cod");
        boolean valido = atendimentos.getAtendimentos().stream()
                        .anyMatch(e -> e.getCod() == id);
        return ResponseEntity.ok(valido);
        
    }

    @PostMapping("processo/alocaatendimento")
    public ResponseEntity<Boolean> vinculaAtendimentoEquipe(@RequestBody Map<String, Integer> payload) {
        int codAtendimento = payload.get("cod");
        int numeroEquipe = payload.get("numeroEquipe");

        boolean sucesso = atendimentos.vinculaAtendimentoEquipe(codAtendimento, numeroEquipe);
        return ResponseEntity.ok(sucesso);
    }

    @PostMapping("/atendimento/{codigo}")
    public ResponseEntity<Atendimento> atualizarStatusAtendimento(
            @PathVariable("codigo") int codigoAtendimento,
            @RequestBody Map<String, String> payload) {

        String novoStatus = payload.get("status");

        atendimentos.atualizarStatus(codigoAtendimento, novoStatus);

        Atendimento atendimentoAtualizado = atendimentos.getAtendimentoPorCodigo(codigoAtendimento);
        return ResponseEntity.ok(atendimentoAtualizado);
    }

    @GetMapping("/atendimento/custo/{codigo}")
    public ResponseEntity<String> calcularCustoAtendimento(@PathVariable Long codigo) {
        return ResponseEntity.ok("Não consegui fazer o cálculo :(");
    }

    @PostMapping("/validaequipe")
    public ResponseEntity<Boolean> validarEquipe(@RequestBody Map<String, Long> payload) {
        Long id = payload.get("idEquipe");
        boolean valido = equipeRepository.listarEquipes().stream()
                        .anyMatch(e -> e.getNumero() == id);
        return ResponseEntity.ok(valido);
    }
    
    @PostMapping("/validaevento")
        public ResponseEntity<Boolean> validaEvento(@RequestBody Map<String, Long> payload) {
        Long codigoEvento = payload.get("codigoEvento");

        boolean valido = eventos.listarEventos().stream()
                        .anyMatch(evento -> evento.getCodigo() == codigoEvento);

        return ResponseEntity.ok(valido);
    }

    @GetMapping("/equipe/atendimento/{numero}")
    public ResponseEntity<List<Atendimento>> listarAtendimentoPorEqui(@PathVariable int numero){
        List<Atendimento> atendimentoEquipe = atendimentos.listarAtendimentoPorEquipe(numero);
        if(atendimentoEquipe.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
        return ResponseEntity.ok(atendimentoEquipe);
    }

}