package com.acmerescue.demo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class EmpresaEventosRepoJdbcImpl implements IEmpresaEventoRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmpresaEventosRepoJdbcImpl (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Evento> listarEventos() {
        String sql = "SELECT * FROM eventos";
        return jdbcTemplate.query(sql, 
            (rs, rowNum) -> new Evento(
                rs.getLong("codigo"),
                rs.getString("descricao"),
                rs.getString("data"),
                rs.getDouble("latitude"),
                rs.getDouble("longitude")
            )
        );
    }

    @Override
    public boolean cadastrarEvento(Evento evento) {
        String sql = "INSERT INTO eventos (codigo, descricao, data, latitude, longitude) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, 
            evento.getCodigo(),
            evento.getDescricao(),
            evento.getData(),
            evento.getLatitude(),
            evento.getLongitude()
        );
        return true;
    }

    @Override
    public boolean removerEvento(long codigo) {
        String sql = "DELETE FROM eventos WHERE codigo = ?";
        jdbcTemplate.update(sql, codigo);
        return true;
    }
}