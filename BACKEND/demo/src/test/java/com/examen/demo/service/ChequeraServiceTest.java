package com.examen.demo.service;

import com.examen.demo.model.Chequera;
import com.examen.demo.model.Cuenta;
import com.examen.demo.repository.ChequeraRepository;
import com.examen.demo.repository.CuentaRepository;
import com.examen.demo.response.ChequeraResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ChequeraServiceTest {

    @Mock
    private ChequeraRepository chequeraRepository;

    @Mock
    private CuentaRepository cuentaRepository;

    @InjectMocks
    private ChequeraService chequeraService;

    private Cuenta cuenta;
    private Chequera chequera;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        cuenta = new Cuenta();
        cuenta.setIdCuenta(1);

        chequera = new Chequera();
        chequera.setIdChequera(1);
        chequera.setCuenta(cuenta);
        chequera.setCantidadCheques(50);
    }

    @Test
    void testGuardarChequeraConCuentaValida() {
        when(cuentaRepository.findById(1)).thenReturn(Optional.of(cuenta));
        when(chequeraRepository.save(chequera)).thenReturn(chequera);

        ChequeraResponse response = chequeraService.guardar(chequera);

        assertTrue(response.getSuccessful());
        assertEquals(1, response.getChequeras().size());
        assertEquals(50, response.getChequeras().get(0).getCantidadCheques());
    }

    @Test
    void testGuardarChequeraConCuentaInvalida() {
        when(cuentaRepository.findById(1)).thenReturn(Optional.empty());

        ChequeraResponse response = chequeraService.guardar(chequera);

        assertFalse(response.getSuccessful());
        assertFalse(response.getErrores().isEmpty());
    }

    @Test
    void testListarChequerasPorCuentaIdExiste() {
        when(cuentaRepository.existsById(1)).thenReturn(true);
        when(chequeraRepository.findByCuentaIdCuenta(1)).thenReturn(List.of(chequera));

        ChequeraResponse response = chequeraService.listarPorCuentaId(1);

        assertTrue(response.getSuccessful());
        assertEquals(1, response.getChequeras().size());
    }

    @Test
    void testListarChequerasPorCuentaIdNoExiste() {
        when(cuentaRepository.existsById(1)).thenReturn(false);

        ChequeraResponse response = chequeraService.listarPorCuentaId(1);

        assertFalse(response.getSuccessful());
        assertFalse(response.getErrores().isEmpty());
    }
}
