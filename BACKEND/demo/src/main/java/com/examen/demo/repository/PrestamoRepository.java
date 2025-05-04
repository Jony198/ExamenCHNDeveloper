package com.examen.demo.repository;

import com.examen.demo.model.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Integer> {

}
