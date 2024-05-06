import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-reset-pass',
  templateUrl: './reset-pass.component.html',
  styleUrls: ['./reset-pass.component.css']
})
export class ResetPassComponent implements OnInit {
  mostrarMensaje: boolean = false;

  constructor() { }

  ngOnInit(): void {
  }

  enviarCorreo(): void {
    // Aquí puedes agregar la lógica para enviar el correo
    // Por ahora, simplemente vamos a mostrar el mensaje
    this.mostrarMensaje = true;
  }
}
