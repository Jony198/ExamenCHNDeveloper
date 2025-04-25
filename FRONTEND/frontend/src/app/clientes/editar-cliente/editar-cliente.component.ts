import {Component, OnInit} from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ClientesService} from "../clientes.service";
@Component({
  selector: 'app-editar-cliente',
  templateUrl: './editar-cliente.component.html',
  styleUrl: './editar-cliente.component.css'
})
export class EditarClienteComponent implements OnInit {
  clienteForm!: FormGroup;
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private fb: FormBuilder,
    private clientesService: ClientesService
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.clienteForm = this.fb.group({
        nombre: ['', Validators.required],
        apellido: ['', Validators.required],
        numeroIdentificacion: ['', Validators.required],
        fechaNacimiento: ['', Validators.required],
        direccion: [''],
        correoElectronico: [''],
        telefono: ['']
      });

      this.clientesService.obtenerPorId(+id).subscribe({
        next: (response) => {
          if (response.successful) {
            console.log(response);
            this.clienteForm.patchValue(response.clientes[0]);
          } else {
            let MensajeError=""
            for(let i = 0;i<response.errores.length;i++){
              console.error(response.errores[i].mensaje);
              MensajeError+=response.errores[i].mensaje;
            }
            window.alert(MensajeError);

          }
        },
        error: (err) => {
          console.error('Error al obtener cliente:', err);
          window.alert('No se pudo cargar el cliente.');
        }
      });
    }
  }
  actualizarCliente(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id && this.clienteForm.valid) {
      this.clientesService.actualizar(+id, this.clienteForm.value).subscribe({
        next: (response) => {
          if (response.successful) {
            window.alert('Cliente actualizado correctamente');
            this.router.navigate(['/clientes']);
          } else {
            let MensajeError=""
            for(let i = 0;i<response.errores.length;i++){
              console.error(response.errores[i].mensaje);
              MensajeError+=response.errores[i].mensaje;
            }
            window.alert(MensajeError);

          }
        },
        error: (err) => {
          console.error('Error al actualizar cliente:', err);
          window.alert('Error al actualizar cliente');
        }
      });
    }
  }
  cancelar(): void {
    this.router.navigate(['/clientes']);
  }

}
