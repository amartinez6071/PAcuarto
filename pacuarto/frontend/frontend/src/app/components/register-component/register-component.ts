import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { UsuarioService, RegistroRequest } from '../../services/usuario-service';

@Component({
  selector: 'app-register-component',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './register-component.html',
  styleUrl: './register-component.scss',
})
export class RegisterComponent {

  nombre: string='';
  direccion: string='';
  username: string='';
  password: string='';
  tipo: 'DONANTE' | 'RECEPTOR' = 'DONANTE';

  mensajeError: string = '';
  mensajeOk: string = '';
  cargando: boolean = false;

  constructor(
    private usuarioService: UsuarioService,
    private router: Router
  ){}

  onRegister(){
    this.mensajeError = '';
    this.mensajeOk = '';

    if(!this.nombre || !this.username || !this.password){
      this.mensajeError = 'Por favor, ingresar todos los campos';
      return;
    }

    const body: RegistroRequest = {
      nombre: this.nombre,
      direccion: this.direccion,
      username: this.username,
      password: this.password,
      tipo: this.tipo
    };

    console.log('Registro enviado', body);

    this.cargando = true;

    this.usuarioService.registrarUsuario(body).subscribe({
      next:(resp)=>{
        console.log('Respuesta backend', resp);
        this.mensajeOk='Usuario registrado correctamente';

        setTimeout(()=>{
          this.router.navigate(['/login']);
        },1000);
      },
      error: (err)=>{
        console.error('Error al registrar:', err);
        if (err.status === 400 || err.status===409){
          this.mensajeError = err.error?.message || 'Error de Validación';
        } else {
          this.mensajeError = 'Ocurrió un error al registrar';
        }
      },

      complete: ()=>{
        this.cargando = false;
      }
    });
  }

}
