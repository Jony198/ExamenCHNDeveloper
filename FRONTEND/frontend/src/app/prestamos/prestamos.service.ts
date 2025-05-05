import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

export interface Prestamo {
  solicitud: any;
  idPrestamo: number;
  montoAprobado: number;
  fechaAprobacion: string;
  cuota: number;
  interesesPagados: number;
  saldoPendiente: number;
  estado:string;
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
export interface PagoResponse extends BackendResponse {
  pagos?: any;
}

@Injectable({
  providedIn: 'root'
})
export class PrestamosService {
  private baseUrl = environment.apiUrl;
  private apiUrl = `${this.baseUrl}/prestamos`;
  private pagosUrl = `${this.baseUrl}/pagos`;
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
  obtenerHistorialPagos(idPrestamo: number): Observable<PagoResponse> {
    return this.http.get<PagoResponse>(`${this.pagosUrl}/historial/${idPrestamo}`);
  }
  guardarPago(pago: any): Observable<BackendResponse> {
    return this.http.post<BackendResponse>(`${this.pagosUrl}`, pago);
  }
}
