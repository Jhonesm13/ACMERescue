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
public class TestBancosEventos {

    @Autowired
    private EmpresaEventosRepoJdbcImpl repo;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void setUp() {
        jdbcTemplate.execute("DELETE FROM eventos");
    }

    @Test
    public void testCadastrarEvento() {
        Evento evento = new Evento(1, "Evento1", "2024-11-21", 40.7128, -74.0060);
        boolean result = repo.cadastrarEvento(evento);
        assertTrue(result);
    }

    @Test
    public void testListarEventos() {
        jdbcTemplate.update("INSERT INTO eventos (codigo, descricao, data, latitude, longitude) VALUES (?, ?, ?, ?, ?)",
                1, "Evento1", "2024-11-21", 40.7128, -74.0060);
        List<Evento> eventos = repo.listarEventos();

        assertEquals("Evento1", eventos.get(0).getDescricao());
        assertEquals("2024-11-21", eventos.get(0).getData());
    }

    @Test
    public void testRemoverEvento() {
        jdbcTemplate.update("INSERT INTO eventos (codigo, descricao, data, latitude, longitude) VALUES (?, ?, ?, ?, ?)",
                1, "Evento1", "2024-11-21", 40.7128, -74.0060);

        boolean result = repo.removerEvento(1);
        assertTrue(result);
    }

}
