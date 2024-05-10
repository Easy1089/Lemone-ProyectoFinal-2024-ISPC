import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProductoService } from 'src/app/service/producto.service';
@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {

  productosDestacados: any[] | undefined;
  errorMensaje: string | undefined;

  scrollToTop() {
    window.scrollTo(0, 0);
  }

  constructor(private router: Router, public productoService: ProductoService) { }

  ngOnInit(): void {
    this.obtenerProductosDestacados();
  }

  contactos() {
    this.router.navigate(['/contacto'])
  }

  catalogo(){
    this.router.navigate(["/catalogo"])
  }

  obtenerProductosDestacados() {
    this.productoService.ObtenerProductoDestacado().subscribe({
      next: (response) => {
        console.log('productos destacados',response.productosdestacados);
        this.productosDestacados = response.productosdestacados; // Extraer el array de categorias de la respuesta
      },
      error: (error) => {
        console.error(error);
        this.errorMensaje = 'Ocurrió un error al obtener los productos destacados. Por favor, inténtalo de nuevo más tarde.';
      }
    })
  }
}
