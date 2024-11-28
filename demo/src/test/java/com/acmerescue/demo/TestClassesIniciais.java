package com.acmerescue.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestClassesIniciais {

	@Test
	public void testAtendimento() {
    	Atendimento atendimento = new Atendimento(1, "Em andamento", Date.valueOf("2024-11-21"), 120);
    	assertEquals(1, atendimento.getCod());
    	assertEquals("Em andamento", atendimento.getStatus());
    	assertEquals(Date.valueOf("2024-11-21"), atendimento.getInicio());
    	assertEquals(120, atendimento.getDuracao());
	}

	@Test
	public void testEquipamento() {
		Equipamento equipamento = new Equipamento(1, "Equipamento 1", 150.55);
		assertEquals(1, equipamento.getId());
		assertEquals("Equipamento 1", equipamento.getNome());
		assertEquals(150.55, equipamento.getCustoDiario());
		assertEquals("Equipamento [id=1, nome=Equipamento 1, custo diario=150.55]", equipamento.toString());
	}

	@Test
	public void testEquipe() {
		Equipe equipe = new Equipe(1L, 5, -30.034, -51.223);
		assertEquals(1L, equipe.getNumero());
		assertEquals(5, equipe.getQuantidadeMembros());
		assertEquals(-30.034, equipe.getLatitude());
		assertEquals(-51.223, equipe.getLongitude());
	}

	@Test
	public void testEvento() {
		Evento evento = new Evento(1L, "Desastre natural", "2024-11-21", -30.034, -51.223);
		assertEquals(1L, evento.getCodigo());
		assertEquals("Desastre natural", evento.getDescricao());
		assertEquals("2024-11-21", evento.getData());
		assertEquals(-30.034, evento.getLatitude());
		assertEquals(-51.223, evento.getLongitude());
		assertEquals("Evento{codigo=1, descricao='Desastre natural', data='2024-11-21', latitude=-30.034, longitude=-51.223}", evento.toString());
	}



}
