package com.examen.demo.repository;

import com.examen.demo.model.SolicitudPrestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolicitudPrestamoRepository extends JpaRepository<SolicitudPrestamo, Integer> {

}
