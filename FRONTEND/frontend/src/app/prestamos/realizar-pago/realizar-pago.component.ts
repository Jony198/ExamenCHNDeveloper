import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { PrestamosService, Prestamo } from '../prestamos.service';


@Component({
  selector: 'app-realizar-pago',
  templateUrl: './realizar-pago.component.html',
  styleUrls: ['./realizar-pago.component.css'],
  providers: [MessageService]
})
export class RealizarPagoComponent implements OnInit {
  prestamo!: Prestamo;
  tipoPago: string = '';
  tiposPago = [
    { label: 'Pago Cuota', value: 'CUOTA' },
    { label: 'Pago a Capital', value: 'CAPITAL' }
  ];

  capitalCuota: number = 0;
  interesCuota: number = 0;
  montoCapital: number = 0;
  cargando: boolean = false;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private prestamosService: PrestamosService,
    private pagosService: PrestamosService,
    private messageService: MessageService
  ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.prestamosService.obtenerPorId(id).subscribe(resp => {

      if (resp.successful ) {
        this.prestamo = resp.prestamos[0];
        this.calcularComponentesCuota();
      } else {
        this.messageService.add({ severity: 'error', summary: 'Error', detail: 'No se pudo cargar el préstamo.' });
        this.router.navigate(['/prestamos']);
      }
    });

  }
  onDecisionChange(valor:any): void {
    this.tipoPago = valor.value;

  }
  calcularComponentesCuota(): void {
    if(this.prestamo.cuota>this.prestamo.saldoPendiente){
      this.prestamo.cuota=this.prestamo.saldoPendiente;
    }
    const tasaMensual = this.prestamo.solicitud.tasaInteres / 100 / 12;
    this.interesCuota = parseFloat((this.prestamo.saldoPendiente * tasaMensual).toFixed(2));
    this.capitalCuota = parseFloat((this.prestamo.cuota - this.interesCuota).toFixed(2));

  }

  guardarPago(): void {
    if (!this.tipoPago) return;

    this.cargando = true;
    const pago: any = {
      prestamo: this.prestamo,
      pagoCapital: 0,
      intereses: 0,
      montoPagado: 0
    };

    if (this.tipoPago === 'CUOTA') {
      pago.pagoCapital = this.capitalCuota;
      pago.intereses = this.interesCuota;
      pago.montoPagado = this.prestamo.cuota;
    } else {
      pago.pagoCapital = this.montoCapital;
      pago.intereses = 0;
      pago.montoPagado = this.montoCapital;
    }
    if(pago.pagoCapital > this.prestamo.saldoPendiente) {
      this.messageService.add({ severity: 'error', summary: 'Error', detail: "El pago a capital no puede ser mayor al saldo pendiente." });
      return;
    }
    this.pagosService.guardarPago(pago).subscribe(resp => {
      this.cargando = false;
      if (resp.successful) {
        this.messageService.add({ severity: 'success', summary: 'Éxito', detail: 'Pago registrado correctamente' });
        this.router.navigate(['/prestamos']);
      } else {
        const errores = resp.errores?.map((e: any) => e.mensaje).join('\n') || 'Error desconocido';
        this.messageService.add({ severity: 'error', summary: 'Error', detail: errores });
      }
    });
  }

  cancelar(): void {
    this.router.navigate(['/prestamos']);
  }
}
