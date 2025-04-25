package com.examen.demo.service;

import com.examen.demo.model.Cheque;
import com.examen.demo.model.Chequera;
import com.examen.demo.repository.ChequeRepository;
import com.examen.demo.repository.ChequeraRepository;
import com.examen.demo.response.ChequeResponse;
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

class ChequeServiceTest {

    @Mock
    private ChequeRepository chequeRepository;

    @Mock
    private ChequeraRepository chequeraRepository;

    @InjectMocks
    private ChequeService chequeService;

    private Chequera chequera;
    private Cheque cheque;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        chequera = new Chequera();
        chequera.setIdChequera(1);

        cheque = new Cheque();
        cheque.setIdCheque(1);
        cheque.setNumeroCheque(1001);
        cheque.setChequera(chequera);
        cheque.setMonto(BigDecimal.valueOf(250.00));
    }

    @Test
    void testGuardarChequeConChequeraValida() {
        when(chequeraRepository.findById(1)).thenReturn(Optional.of(chequera));
        when(chequeRepository.save(cheque)).thenReturn(cheque);

        ChequeResponse response = chequeService.guardar(cheque);

        assertTrue(response.getSuccessful());
        assertEquals(1, response.getCheques().size());
        assertEquals(1001, response.getCheques().get(0).getNumeroCheque());
    }

    @Test
    void testGuardarChequeConChequeraInvalida() {
        when(chequeraRepository.findById(1)).thenReturn(Optional.empty());

        ChequeResponse response = chequeService.guardar(cheque);

        assertFalse(response.getSuccessful());
        assertFalse(response.getErrores().isEmpty());
    }

    @Test
    void testListarChequesPorChequeraIdValida() {
        when(chequeraRepository.existsById(1)).thenReturn(true);
        when(chequeRepository.findByChequeraIdChequera(1)).thenReturn(List.of(cheque));

        ChequeResponse response = chequeService.listarPorChequeraId(1);

        assertTrue(response.getSuccessful());
        assertEquals(1, response.getCheques().size());
    }

    @Test
    void testListarChequesPorChequeraIdInvalida() {
        when(chequeraRepository.existsById(1)).thenReturn(false);

        ChequeResponse response = chequeService.listarPorChequeraId(1);

        assertFalse(response.getSuccessful());
        assertFalse(response.getErrores().isEmpty());
    }
}
