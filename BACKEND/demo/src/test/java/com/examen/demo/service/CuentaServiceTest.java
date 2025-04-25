package com.examen.demo.service;

import com.examen.demo.model.Cliente;
import com.examen.demo.model.Cuenta;
import com.examen.demo.repository.ClienteRepository;
import com.examen.demo.repository.CuentaRepository;
import com.examen.demo.response.CuentaResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CuentaServiceTest {

    @Mock
    private CuentaRepository cuentaRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private CuentaService cuentaService;

    private Cliente cliente;
    private Cuenta cuenta;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        cliente = new Cliente();
        cliente.setIdCliente(1);
        cliente.setNombre("Ana");
        cliente.setNumeroIdentificacion("9876543210101");

        cuenta = new Cuenta();
        cuenta.setIdCuenta(1);
        cuenta.setCliente(cliente);
        cuenta.setTipoCuenta("ahorro");
        cuenta.setMontoApertura(BigDecimal.valueOf(1000));
    }

    @Test
    void testGuardarCuentaConClienteExistente() {
        when(clienteRepository.findById(cliente.getIdCliente())).thenReturn(Optional.of(cliente));
        when(cuentaRepository.save(cuenta)).thenReturn(cuenta);

        CuentaResponse response = cuentaService.guardar(cuenta);

        assertTrue(response.getSuccessful());
        assertEquals(1, response.getCuentas().size());
        assertEquals("ahorro", response.getCuentas().get(0).getTipoCuenta());
    }

    @Test
    void testGuardarCuentaClienteNoExiste() {
        when(clienteRepository.findById(cliente.getIdCliente())).thenReturn(Optional.empty());

        CuentaResponse response = cuentaService.guardar(cuenta);

        assertFalse(response.getSuccessful());
        assertFalse(response.getErrores().isEmpty());
    }

    @Test
    void testListarPorClienteIdExitoso() {
        when(clienteRepository.existsById(1)).thenReturn(true);
        when(cuentaRepository.findByClienteIdCliente(1)).thenReturn(List.of(cuenta));

        CuentaResponse response = cuentaService.listarPorClienteId(1);

        assertTrue(response.getSuccessful());
        assertEquals(1, response.getCuentas().size());
    }

    @Test
    void testListarPorClienteIdNoExiste() {
        when(clienteRepository.existsById(1)).thenReturn(false);

        CuentaResponse response = cuentaService.listarPorClienteId(1);

        assertFalse(response.getSuccessful());
        assertFalse(response.getErrores().isEmpty());
    }
}
