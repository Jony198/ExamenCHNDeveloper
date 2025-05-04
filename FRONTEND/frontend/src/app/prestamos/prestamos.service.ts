import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

export interface Prestamo {
  solicitud: any;
  idPrestamo?: number;
  monto: number;
  tasaInteres: number;
  plazoEnMeses: number;
  fechaPrestamo?: string;
}

export interface BackendResponse {
  successful: boolean;
  body: any;
  statusCode: any;
  statusCodeValue: any;
  errores: any;
}

export interface PrestamoResponse extends BackendResponse {
  prestamos?: any;
}

@Injectable({
  providedIn: 'root'
})
export class PrestamosService {
  private baseUrl = environment.apiUrl;
  private apiUrl = `${this.baseUrl}/prestamos`;

  constructor(private http: HttpClient) {}

  listar(): Observable<PrestamoResponse> {
    return this.http.get<PrestamoResponse>(this.apiUrl);
  }

  obtenerPorId(id: number): Observable<PrestamoResponse> {
    return this.http.get<PrestamoResponse>(`${this.apiUrl}/${id}`);
  }

  crear(prestamo: any): Observable<BackendResponse> {
    return this.http.post<BackendResponse>(this.apiUrl, prestamo);
  }

  eliminar(id: number): Observable<BackendResponse> {
    return this.http.delete<BackendResponse>(`${this.apiUrl}/${id}`);
  }
}
