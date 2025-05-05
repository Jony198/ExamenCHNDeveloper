import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { ListarClientesComponent } from './clientes/listar-clientes/listar-clientes.component';
import { CrearClienteComponent } from './clientes/crear-cliente/crear-cliente.component';
import { EditarClienteComponent } from './clientes/editar-cliente/editar-cliente.component';
import { NavbarComponent } from './layout/navbar/navbar.component';
import { ListarSolicitudesComponent } from './solicitudes/listar-solicitudes/listar-solicitudes.component'
import { CrearSolicitudComponent} from "./solicitudes/crear-solicitud/crear-solicitud.component";
import { AprobarSolicitudComponent} from "./solicitudes/aprobar-solicitud/aprobar-solicitud.component";
import { ListarPrestamoComponent} from "./prestamos/listar-prestamo/listar-prestamo.component";
import { HistorialPagosComponent} from "./prestamos/historial-pagos/historial-pagos.component";
import {RealizarPagoComponent} from "./prestamos/realizar-pago/realizar-pago.component";

import { RouterModule, Routes } from '@angular/router';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { DropdownModule } from 'primeng/dropdown';
import {ToastModule} from "primeng/toast";

const routes: Routes = [
  { path: 'clientes', component: ListarClientesComponent },
  { path: 'clientes/nuevo', component: CrearClienteComponent },
  { path: 'clientes/editar/:id', component: EditarClienteComponent },
  { path: 'solicitudes', component: ListarSolicitudesComponent },
  { path: 'solicitudes/nuevo', component: CrearSolicitudComponent },
  { path: 'solicitudes/aprobar/:id', component: AprobarSolicitudComponent },
  { path: 'prestamos', component: ListarPrestamoComponent },
  { path: 'prestamos/historial/:id', component: HistorialPagosComponent },
  { path: 'prestamos/realizarpago/:id', component: RealizarPagoComponent },
  { path: '', redirectTo: 'clientes', pathMatch: 'full' }
];

@NgModule({
  declarations: [
    AppComponent,
    ListarClientesComponent,
    CrearClienteComponent,
    EditarClienteComponent,
    NavbarComponent,
    ListarSolicitudesComponent,
    CrearSolicitudComponent,
    AprobarSolicitudComponent,
    ListarPrestamoComponent,
    HistorialPagosComponent,
    RealizarPagoComponent
  ],
  imports: [
    BrowserModule,
    CommonModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    DropdownModule,
    BrowserAnimationsModule,
    RouterModule.forRoot(routes),
    ToastModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
