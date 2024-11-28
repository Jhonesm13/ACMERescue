package com.acmerescue.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
public class EmpresaEquipeRepositoryJdbcImpl implements IEquipeRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmpresaEquipeRepositoryJdbcImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Equipe> listarEquipes() {
        return this.jdbcTemplate.query(
                "SELECT * FROM equipes", 
                (rs, rowNum) -> new Equipe(
                        rs.getLong("numero"), 
                        rs.getInt("quantidade_membros"), 
                        rs.getDouble("latitude"), 
                        rs.getDouble("longitude")
                )
        );
    }

    @Override
    public boolean cadastrarEquipe(Equipe equipe) {
        this.jdbcTemplate.update(
                "INSERT INTO equipes(numero, quantidade_membros, latitude, longitude) VALUES (?, ?, ?, ?)",
                equipe.getNumero(), 
                equipe.getQuantidadeMembros(), 
                equipe.getLatitude(), 
                equipe.getLongitude()
        );
        return true;
    }

    @Override
    public boolean removerEquipe(Long numero) {
        this.jdbcTemplate.update(
                "DELETE FROM equipes WHERE numero = ?",
                numero
        );
        return true;
    }
}
