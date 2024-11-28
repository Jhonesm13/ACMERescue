package com.acmerescue.demo;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class EmpresaEquipamentosRepoJdbcImpl implements IEmpresaEquipamentosRepository{
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public EmpresaEquipamentosRepoJdbcImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Equipamento> getEquipamentos() {
        List<Equipamento> resp = this.jdbcTemplate.query("SELECT * FROM equipamentos",
                (rs, rowNum) -> new Equipamento(rs.getInt("id"), rs.getString("nome"), rs.getDouble("custoDiario")));
        return resp;
    }

    @Override
    public boolean cadastraEquipamentoNovo(Equipamento equipamento) {
        this.jdbcTemplate.update(
                "INSERT INTO equipamentos(nome, custoDiario) VALUES (?, ?)",
                equipamento.getNome(), equipamento.getCustoDiario());
        return true;
    }

    @Override
    public boolean removeEquipamento(int id) {
        String sql = "DELETE FROM equipamentos WHERE id = ?";
        jdbcTemplate.update(sql, id);
        return true;
    }

    @Override
    public List<Equipamento> listarEquipamentosPorEquipe(int equipeNumero) {
        String sql = """
            SELECT e.id, e.nome, e.custoDiario
            FROM equipamentos e
            INNER JOIN equipe_equipamentos ee ON e.id = ee.equipamento_id
            WHERE ee.equipe_id = ?
        """;
        return jdbcTemplate.query(sql, 
            (rs, rowNum) -> new Equipamento(rs.getInt("id"), rs.getString("nome"), rs.getDouble("custoDiario")), 
            equipeNumero);
    }

    @Override
    public Equipamento buscarEquipamentoPorId(int id) {
        String sql = "SELECT * FROM equipamentos WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, 
                (rs, rowNum) -> new Equipamento(rs.getInt("id"), rs.getString("nome"), rs.getDouble("custoDiario")), 
                id);
    }

    @Override
    public boolean vincularEquipamentoEquipe(int idEquipamento, int numeroEquipe) {
        String buscaEquipamentoSql = "SELECT * FROM equipamentos WHERE id = ?";
        Equipamento equipamento = jdbcTemplate.queryForObject(buscaEquipamentoSql, 
            (rs, rowNum) -> new Equipamento(rs.getInt("id"), rs.getString("nome"), rs.getDouble("custoDiario")),
            idEquipamento);
    
        String buscaEquipeSql = "SELECT * FROM equipes WHERE numero = ?";
        Equipe equipe = jdbcTemplate.queryForObject(buscaEquipeSql, 
            (rs, rowNum) -> new Equipe(rs.getLong("numero"), rs.getInt("quantidade_membros"), rs.getDouble("latitude"), rs.getDouble("longitude")),
            numeroEquipe);
    
        String sql = "INSERT INTO equipe_equipamentos (equipe_id, equipamento_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, numeroEquipe, idEquipamento);
        return true;
    }    

    
}
