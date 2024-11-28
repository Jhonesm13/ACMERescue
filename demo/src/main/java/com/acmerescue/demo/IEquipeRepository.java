package com.acmerescue.demo;

import java.util.List;

public interface IEquipeRepository {
    List<Equipe> listarEquipes();
    boolean cadastrarEquipe(Equipe equipe);
    boolean removerEquipe(Long numero);
}
