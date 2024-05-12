import { Component, OnInit } from '@angular/core';
import { ProductoService } from 'src/app/service/producto.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-desc-producto',
  templateUrl: './desc-producto.component.html',
  styleUrls: ['./desc-producto.component.css']
})
export class DescProductoComponent implements OnInit {
  id: number = 0;
  product: any;
  puntosClavesPorProducto: any[] = [];
  errorMensaje: string | undefined;

  constructor(private route: ActivatedRoute, private productoServ: ProductoService) {}

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.id = +params['id'];

      this.obtenerPuntosClavesPorProducto();
      
      this.productoServ.ObtenerProductoPorId(this.id).subscribe({
        next: (productoData: any) => {
          console.log(productoData.producto);
          this.product = productoData.producto;
        },
        error: (errorData: any) => {
          console.error(errorData);
        }
      });
    });
  }

  obtenerPuntosClavesPorProducto() {
    this.productoServ.ObtenerPuntosClavesPorProducto(this.id).subscribe({
      next: (response) => {
        console.log('Puntos claves', response.puntosclavesporproducto);
        this.puntosClavesPorProducto = response.puntosclavesporproducto;
      },
      error: (error) => {
        console.error(error);
        this.errorMensaje = 'Ocurrió un error al obtener los puntos claves del producto. Por favor, inténtalo de nuevo más tarde.';
      }
    });    
  }
}
