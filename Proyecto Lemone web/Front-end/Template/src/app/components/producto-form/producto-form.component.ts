import { Component, OnInit, EventEmitter, Output } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { ProductoService } from 'src/app/service/producto.service';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';
import { Producto } from 'src/app/models/Producto';

@Component({
  selector: 'app-producto-form',
  templateUrl: './producto-form.component.html',
  styleUrls: ['./producto-form.component.css']
})

export class ProductoFormComponent {  
  @Output() formularioCerrado = new EventEmitter<void>();
  productoForm!: FormGroup; 
  formularioEdicion!:FormGroup;
  categorias: any[] | undefined;
  errorMensaje: string | undefined;
  productoActual!: Producto 
  mostrarFormularioEdicion: boolean = false;
  idproducto: number =0;

  constructor(private formBuilder: FormBuilder, 
             private productoServ: ProductoService, 
             private router: Router,
             private routers: ActivatedRoute,
             private authService: AuthService) { }

  ngOnInit() {
    this.productoForm = this.formBuilder.group({
      codigo: ['', Validators.required],
      nombre: ['', Validators.required],
      descripcion: ['', Validators.required],
      inventariominimo: [20, Validators.required],
      preciodecosto: [2000, Validators.required],
      preciodeventa: [2000, Validators.required],
      categoria: ['', Validators.required],
      activoactualmente: [true, Validators.required],
      imagen: [''],
      estado: ['A', Validators.required]
    });

    this.formularioEdicion = this.formBuilder.group({
      codigo: ['', Validators.required],
      nombre: ['', Validators.required],
      descripcion: ['', Validators.required],
      inventariominimo: [20, Validators.required],
      preciodecosto: [2000, Validators.required],
      preciodeventa: [2000, Validators.required],
      categoria: ['', Validators.required],
      activoactualmente: [true, Validators.required],
      imagen: [''],
      estado: ['A', Validators.required]
    });

    this.routers.params.subscribe(params => {
      this.mostrarFormularioEdicion = params['editar'] === 'true';
      this.idproducto = params['id']
    });
    
    this.ObtenerCategorias(); 

    if (this.mostrarFormularioEdicion === true) {
      console.log("Llamando al editarProducto...", this.idproducto)
      this.editarProducto(this.idproducto);
    }
  }

  ObtenerCategorias() {
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
  }

  guardarProducto() {
    if (this.productoForm.valid) {
      console.log('Producto a guardar:', this.productoForm.value);
      console.log("Obteniendo usuario...");
      const usuario = this.authService.usuarioAutenticado;
      console.log("Usuario:", usuario);
      this.productoServ.onCrearProducto(this.productoForm.value, usuario).subscribe({
        next: (productoCreado) => {
          console.log('Producto creado:', productoCreado);
          this.productoForm.reset();
          this.router.navigate(['/abmproductos']);
        },
        error: (error) => {
          console.error(error);
        }
      });
    } else {
      console.log('Formulario inválido');
    }
  }  

  editarProducto(idproducto: number) {
    this.productoServ.ObtenerProductoPorId(this.idproducto).subscribe({
      next: (productoData: any) => {
        this.productoActual = productoData.producto; 
        console.log("Producto actual:", this.productoActual);
        
        this.formularioEdicion.patchValue({
          codigo: this.productoActual.codigo,
          nombre: this.productoActual.nombre,
          descripcion: this.productoActual.descripcion,
          inventariominimo: this.productoActual.inventariominimo,
          preciodecosto: this.productoActual.preciodecosto,
          preciodeventa: this.productoActual.preciodeventa,
          categoria: this.productoActual.categoria,
          activoactualmente: this.productoActual.activoactualmente,
          imagen: this.productoActual.imagen,
          estado: this.productoActual.estado
        });
      },
      error: (errorData: any) => {
        console.error(errorData);
      }
    });
  }
  
  actualizarProducto(producto: Producto) {
    if (this.formularioEdicion.valid) {
      producto.codigo = this.formularioEdicion.value.codigo;
      producto.nombre = this.formularioEdicion.value.nombre;
      producto.descripcion = this.formularioEdicion.value.descripcion;
      producto.inventariominimo = this.formularioEdicion.value.inventariominimo;
      producto.preciodecosto = this.formularioEdicion.value.preciodecosto;
      producto.preciodeventa = this.formularioEdicion.value.preciodeventa;
      producto.categoria = this.formularioEdicion.value.categoria;
      producto.activoactualmente = this.formularioEdicion.value.activoactualmente;
      producto.imagen = this.formularioEdicion.value.imagen;
      producto.estado = this.formularioEdicion.value.estado;
  
      const usuario = this.authService.usuarioAutenticado;
      // Luego, puedes enviar el objeto producto actualizado a tu servicio para actualizarlo en el backend
      this.productoServ.onActualizarProducto(producto, usuario).subscribe({
        next: (productoActualizado) => {
          console.log('Producto actualizado:', productoActualizado);
          this.formularioEdicion.reset();
          this.router.navigate(['/abmproductos']);
        },
        error: (error) => {
          console.error(error);
        }
      });
    } else {
      console.log('Formulario inválido');
    }
  }
  
  
}
