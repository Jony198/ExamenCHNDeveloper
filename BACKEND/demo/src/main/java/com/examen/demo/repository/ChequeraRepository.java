package com.examen.demo.repository;

import com.examen.demo.model.Chequera;
import com.examen.demo.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChequeraRepository extends JpaRepository<Chequera, Integer> {

    List<Chequera> findByCuenta(Cuenta cuenta);

    List<Chequera> findByCuentaIdCuenta(Integer idCuenta);

    List<Chequera> findByEstadoChequera(String estadoChequera);
}
