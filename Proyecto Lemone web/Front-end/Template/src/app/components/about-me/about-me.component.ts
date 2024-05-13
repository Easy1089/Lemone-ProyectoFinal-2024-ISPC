import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-about-me',
  templateUrl: './about-me.component.html',
  styleUrls: ['./about-me.component.css']
})
export class AboutMeComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  miembros = [
    { 
      name: 'Melisa Apaz',
      description: 'melisaapaz@gmail.com',
      image: 'assets/img/miembros/MelisApaz.jpeg'
    },
    {
      name: 'Mateo Antunez',
      description: 'mateoantunez0@gmail.com',
      image: 'assets/img/miembros/MateoAntunes.jpg'
    },
    {
      name: 'Emiliano Guazzetti',
      description: 'edguazzetti@gmail.com',
      image: ''
    },
    {
      name: 'Fabiola Benítez',
      description: 'fabiolaben03@gmail.com',
      image: ''
    }, 
    {
      name: 'Priscila Teruel',
      description: 'priscilateruel93@gmail.com',
      image: ''
    }
  ];

  addToCart(miembro: any) {
    // implement the add to cart functionality here
  }

  selectedMiembro: any;
  selectMiembro(miembro: any) {
    this.selectedMiembro = miembro;
  }
}
