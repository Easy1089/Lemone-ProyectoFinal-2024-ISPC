import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService, Usuario } from 'src/app/service/auth.service';
import { Observable } from 'rxjs';


@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  isLoggedIn: boolean = false;
  currentUser: any;
  esAdmin: boolean = false;
  usuarioLogueado: boolean = false;
  errorMensaje: string | null = null;
  isLoggedIn$: Observable<boolean>;  // Observable para el estado de login
  currentUser$: Observable<Usuario | null>;  // Observable para el usuario actual
  esAdmin$: Observable<boolean>;  // Observable para comprobar si el usuario es administrador

  constructor(private router: Router, private authService: AuthService) { 
    this.isLoggedIn$ = this.authService.estaAutenticado;
    this.currentUser$ = this.authService.currentUser;
    this.esAdmin$ = this.authService.esAdmin();
  }

  ngOnInit(): void {
    this.authService.currentUser.subscribe(user => {  
      this.currentUser = user;
      console.log("Usuario logueado", this.currentUser.id, this.currentUser.username);

      this.isLoggedIn = user !== null;
    });

    this.authService.esAdmin().subscribe(admin => {
      this.esAdmin = admin;
    });
  }

  inicio() {
    this.router.navigate([""]);
  }

  login() {
    this.router.navigate(['/login']);
  }

  register() {
    this.router.navigate(['/register']);
  }

  catalogo() {
    this.router.navigate(['/catalogo']);
  }

  contacto() {
    this.router.navigate(['/contacto']);
  }

  aboutme() {
    this.router.navigate(['/about-me']);
  }

  carrito() {
    this.router.navigate(['/checkout']);
  }
  
  profile() {
    this.router.navigate(['/usuarioprofile']);
  }

  ambproductos() {
    this.router.navigate(['/abmproductos']);
  }

  ambcategorias() {
    this.router.navigate(['/ambcategorias']);
  }

  ambpuntosclaves() {
    this.router.navigate(['/puntosclaves']);
  }

  logout(event: Event): void {
    event.preventDefault();
    if (this.currentUser) {
      this.authService.logout(this.currentUser).subscribe({
        next: () => {
          this.router.navigate(['/home']);  
        },
        error: (error) => {
          console.error("Error al cerrar sesión:", error);
        }
      });
    } else {
      console.warn("No hay usuario actualmente logueado.");
    }
  }
 

  /*logout(event: Event): void {
    event.preventDefault();
    console.log("Llamando al deslogueo desde navbar.")

    if (this.currentUser) {
      console.log("Llamando al deslogueo desde navbar.")
      this.authService.logout(this.currentUser).subscribe({
        next: (data) => {
          this.router.navigate(['/home']);  
          this.currentUser = null; // Establecer el usuario actual como null
          this.errorMensaje = null; // Limpiar el mensaje de error
          //location.reload(); // Forzar el refresco de la página
        },
        error: (error) => {
          console.error("Error al cerrar sesión:", error);
          this.errorMensaje = 'Ocurrió un error al cerrar sesión. Por favor, inténtalo de nuevo más tarde.';
        }
      });
    } else {
      console.warn("No hay usuario actualmente logueado.");
    }
  } */ 
}
