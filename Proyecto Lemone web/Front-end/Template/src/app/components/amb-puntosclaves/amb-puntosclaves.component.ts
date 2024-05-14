import { Component, OnInit } from '@angular/core';
import { ProductoService } from 'src/app/service/producto.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-amb-puntosclaves',
  templateUrl: './amb-puntosclaves.component.html',
  styleUrls: ['./amb-puntosclaves.component.css']
})
export class AmbPuntosclavesComponent implements OnInit {
  productos: any[] | undefined;
  errorMensaje: string | undefined;
  mostrarForm = false;
  puntosclaves: any;
  cantidadpuntosclaves: any;
  
  constructor(private productoServ: ProductoService, private router: Router) {}

  ngOnInit(): void {
    this.productoServ.ObtenerPuntosClaves().subscribe({
      next: (response) => {
          console.log(response.puntosclave);
          this.puntosclaves = response.puntosclave;
          this.cantidadpuntosclaves = this.puntosclaves.length;
      },
      error: (error) => {
          console.error(error);
          this.errorMensaje = 'Ocurrió un error al obtener los puntos claves. Por favor, inténtalo de nuevo más tarde.';
      }
    }); 
  }
  
  mostrarFormulario() {
    this.router.navigate(['/producto-form'])
  }

  ocultarFormulario() {
    this.mostrarForm = false;
  }
}
