import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AbmCategoriasComponent } from './abm-categorias.component';

describe('AbmCategoriasComponent', () => {
  let component: AbmCategoriasComponent;
  let fixture: ComponentFixture<AbmCategoriasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AbmCategoriasComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AbmCategoriasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
