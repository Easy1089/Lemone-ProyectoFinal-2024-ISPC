import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Usuario } from 'src/app/models/Usuario';
import { UsuarioService } from 'src/app/service/usuario.service';

@Component({
  selector: 'app-usuario-form',
  templateUrl: './usuario-form.component.html',
  styleUrls: ['./usuario-form.component.css']
})
export class UsuarioFormComponent implements OnInit {
  accountForm!: FormGroup;
  idUsuario: any | null = null;
  usuario: Usuario | null = null;

  constructor(private formBuilder: FormBuilder, private usuarioService: UsuarioService,
    private router: Router,
    private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => { 
      this.idUsuario = params['id'];
      if (this.idUsuario) {
        this.usuarioService.ObtenerUsuarioPorId(this.idUsuario).subscribe({
          next: (usuarioData: any) => {
            console.log(usuarioData.usuario);
            this.usuario = usuarioData.usuario;
            this.initializeForm();
          },
          error: (errorData: any) => {
            console.error(errorData);
          }
        });
      } else {
        this.initializeForm();
      }
    });
  }

  initializeForm(): void {
    this.accountForm = this.formBuilder.group({
      // Define los campos y las validaciones aquí
      nombre: [this.usuario ? this.usuario.username : '', Validators.required],
      // Otros campos del formulario
    });
  }

  saveChanges(): void {
    // Aquí irá la lógica para guardar los cambios en el usuario
  }
}
