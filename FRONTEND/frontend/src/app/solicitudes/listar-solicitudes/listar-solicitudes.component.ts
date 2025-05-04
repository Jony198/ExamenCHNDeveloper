import { Component, OnInit } from '@angular/core';
import { SolicitudesService, SolicitudPrestamo } from '../solicitudes.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-listar-solicitudes',
  templateUrl: './listar-solicitudes.component.html',
  styleUrls: ['./listar-solicitudes.component.css']
})
export class ListarSolicitudesComponent implements OnInit {
  solicitudes: SolicitudPrestamo[] = [];

  constructor(private solicitudesService: SolicitudesService, private router: Router) {}

  ngOnInit(): void {
    this.obtenerSolicitudes();
  }


  obtenerSolicitudes(): void {
    this.solicitudesService.listar().subscribe({
      next: (response) => {
        if (response.successful) {
          this.solicitudes = response.solicitudes;
        } else {
          let MensajeError = '';
          for (let i = 0; i < response.errores.length; i++) {
            console.error(response.errores[i].mensaje);
            MensajeError += response.errores[i].mensaje;
          }
          window.alert(MensajeError);
        }
      },
      error: (err) => {
        console.error('Error al cargar solicitudes:', err);
        window.alert('Error al cargar solicitudes');
      }
    });
  }

  editarSolicitud(id: any): void {
    this.router.navigate(['/solicitudes/aprobar', id]);
  }

  eliminarSolicitud(id: any): void {
    const confirmado = window.confirm('¿Está seguro de eliminar esta solicitud?');
    if (confirmado) {
      this.solicitudesService.eliminar(id).subscribe({
        next: (response) => {
          if (response.successful) {
            window.alert('Solicitud eliminada correctamente');
            this.obtenerSolicitudes();
          } else {
            let MensajeError = '';
            for (let i = 0; i < response.errores.length; i++) {
              console.error(response.errores[i].mensaje);
              MensajeError += response.errores[i].mensaje;
            }
            window.alert(MensajeError);
          }
        },
        error: (err) => {
          console.error('Error al eliminar solicitud:', err);
          window.alert('Error al eliminar solicitud');
        }
      });
    }
  }
}
