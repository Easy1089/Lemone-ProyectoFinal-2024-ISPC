import { Component, OnInit } from '@angular/core';
import { ProductoService } from 'src/app/service/producto.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-abm-productos',
  templateUrl: './abm-productos.component.html',
  styleUrls: ['./abm-productos.component.css']
})

export class AbmProductosComponent implements OnInit {
  productos: any | undefined;
  errorMensaje: string | undefined;
  mostrarForm = false;
  cantidadProductos: any;

  constructor(private productoServ: ProductoService, private router: Router) {}

  ngOnInit(): void {
    this.obtenerProductos();
  }

  obtenerProductos(): void {
    this.productoServ.ObtenerProductos().subscribe({
      next: (response) => {
        console.log(response.productos);
        this.productos = response.productos; 
        this.cantidadProductos = this.productos.length;
      },
      error: (error) => {
        console.error(error);
        this.errorMensaje = 'Ocurrió un error al obtener los productos. Por favor, inténtalo de nuevo más tarde.';
      }
    });
  }

  eliminarProducto(productoId: number): void {
    const confirmacion = confirm('¿Estás seguro de que deseas eliminar este producto? Esta acción no se puede deshacer.');
    if (confirmacion) {
      this.productoServ.onEliminarProducto(productoId).subscribe(
        () => {
          this.obtenerProductos(); 
          console.log('Producto eliminado exitosamente');
        },
        error => {
          console.error('Error eliminando el producto:', error);
        }
      );
    } else {
      console.log('Eliminación cancelada por el usuario');
    }
  }
  
  mostrarFormulario() {
    //this.mostrarForm = true;
    this.router.navigate(['/producto-form'])
  }

  ocultarFormulario() {
    this.mostrarForm = false;
  }
}
