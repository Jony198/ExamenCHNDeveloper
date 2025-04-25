import { Component, OnInit } from '@angular/core';
import { ClientesService, Cliente } from '../clientes.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-listar-clientes',
  templateUrl: './listar-clientes.component.html',
  styleUrls: ['./listar-clientes.component.css']
})
export class ListarClientesComponent implements OnInit {
  clientes: Cliente[] = [];

  constructor(private clientesService: ClientesService, private router: Router) {}


  ngOnInit(): void {
    this.obtenerClientes();
  }

  obtenerClientes(): void {
    this.clientesService.listar().subscribe({
      next: (response) => {
        if (response.successful) {
          console.log(response);
          this.clientes = response.clientes;
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
        console.error('Error en la petición:', err);
        window.alert('Error al cargar clientes');
      }
    });
  }
  eliminarCliente(id: any): void {
    const confirmado = window.confirm('¿Está seguro de eliminar este cliente?');
    if (confirmado) {
      this.clientesService.eliminar(id).subscribe({
        next: (response) => {
          if (response.successful) {
            window.alert('Cliente eliminado correctamente');
            this.obtenerClientes(); // recarga lista
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
          console.error('Error al eliminar cliente:', err);
          window.alert('Error al eliminar cliente');
        }
      });
    }
  }
  editarCliente(id: any): void {
    this.router.navigate(['/clientes/editar', id]);
  }
}
