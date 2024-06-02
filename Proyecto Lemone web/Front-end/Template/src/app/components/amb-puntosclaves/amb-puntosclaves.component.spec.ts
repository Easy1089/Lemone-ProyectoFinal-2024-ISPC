import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AmbPuntosclavesComponent } from './amb-puntosclaves.component';

describe('AmbPuntosclavesComponent', () => {
  let component: AmbPuntosclavesComponent;
  let fixture: ComponentFixture<AmbPuntosclavesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AmbPuntosclavesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AmbPuntosclavesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
