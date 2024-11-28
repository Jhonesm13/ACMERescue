package com.acmerescue.demo;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class EmpresaAtendimentosRepoJdbcImpl implements IEmpresaAtendimentosRepository{
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public EmpresaAtendimentosRepoJdbcImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Atendimento> getAtendimentos() {
        List<Atendimento> resp = this.jdbcTemplate.query("SELECT * FROM atendimentos",
        (rs, rowNum) -> new Atendimento(rs.getInt("cod"), 
                        rs.getString("status"),
                        rs.getDate("inicio"), 
                        rs.getInt("duracao")));
        return resp;
    }

    @Override
    public boolean cadastrarAtendimento(Atendimento atendimento, Long codigoEvento) {
        this.jdbcTemplate.update(
                "INSERT INTO atendimentos(cod,duracao,status,inicio, codigo_evento) VALUES (?,?,?,?,?)",
                atendimento.getCod(), 
                atendimento.getDuracao(), 
                atendimento.getStatus(), 
                atendimento.getInicio(),
                codigoEvento);
        return true;
    }

  

    @Override   
    public List<Atendimento> listaAtendimentosPorStatus(String status) {
        List<Atendimento> resp = this.jdbcTemplate.query("SELECT * FROM atendimentos WHERE status = ?",
                (rs, rowNum) -> new Atendimento(rs.getInt("cod"), rs.getString("status"), rs.getDate("inicio"),rs.getInt("duracao")),status);
        return resp;

    }

    @Override
    public boolean vinculaAtendimentoEquipe(int codAtendimento, int numeroEquipe) {
        String buscaAtendimento = "SELECT * FROM atendimentos WHERE cod = ?";
        List<Atendimento> atendimentos = jdbcTemplate.query(
            buscaAtendimento,
            (rs, rowNum) -> new Atendimento(
                rs.getInt("cod"),
                rs.getString("status"),
                rs.getDate("inicio"),
                rs.getInt("duracao")
            ),
            codAtendimento
        );
        
        String buscaEquipe = "SELECT * FROM equipes WHERE numero = ?";
        List<Equipe> equipes = jdbcTemplate.query(
            buscaEquipe,
            (rs, rowNum) -> new Equipe(
                rs.getLong("numero"),
                rs.getInt("quantidade_membros"),
                rs.getDouble("latitude"),
                rs.getDouble("longitude")
            ),
            numeroEquipe
        );
        
        String sql = "INSERT INTO equipe_atendimento (equipe_id, cod_atendimento) VALUES (?, ?)";
        jdbcTemplate.update(sql, numeroEquipe, codAtendimento);
        return true;
    }

    @Override
    public Atendimento getAtendimentoPorCodigo(int codigo) {
        List<Atendimento> resp = this.jdbcTemplate.query(
            "SELECT * FROM atendimentos WHERE cod = ?",
            (rs, rowNum) -> new Atendimento(
                rs.getInt("cod"), 
                rs.getString("status"),
                rs.getDate("inicio"),
                rs.getInt("duracao")
            ),
            codigo
        );
        return resp.get(0);
    }

    @Override
    public void atualizarStatus(int codigo, String novoStatus) {
        this.jdbcTemplate.update(
            "UPDATE atendimentos SET status = ? WHERE cod = ?",
            novoStatus, codigo
        );
    }

    @Override
    public List<Atendimento> listarAtendimentoPorEquipe(int numero){
        String sql = """
            SELECT a.cod, a.duracao, a.status, a.inicio
            FROM atendimentos a
            INNER JOIN equipe_atendimento atend ON a.cod = atend.cod_atendimento
            WHERE atend.equipe_id = ?
        """;
        return jdbcTemplate.query(sql,
            (rs, rowNum) -> new Atendimento(
                rs.getInt("cod"),
                rs.getString("Status"),
                rs.getDate("inicio"),
                rs.getInt("duracao")),
                numero
        );
    }

}
