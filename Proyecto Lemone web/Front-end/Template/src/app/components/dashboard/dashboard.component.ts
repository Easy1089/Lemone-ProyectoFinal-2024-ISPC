import { Component, OnInit } from '@angular/core';
import { ProductoService } from 'src/app/service/producto.service';
import { UsuarioService } from 'src/app/service/usuario.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  cantidadDeProductos: any | undefined;
  compras: any | undefined;
  ventas: any | undefined;
  cantidadDeUsuarios: any | undefined;
  errorMensaje: string | undefined;
  usuarios: any;
  cantidadUsuarios: any;
  categorias: any;

  constructor(private productoServ: ProductoService, private usuarioServ: UsuarioService) { }

  ngOnInit(): void {
    this.productoServ.ObtenerCantidadDeProductos().subscribe({
      next: (response) => {
          console.log(response.cantidad_productos);
          this.cantidadDeProductos = response.cantidad_productos;
      },
      error: (error) => {
          console.error(error);
          this.errorMensaje = 'Ocurrió un error al obtener la cantidad de productos. Por favor, inténtalo de nuevo más tarde.';
      }
  }); 

  this.productoServ.ObtenerCompras().subscribe({
    next: (response) => {
        console.log(response.total_precio_compras);
        this.compras = response.total_precio_compras;
    },
    error: (error) => {
        console.error(error);
        this.errorMensaje = 'Ocurrió un error al obtener la cantidad de compras. Por favor, inténtalo de nuevo más tarde.';
    }
}); 


this.productoServ.ObtenerVentas().subscribe({
  next: (response) => {
      console.log(response.total_ventas);
      this.ventas = response.total_ventas;
  },
  error: (error) => {
      console.error(error);
      this.errorMensaje = 'Ocurrió un error al obtener las ventas. Por favor, inténtalo de nuevo más tarde.';
  }
}); 

this.usuarioServ.ObtenerCantidadDeUsuarios().subscribe({
  next: (response) => {
      console.log(response.cantidad_usuarios_activos);
      this.cantidadDeUsuarios = response.cantidad_usuarios_activos;
  },
  error: (error) => {
      console.error(error);
      this.errorMensaje = 'Ocurrió un error al obtener la cantidad de usuarios. Por favor, inténtalo de nuevo más tarde.';
  }
}); 

this.productoServ.ObtenerCategorias().subscribe({
  next: (response) => {
      console.log(response.categorias);
      this.categorias = response.categorias;
  },
  error: (error) => {
      console.error(error);
      this.errorMensaje = 'Ocurrió un error al obtener las categorías. Por favor, inténtalo de nuevo más tarde.';
  }
}); 

this.usuarioServ.ObtenerUsuarios().subscribe({
  next: (response) => {
      console.log(response.usuarios);
      this.usuarios = response.usuarios;
      this.cantidadUsuarios = this.usuarios.length;
  },
  error: (error) => {
      console.error(error);
      this.errorMensaje = 'Ocurrió un error al obtener los usuarios. Por favor, inténtalo de nuevo más tarde.';
  }
}); 

}

}

