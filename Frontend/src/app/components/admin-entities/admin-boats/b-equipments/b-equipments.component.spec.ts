import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BEquipmentsComponent } from './b-equipments.component';

describe('BEquipmentsComponent', () => {
  let component: BEquipmentsComponent;
  let fixture: ComponentFixture<BEquipmentsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BEquipmentsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BEquipmentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
