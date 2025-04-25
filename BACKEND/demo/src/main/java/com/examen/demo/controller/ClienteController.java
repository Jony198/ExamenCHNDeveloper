package com.examen.demo.controller;

import com.examen.demo.model.Cliente;
import com.examen.demo.response.ClienteResponse;
import com.examen.demo.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*") // O ajusta al dominio del frontend si lo necesitas
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ClienteResponse listarClientes() {
        return clienteService.listarTodos();
    }

    @GetMapping("/{id}")
    public ClienteResponse obtenerClientePorId(@PathVariable Integer id) {
        return clienteService.obtenerPorId(id);
    }

    @PostMapping
    public ClienteResponse crearCliente(@RequestBody Cliente cliente) {
        return clienteService.guardar(cliente);
    }

    @PutMapping("/{id}")
    public ClienteResponse actualizarCliente(@PathVariable Integer id, @RequestBody Cliente cliente) {
        cliente.setIdCliente(id); // Asegura que el ID viene del path
        return clienteService.actualizar(cliente);
    }

    @DeleteMapping("/{id}")
    public ClienteResponse eliminarCliente(@PathVariable Integer id) {
        return clienteService.eliminar(id);
    }
}
