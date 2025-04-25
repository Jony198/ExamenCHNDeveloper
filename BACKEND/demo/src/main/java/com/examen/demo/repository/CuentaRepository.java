package com.examen.demo.repository;

import com.examen.demo.model.Cuenta;
import com.examen.demo.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Integer> {

    List<Cuenta> findByCliente(Cliente cliente);

    List<Cuenta> findByClienteIdCliente(Integer idCliente);

    List<Cuenta> findByEstadoCuenta(String estadoCuenta);
}
