package com.examen.demo.service;

import com.examen.demo.entity.ErrorList;
import com.examen.demo.model.Cliente;
import com.examen.demo.repository.ClienteRepository;
import com.examen.demo.response.ClienteResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cliente = new Cliente();
        cliente.setIdCliente(1);
        cliente.setNombre("Carlos");
        cliente.setApellido("Pérez");
        cliente.setNumeroIdentificacion("1234567890101");
    }

    @Test
    void testGuardarClienteExitoso() {
        when(clienteRepository.existsByNumeroIdentificacion(cliente.getNumeroIdentificacion())).thenReturn(false);
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        ClienteResponse response = clienteService.guardar(cliente);

        assertTrue(response.getSuccessful());
        assertNotNull(response.getClientes());
        assertEquals(1, response.getClientes().size());
        assertEquals("Carlos", response.getClientes().get(0).getNombre());
    }

    @Test
    void testGuardarClienteDuplicado() {
        when(clienteRepository.existsByNumeroIdentificacion(cliente.getNumeroIdentificacion())).thenReturn(true);

        ClienteResponse response = clienteService.guardar(cliente);

        assertFalse(response.getSuccessful());
        assertTrue(response.getErrores().size() > 0);
    }

    @Test
    void testObtenerClientePorIdExitoso() {
        when(clienteRepository.findById(1)).thenReturn(Optional.of(cliente));

        ClienteResponse response = clienteService.obtenerPorId(1);

        assertTrue(response.getSuccessful());
        assertEquals(1, response.getClientes().get(0).getIdCliente());
    }

    @Test
    void testObtenerClientePorIdNoExiste() {
        when(clienteRepository.findById(2)).thenReturn(Optional.empty());

        ClienteResponse response = clienteService.obtenerPorId(2);

        assertFalse(response.getSuccessful());
        assertTrue(response.getErrores().size() > 0);
    }
}
