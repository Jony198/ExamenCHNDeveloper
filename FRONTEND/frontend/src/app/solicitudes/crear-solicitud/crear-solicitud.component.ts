import { Component, OnInit } from '@angular/core';
import { SolicitudesService, SolicitudPrestamo } from '../solicitudes.service';
import { ClientesService, Cliente } from '../../clientes/clientes.service';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
@Component({
  selector: 'app-crear-solicitud',
  templateUrl: './crear-solicitud.component.html',
  styleUrls: ['./crear-solicitud.component.css'],
  providers: [MessageService]
})
export class CrearSolicitudComponent implements OnInit {
  solicitud: SolicitudPrestamo = {
    cliente: {},
    monto: 0,
    plazoEnMeses: 0,
    tasaInteres: 0,
    estado: 'PENDIENTE'
  };

  clientes: Cliente[] = [];

  constructor(
    private solicitudesService: SolicitudesService,
    private clientesService: ClientesService,
    private router: Router,
    private messageService: MessageService
  ) {}

  ngOnInit(): void {
    this.clientesService.listar().subscribe({
      next: (response) => {
        if (response.successful) {
          this.clientes = response.clientes;
        } else {
          let MensajeError = '';
          for (let i = 0; i < response.errores.length; i++) {
            console.error(response.errores[i].mensaje);
            MensajeError += response.errores[i].mensaje;
          }
          window.alert(MensajeError);
        }
      },
      error: () => window.alert('Error al cargar los clientes')
    });
  }

  crear(): void {
    if(this.solicitud.monto<=0){
      window.alert("El monto solicitado no puede se menor o igual que 0");
      return;
    }
    if(this.solicitud.plazoEnMeses<=0){
      window.alert("El Plazo solicitado no puede se menor o igual que 0");
      return;
    }


    if(this.solicitud.cliente && !this.solicitud.cliente.idCliente){
      this.messageService.add({ severity: 'warn', summary: 'Advertencia', detail: 'Por favor, selecionar un cliente' });

      return;
    }
    this.solicitudesService.crear(this.solicitud).subscribe({
      next: (response) => {
        if (response.successful) {

          this.messageService.add({ severity: 'success', summary: 'Éxito', detail: 'Solicitud creada correctamente' });
          this.router.navigate(['/solicitudes']);
        } else {
          let MensajeError = '';
          for (let i = 0; i < response.errores.length; i++) {
            console.error(response.errores[i].mensaje);
            MensajeError += response.errores[i].mensaje;
          }
          window.alert(MensajeError);
        }
      },
      error: () => window.alert("Error en el servidor al crear solicitud")
    });
  }

  cancelar(): void {
    this.router.navigate(['/solicitudes']);
  }
}
