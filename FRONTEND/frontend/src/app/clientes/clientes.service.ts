import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

export interface Cliente {
  idCliente?: number;
  nombre: string;
  apellido: string;
  numeroIdentificacion: string;
  fechaNacimiento: string;
  direccion?: string;
  correoElectronico?: string;
  telefono?: string;
}
export interface BackendResponse {
  successful: boolean;
  body: any;
  statusCode: any;
  statusCodeValue: any;
  errores:any;
}
export interface ClienteResponse extends BackendResponse {
  clientes?: any;
}
@Injectable({
  providedIn: 'root'
})
export class ClientesService {
  private baseUrl = environment.apiUrl;
  private apiUrl = this.baseUrl+'/clientes';

  constructor(private http: HttpClient) {}

  listar(): Observable<ClienteResponse> {
    return this.http.get<ClienteResponse>(this.apiUrl);
  }

  crear(cliente: Cliente): Observable<BackendResponse> {
    return this.http.post<BackendResponse>(this.apiUrl, cliente);
  }

  obtenerPorId(id: number): Observable<ClienteResponse> {
    return this.http.get<ClienteResponse>(`${this.apiUrl}/${id}`);
  }

  actualizar(id: number, cliente: Cliente): Observable<BackendResponse> {
    return this.http.put<BackendResponse>(`${this.apiUrl}/${id}`, cliente);
  }

  eliminar(id: number): Observable<BackendResponse> {
    return this.http.delete<BackendResponse>(`${this.apiUrl}/${id}`);
  }
}
