import { Component, OnInit } from '@angular/core';
import { PrestamosService, Prestamo } from '../prestamos.service';
import { ClientesService, Cliente } from '../../clientes/clientes.service';
import {Router} from "@angular/router";

@Component({
  selector: 'app-listar-prestamo',
  templateUrl: './listar-prestamo.component.html',
  styleUrls: ['./listar-prestamo.component.css'],
})
export class ListarPrestamoComponent implements OnInit {
  prestamos: Prestamo[]= [];
  prestamosFiltrados: any[] = [];
  clientes: any[] = [];
  clienteSeleccionado: any = null;

  constructor(
    private prestamosService: PrestamosService,
    private clientesService: ClientesService,
    private router: Router
  ) {}
  verHistorial(idPrestamo: number) {
    this.router.navigate(['/prestamos/historial', idPrestamo]);
  }
  RealizarPago(idPrestamo: number) {
    this.router.navigate(['/prestamos/realizarpago', idPrestamo]);
  }
  ngOnInit(): void {
    this.prestamosService.listar().subscribe(response => {
      if (response.successful) {
        this.prestamos = response.prestamos;
        this.prestamosFiltrados = [...this.prestamos];
      }
    });

    this.clientesService.listar().subscribe(response => {
      if (response.successful) {
        this.clientes = response.clientes.map((c: any) => ({
          ...c,
          nombreCompleto: `${c.nombre} ${c.apellido}`
        }));
      }
    });
  }

  filtrarPorCliente(): void {
    if (this.clienteSeleccionado) {

      this.prestamosFiltrados = this.prestamos.filter(p =>

        p.solicitud.cliente.idCliente === this.clienteSeleccionado.idCliente
      );
    } else {
      this.prestamosFiltrados = [...this.prestamos];
    }
  }
}
