import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import { PrestamosService } from '../prestamos.service';

@Component({
  selector: 'app-historial-pagos',
  templateUrl: './historial-pagos.component.html',
  styleUrls: ['./historial-pagos.component.css']
})
export class HistorialPagosComponent implements OnInit {
  pagos: any[] = [];
  idPrestamo!: number;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private pagosService: PrestamosService
  ) {}

  ngOnInit(): void {
    this.idPrestamo = Number(this.route.snapshot.paramMap.get('id'));
    this.cargarHistorial();
  }
  Regresar() {
    this.router.navigate(['/prestamos']);
  }
  cargarHistorial(): void {
    this.pagosService.obtenerHistorialPagos(this.idPrestamo).subscribe(response => {
      if (response.successful) {
        this.pagos = response.pagos;
      } else {
        let MensajeError = '';
        for (let i = 0; i < response.errores.length; i++) {
          console.error(response.errores[i].mensaje);
          MensajeError += response.errores[i].mensaje;
        }
        window.alert(MensajeError);
      }
    });
  }
}
