import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

export interface SolicitudPrestamo {
  idSolicitud?: number;
  cliente: any;
  monto: number;
  plazoEnMeses: number;
  tasaInteres: number;
  fechaSolicitud?: string;
  estado: string;
  motivoRechazo?: string;
}

export interface BackendResponse {
  successful: boolean;
  body: any;
  statusCode: any;
  statusCodeValue: any;
  errores: any;
}

export interface SolicitudPrestamoResponse extends BackendResponse {
  solicitudes?: any;
}

@Injectable({
  providedIn: 'root'
})
export class SolicitudesService {
  private baseUrl = environment.apiUrl;
  private apiUrl = `${this.baseUrl}/solicitudes`;

  constructor(private http: HttpClient) {}

  listar(): Observable<SolicitudPrestamoResponse> {
    return this.http.get<SolicitudPrestamoResponse>(this.apiUrl);
  }

  obtenerPorId(id: number): Observable<SolicitudPrestamoResponse> {
    return this.http.get<SolicitudPrestamoResponse>(`${this.apiUrl}/${id}`);
  }

  crear(solicitud: SolicitudPrestamo): Observable<BackendResponse> {
    return this.http.post<BackendResponse>(this.apiUrl, solicitud);
  }

  actualizar(id: number, solicitud: SolicitudPrestamo): Observable<BackendResponse> {
    return this.http.put<BackendResponse>(`${this.apiUrl}/${id}`, solicitud);
  }

  eliminar(id: number): Observable<BackendResponse> {
    return this.http.delete<BackendResponse>(`${this.apiUrl}/${id}`);
  }
  actualizarEstado(solicitud:any): Observable<BackendResponse> {
    console.log(solicitud);

    return this.http.put<BackendResponse>(`${this.apiUrl}/estado`, solicitud);
  }
}
