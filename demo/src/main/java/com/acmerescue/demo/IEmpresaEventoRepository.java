package com.acmerescue.demo;

import java.util.List;

public interface IEmpresaEventoRepository {
    List<Evento> listarEventos();
    boolean cadastrarEvento(Evento evento);
    boolean removerEvento(long codigo);
}