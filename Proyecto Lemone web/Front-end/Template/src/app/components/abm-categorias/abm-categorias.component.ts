import { Component, OnInit } from '@angular/core';
import { ProductoService } from 'src/app/service/producto.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-abm-categorias',
  templateUrl: './abm-categorias.component.html',
  styleUrls: ['./abm-categorias.component.css']
})
export class AbmCategoriasComponent implements OnInit {
  productos: any[] | undefined;
  errorMensaje: string | undefined;
  mostrarForm = false;

  constructor(private productoServ: ProductoService, private router: Router) {}

  ngOnInit(): void {
    this.productoServ.ObtenerProductos().subscribe({
      next: (response) => {
        console.log(response.productos);
        this.productos = response.productos; // Extraer el array de productos de la respuesta
      },
      error: (error) => {
        console.error(error);
        this.errorMensaje = 'Ocurrió un error al obtener los productos. Por favor, inténtalo de nuevo más tarde.';
      }
  });
  }
  mostrarFormulario() {
    //this.mostrarForm = true;
    this.router.navigate(['/producto-form'])
  }

  ocultarFormulario() {
    this.mostrarForm = false;
  }

}