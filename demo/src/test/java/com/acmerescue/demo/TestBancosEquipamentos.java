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
public class TestBancosEquipamentos {

    @Autowired
    private EmpresaEquipamentosRepoJdbcImpl repo;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void setUp() {
        jdbcTemplate.execute("DELETE FROM equipamentos");
    }

    @Test
    public void testCadastraEquipamentoNovo() {
        Equipamento equipamento = new Equipamento(1, "Equipamento1", 100.0);
        boolean result = repo.cadastraEquipamentoNovo(equipamento);
        assertTrue(result);

        String query = "SELECT count(*) FROM equipamentos WHERE nome = ?";
        int count = jdbcTemplate.queryForObject(query, Integer.class, "Equipamento1");
        assertEquals(1, count);
    }

    @Test
    public void testGetEquipamentos() {
        jdbcTemplate.update("INSERT INTO equipamentos (id, nome, custoDiario) VALUES (?, ?, ?)",
                101, "Equipamento1", 100.0);
        List<Equipamento> equipamentos = repo.getEquipamentos();

        assertEquals("Equipamento1", equipamentos.get(0).getNome());
        assertEquals(100.0, equipamentos.get(0).getCustoDiario());
    }

    @Test
    public void testRemoveEquipamento() {
        jdbcTemplate.update("INSERT INTO equipamentos (id, nome, custoDiario) VALUES (?, ?, ?)",
                1, "Equipamento1", 100.0);

        boolean result = repo.removeEquipamento(1);
        assertTrue(result);
    }

    @Test
    public void testListarEquipamentosPorEquipe() {
        jdbcTemplate.update("INSERT INTO equipamentos (id, nome, custoDiario) VALUES (?, ?, ?)",
                1, "Equipamento1", 100.0);
        jdbcTemplate.update("INSERT INTO equipe_equipamentos (equipe_id, equipamento_id) VALUES (?, ?)",
                1, 1);

        List<Equipamento> equipamentos = repo.listarEquipamentosPorEquipe(1);

        assertEquals("Equipamento1", equipamentos.get(0).getNome());
        assertEquals(100.0, equipamentos.get(0).getCustoDiario(), 0.01);
    }

    @Test
    public void testBuscarEquipamentoPorId() {
        jdbcTemplate.update("INSERT INTO equipamentos (id, nome, custoDiario) VALUES (?, ?, ?)",
                1, "Equipamento1", 100.0);

        Equipamento equipamento = repo.buscarEquipamentoPorId(1);

        assertEquals(1, equipamento.getId());
        assertEquals("Equipamento1", equipamento.getNome());
        assertEquals(100.0, equipamento.getCustoDiario(), 0.01);
    }

    @Test
    public void testVincularEquipamentoEquipe() {
        jdbcTemplate.update("INSERT INTO equipamentos (id, nome, custoDiario) VALUES (?, ?, ?)",
                102, "Equipamento1", 100.0);
        jdbcTemplate.update("INSERT INTO equipes (numero, quantidade_membros, latitude, longitude) VALUES (?, ?, ?, ?)",
                102, 5, 40.7128, -74.0060);

        boolean result = repo.vincularEquipamentoEquipe(102, 102);
        assertTrue(result);
    }

}
