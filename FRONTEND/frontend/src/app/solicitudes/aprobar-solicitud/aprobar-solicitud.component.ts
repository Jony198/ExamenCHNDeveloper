import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { SolicitudesService, SolicitudPrestamo } from '../solicitudes.service';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-aprobar-solicitud',
  templateUrl: './aprobar-solicitud.component.html',
  styleUrls: ['./aprobar-solicitud.component.css'],
  providers: [MessageService]
})
export class AprobarSolicitudComponent implements OnInit {

  solicitud: SolicitudPrestamo = {
    cliente: {},
    monto: 0,
    plazoEnMeses: 0,
    tasaInteres: 0,
    estado: 'PENDIENTE'
  };

  decision:string = '';
  motivoRechazo: string = '';
  cargando: boolean = false;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private solicitudesService: SolicitudesService,
    private messageService: MessageService
  ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.solicitudesService.obtenerPorId(id).subscribe(response => {
      if (response.successful && response.solicitudes.length > 0) {
        this.solicitud = response.solicitudes[0];
      } else {
        this.messageService.add({ severity: 'error', summary: 'Error', detail: 'No se pudo cargar la solicitud' });
      }
    });
  }


  onDecisionChange(valor:any): void {
    this.decision = valor.value;

  }

  guardar(): void {
    if (!this.decision) {
      this.messageService.add({ severity: 'warn', summary: 'Advertencia', detail: 'Debe seleccionar una decisión' });
      return;
    }

    if (this.decision === 'RECHAZADO' && !this.motivoRechazo.trim()) {
      this.messageService.add({ severity: 'warn', summary: 'Advertencia', detail: 'Debe ingresar el motivo de rechazo' });
      return;
    }

    this.cargando = true;
    const estado = this.decision;
    const motivo = this.decision === 'RECHAZADO' ? this.motivoRechazo : '';
    this.solicitud.estado = estado;
    this.solicitud.motivoRechazo = motivo;
    this.solicitudesService.actualizarEstado(
      this.solicitud
    ).subscribe(response => {
      this.cargando = false;
      if (response.successful) {
        this.messageService.add({ severity: 'success', summary: 'Éxito', detail: 'Solicitud actualizada correctamente' });
        setTimeout(() => this.router.navigate(['/solicitudes']), 1000);
      } else {
        let MensajeError = '';
        for (let i = 0; i < response.errores.length; i++) {
          console.error(response.errores[i].mensaje);
          MensajeError += response.errores[i].mensaje;
        }
        this.messageService.add({ severity: 'error', summary: 'Error', detail: MensajeError });
      }
    });
  }

  cancelar(): void {
    this.router.navigate(['/solicitudes']);
  }
}
