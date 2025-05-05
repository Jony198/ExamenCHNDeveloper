package com.examen.demo.repository;

import com.examen.demo.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Integer> {
    List<Pago> findByPrestamo_IdPrestamo(Integer idPrestamo);

}
