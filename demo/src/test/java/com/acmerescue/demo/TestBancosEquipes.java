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
public class TestBancosEquipes {

    @Autowired
    private EmpresaEquipeRepositoryJdbcImpl repo;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void setUp() {
        jdbcTemplate.execute("DELETE FROM equipes");
    }

    @Test
    public void testCadastrarEquipe() {
        Equipe equipe = new Equipe(1L, 5, 40.7128, -74.0060);
        boolean result = repo.cadastrarEquipe(equipe);
        assertTrue(result);
    }

    @Test
    public void testListarEquipes() {
        jdbcTemplate.update("INSERT INTO equipes (numero, quantidade_membros, latitude, longitude) VALUES (?, ?, ?, ?)",
                1, 5, 40.7128, -74.0060);
        List<Equipe> equipes = repo.listarEquipes();

        assertEquals(1, equipes.size());
        assertEquals(1, equipes.get(0).getNumero());
        assertEquals(5, equipes.get(0).getQuantidadeMembros());
        assertEquals(40.7128, equipes.get(0).getLatitude(), 0.01);
        assertEquals(-74.0060, equipes.get(0).getLongitude(), 0.01);
    }

    @Test
    public void testRemoverEquipe() {
        jdbcTemplate.update("INSERT INTO equipes (numero, quantidade_membros, latitude, longitude) VALUES (?, ?, ?, ?)",
                1, 5, 40.7128, -74.0060);

        boolean result = repo.removerEquipe(1L);
        assertTrue(result);
    }
}