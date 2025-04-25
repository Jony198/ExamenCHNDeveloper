package com.examen.demo.repository;

import com.examen.demo.model.Cheque;
import com.examen.demo.model.Chequera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChequeRepository extends JpaRepository<Cheque, Integer> {

    List<Cheque> findByChequera(Chequera chequera);

    List<Cheque> findByChequeraIdChequera(Integer idChequera);

    List<Cheque> findByEstadoCheque(String estadoCheque);

    Cheque findByChequeraIdChequeraAndNumeroCheque(Integer idChequera, Integer numeroCheque);
}
