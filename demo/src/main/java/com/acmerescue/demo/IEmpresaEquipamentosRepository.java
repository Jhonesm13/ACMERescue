package com.acmerescue.demo;

import java.util.List;

public interface IEmpresaEquipamentosRepository {
    List<Equipamento> getEquipamentos();
    boolean cadastraEquipamentoNovo(Equipamento equipamento);
    boolean removeEquipamento(int id);
    boolean vincularEquipamentoEquipe(int idEquipamento, int numeroEquipe);
    List<Equipamento> listarEquipamentosPorEquipe(int numero);
    Equipamento buscarEquipamentoPorId(int id);
}
