import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShipReservationComponent } from './ship-reservation.component';

describe('ShipReservationComponent', () => {
  let component: ShipReservationComponent;
  let fixture: ComponentFixture<ShipReservationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShipReservationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ShipReservationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
