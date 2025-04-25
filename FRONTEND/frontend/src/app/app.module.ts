import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { ListarClientesComponent } from './clientes/listar-clientes/listar-clientes.component';
import { CrearClienteComponent } from './clientes/crear-cliente/crear-cliente.component';
import { EditarClienteComponent } from './clientes/editar-cliente/editar-cliente.component';

import { RouterModule, Routes } from '@angular/router';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

const routes: Routes = [
  { path: 'clientes', component: ListarClientesComponent },
  { path: 'clientes/nuevo', component: CrearClienteComponent },
  { path: 'clientes/editar/:id', component: EditarClienteComponent },
  { path: '', redirectTo: 'clientes', pathMatch: 'full' }
];

@NgModule({
  declarations: [
    AppComponent,
    ListarClientesComponent,
    CrearClienteComponent,
    EditarClienteComponent
  ],
  imports: [
    BrowserModule,
    CommonModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    RouterModule.forRoot(routes)
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
