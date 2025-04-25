import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {ClientesService} from "../clientes.service";

@Component({
  selector: 'app-crear-cliente',
  templateUrl: './crear-cliente.component.html',
  styleUrls: ['./crear-cliente.component.css']
})
export class CrearClienteComponent implements OnInit {
  clienteForm!: FormGroup;

  constructor(private fb: FormBuilder, private clientesService: ClientesService) {}


  ngOnInit(): void {
    this.clienteForm = this.fb.group({
      nombre: ['', Validators.required],
      apellido: ['', Validators.required],
      numeroIdentificacion: ['', Validators.required],
      fechaNacimiento: ['', Validators.required],
      direccion: [''],
      correoElectronico: [''],
      telefono: ['']
    });
  }

  guardar(): void {
    if (this.clienteForm.valid) {
      this.clientesService.crear(this.clienteForm.value).subscribe({
        next: (resp) => {
          console.log('Cliente creado:', resp);
          // redireccionar o mostrar mensaje
        },
        error: (err) => {
          console.error('Error al crear cliente:', err);
        }
      });
    }
  }

}
