import { Component, OnInit } from '@angular/core';
import { ItemCarrito } from 'src/app/service/carrito';
import { CarritoService } from 'src/app/service/service.carrito';
import { Router } from '@angular/router';

@Component({
  selector: 'app-carrito',
  templateUrl: './carrito.component.html',
  styleUrls: ['./carrito.component.css']
})
export class CarritoComponent implements OnInit {
  listaItemsCarrito: ItemCarrito[] = [];

  constructor(private carritoService: CarritoService, private router: Router) { }

  ngOnInit(): void {
    this.listaItemsCarrito = this.carritoService.obtenerCarrito();
  }

  vaciarCarrito() {
    this.carritoService.vaciarCarrito();
    this.listaItemsCarrito = [];
  }

  scrollToTop() {
    window.scrollTo(0, 0); 
  }

  catalogo(){
    this.router.navigate(['/catalogo']);
  }
}
