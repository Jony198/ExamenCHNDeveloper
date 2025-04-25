package com.examen.demo.response;

import com.examen.demo.entity.AbstractResponse;
import com.examen.demo.entity.ErrorList;
import com.examen.demo.model.Cliente;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClienteResponse extends AbstractResponse {
    List<Cliente> clientes;
    public ClienteResponse(ErrorList errores) {super(errores);}
    public ClienteResponse(List<Cliente> clientes) {this.clientes = clientes;}
}
