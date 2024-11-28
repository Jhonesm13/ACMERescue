package com.acmerescue.demo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class TestBancosAtendimentos {

    @Autowired
    private EmpresaAtendimentosRepoJdbcImpl repo;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void setUp() {
        jdbcTemplate.execute("DELETE FROM atendimentos");
        jdbcTemplate.execute("DELETE FROM equipe_atendimento");
    }

    @Test
    public void testCadastrarAtendimento() {
        Atendimento atendimento = new Atendimento(1, "Em andamento", java.sql.Date.valueOf("2024-11-21"), 30);
        boolean result = repo.cadastrarAtendimento(atendimento, 101L);
        assertTrue(result);
    }

    @Test
    public void testGetAtendimentos() {
        jdbcTemplate.update("INSERT INTO atendimentos (cod, status, inicio, duracao, codigo_evento) VALUES (?, ?, ?, ?, ?)",
                1, "Em andamento", java.sql.Date.valueOf("2024-11-21"), 30, 101L);

        List<Atendimento> atendimentos = repo.getAtendimentos();

        assertEquals(1, atendimentos.size());
        assertEquals(1, atendimentos.get(0).getCod());
        assertEquals("Em andamento", atendimentos.get(0).getStatus());
        assertEquals(30, atendimentos.get(0).getDuracao());
    }

    @Test
    public void testListaAtendimentosPorStatus() {
        jdbcTemplate.update("INSERT INTO atendimentos (cod, status, inicio, duracao, codigo_evento) VALUES (?, ?, ?, ?, ?)",
                1, "Em andamento", java.sql.Date.valueOf("2024-11-21"), 30, 101L);

        List<Atendimento> atendimentos = repo.listaAtendimentosPorStatus("Em andamento");

        assertEquals(1, atendimentos.size());
        assertEquals(1, atendimentos.get(0).getCod());
        assertEquals("Em andamento", atendimentos.get(0).getStatus());
    }

    @Test
    public void testVinculaAtendimentoEquipe() {
        jdbcTemplate.update("INSERT INTO atendimentos (cod, status, inicio, duracao, codigo_evento) VALUES (?, ?, ?, ?, ?)",
                1, "Em andamento", java.sql.Date.valueOf("2024-11-21"), 30, 101L);
        jdbcTemplate.update("INSERT INTO equipes (numero, quantidade_membros, latitude, longitude) VALUES (?, ?, ?, ?)",
                101, 5, 40.7128, -74.0060);

        boolean result = repo.vinculaAtendimentoEquipe(1, 101);
        assertTrue(result);
    }

    @Test
    public void testGetAtendimentoPorCodigo() {
        jdbcTemplate.update("INSERT INTO atendimentos (cod, status, inicio, duracao) VALUES (?, ?, ?, ?)",
                1, "Em andamento", java.sql.Date.valueOf("2024-11-21"), 30);

        Atendimento atendimento = repo.getAtendimentoPorCodigo(1);

        assertNotNull(atendimento);
        assertEquals(1, atendimento.getCod());
        assertEquals("Em andamento", atendimento.getStatus());
        assertEquals(30, atendimento.getDuracao());
    }

    @Test
    public void testAtualizarStatus() {
        jdbcTemplate.update("INSERT INTO atendimentos (cod, status, inicio, duracao) VALUES (?, ?, ?, ?)",
                1, "Em andamento", java.sql.Date.valueOf("2024-11-21"), 30);

        repo.atualizarStatus(1, "Cancelado");

        Atendimento atendimentoAtualizado = repo.getAtendimentoPorCodigo(1);
        assertNotNull(atendimentoAtualizado);
        assertEquals("Cancelado", atendimentoAtualizado.getStatus());
    }

    @Test
    public void testListarAtendimentoPorEquipe() {
        jdbcTemplate.update("INSERT INTO equipes (numero, quantidade_membros, latitude, longitude) VALUES (?, ?, ?, ?)",
                101, 5, 40.7128, -74.0060);
        jdbcTemplate.update("INSERT INTO atendimentos (cod, status, inicio, duracao) VALUES (?, ?, ?, ?)",
                1, "Em andamento", java.sql.Date.valueOf("2024-11-21"), 30);
        jdbcTemplate.update("INSERT INTO equipe_atendimento (equipe_id, cod_atendimento) VALUES (?, ?)",
                101, 1);

        List<Atendimento> atendimentos = repo.listarAtendimentoPorEquipe(101);

        assertNotNull(atendimentos);
        assertEquals(1, atendimentos.size());
        assertEquals(1, atendimentos.get(0).getCod());
        assertEquals("Em andamento", atendimentos.get(0).getStatus());
        assertEquals(30, atendimentos.get(0).getDuracao());
    }

}