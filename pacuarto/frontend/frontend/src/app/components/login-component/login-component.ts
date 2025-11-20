import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { UsuarioService } from '../../services/usuario-service';

interface LoginResponse { 
  id: number;
  username: string;
  token: string;
  tipo: 'DONANTE' | 'RECEPTOR'; 
} 

@Component({
  selector: 'app-login-component',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './login-component.html',
  styleUrl: './login-component.scss',
})
export class LoginComponent {

  username: string = '';
  password: string = '';

  mensajeError: string = '';

  constructor(private router: Router, private usuarioService: UsuarioService) {}

  onSubmit() {
    this.mensajeError = '';

    if (!this.username || !this.password) {
      this.mensajeError = 'Por favor ingrese credenciales válidas';
      return;
    }

    this.usuarioService.login(this.username, this.password).subscribe({
      next: (response: LoginResponse) => { 
        console.log('Login exitoso. Tipo de Usuario:', response.tipo);

        const tipoUsuario = response.tipo.toUpperCase(); 
        
        if (tipoUsuario === 'DONANTE') {
          this.router.navigate(['/donante']);
        } else if (tipoUsuario === 'RECEPTOR') {
          this.router.navigate(['/receptor']);
        } else {
          this.mensajeError = 'Tipo de Usuario desconocido devuelto por el servidor';
        }
      },
      error: (err) => {

        let mensaje;
        
        if (err.error && typeof err.error === 'object' && err.error.message) {
            mensaje = err.error.message; 
        } else if (err.status === 401) {
            mensaje = 'Credenciales incorrectas. Verifique usuario y contraseña.';
        } else if (err.status) {
            mensaje = `Error en el servidor (${err.status}): ${err.statusText || 'Error desconocido'}`;
        } else {
            mensaje = 'No se pudo conectar con el servidor. Verifique su conexión de red.';
        }
        
        this.mensajeError = mensaje;
        console.error('Error al iniciar sesión:', err);
      }
    });

  }
}

