import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
 
export interface LoginResponse{
  id: number;
  username: string;
  token: string;
  tipo: 'DONANTE' | 'RECEPTOR';
}

export interface LoginPayload{
  username: string;
  password: string;
}

export interface RegistroRequest{
  nombre: string;
  direccion?: string;
  username: string;
  password: string;
  tipo: 'DONANTE' | 'RECEPTOR';

}

export interface Usuario{

  nombre: string,
  direccion: string,
  username: string,
  password: string

}

@Injectable({
  providedIn: 'root',
})
export class UsuarioService {
  
  private baseUrl = 'http://localhost:8080/api/usuarios';


  constructor(private http: HttpClient){}

  login(username: string, password: string): Observable<LoginResponse>{
    const payload: LoginPayload = { username, password};

    return this.http.post<LoginResponse>(`${this.baseUrl}/login`, payload).pipe(catchError(this.handleError));
  }

  registrarUsuario(data: RegistroRequest): Observable<Usuario>{
    return this.http.post<Usuario>(this.baseUrl, data);
  }

  private handleError(error: HttpErrorResponse){
  let errorMessage = 'Ocurrió un error desconocido al inicio sesion.';

  // 1. Error del Servidor (Respuesta HTTP recibida)
  if (error.error && typeof error.error === 'object') {
    // Si Spring Boot lanzó una ResponseStatusException, el mensaje viene aquí:
    if (error.error.message) {
      errorMessage = error.error.message;
    } else {
      // Intento de capturar errores de validación (otros tipos de objetos de error)
      errorMessage = error.error.error || error.error.status || 'Error desconocido del servidor.';
    }
  }

  // 2. Errores de Conexión y Tipos de Errores Específicos
  if (error.status === 401) {
    // Sobreescribimos con un mensaje específico para 401 si no se capturó antes
    errorMessage = 'Credenciales inválidas. Por favor, verifica tu usuario y contraseña.';
  } else if (error.status === 0) {
    // Error de red o CORS
    errorMessage = 'No se pudo conectar con el servidor. Asegúrate de que tu backend está corriendo en http://localhost:8080.';
  } else if (error.error instanceof ErrorEvent) {
    // Error de lado del cliente/red
    errorMessage = `Error de red: ${error.error.message}`;
  } else if (error.status > 0) {
     // Error HTTP genérico si no se capturó un mensaje específico
    errorMessage = `Error del servidor (${error.status}): ${error.statusText}`;
  }

  console.error('Error capturado en el servicio:', error);
  // Devolvemos un observable con el mensaje de error capturado
  return throwError(() => new Error(errorMessage));
  }


}

