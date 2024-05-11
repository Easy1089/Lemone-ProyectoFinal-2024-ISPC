import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { Producto } from 'src/app/models/Producto';
import { CarritoService } from 'src/app/service/service.carrito';

@Component({
  selector: 'app-producto',
  templateUrl: './producto.component.html',
  styleUrls: ['./producto.component.css']
})
export class ProductoComponent {
  @Input() product: Producto | undefined;
  carrito: any;

  constructor(private router: Router, private carritoService: CarritoService) { }

  descproducto(id: number) {
    this.router.navigate(['/descripcionproducto/' + id]);
  }

  agregarCarrito(producto: Producto) {
    this.carritoService.agregarAlCarrito(producto);
  }
  
  productoEnCarrito(producto: Producto): boolean {
    return this.carrito.some((item: { id: number; }) => item.id === producto.id);
  }
}

