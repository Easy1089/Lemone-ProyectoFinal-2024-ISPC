import { Injectable } from '@angular/core';
import { Producto } from 'src/app/models/Producto';
import { ItemCarrito } from 'src/app/service/carrito';

@Injectable({
    providedIn: 'root'
  })
  export class CarritoService {
    productoEnCarrito(producto: Producto): boolean {
      throw new Error('Method not implemented.');
    }
    carrito: ItemCarrito[] = [];
  
    constructor() { }
  
    agregarAlCarrito(producto: Producto) {
      let item: ItemCarrito = {
        id: producto.id,
        nombre: producto.nombre,
        descripcion: producto.descripcion,
        preciodeventa: producto.preciodeventa,
        imagen: producto.imagen
      };
      
      // Verifica si el producto ya está en el carrito
      const encontrado = this.carrito.find(item => item.id === producto.id);
  
      // Si el producto no está en el carrito, lo agregamos
      if (!encontrado) {
        this.carrito.push(item);
      }
      
    }
  
    obtenerCarrito(): ItemCarrito[] {
      return this.carrito;
    }
  
    vaciarCarrito() {
      this.carrito = [];
    }
  }
  