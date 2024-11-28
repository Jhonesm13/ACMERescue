package com.acmerescue.demo;

import java.util.List;

public interface IEmpresaAtendimentosRepository {
    List<Atendimento> getAtendimentos();
    boolean cadastrarAtendimento(Atendimento atendimento, Long codigoEvento);
    List<Atendimento> listaAtendimentosPorStatus(String status);
    boolean vinculaAtendimentoEquipe(int codAtendimento, int numeroEquipe);
    Atendimento getAtendimentoPorCodigo(int codigo);
    void atualizarStatus(int codigo, String novoStatus);
    List<Atendimento> listarAtendimentoPorEquipe(int numero);
}
