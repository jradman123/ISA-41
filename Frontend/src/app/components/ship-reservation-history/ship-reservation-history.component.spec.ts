import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShipReservationHistoryComponent } from './ship-reservation-history.component';

describe('ShipReservationHistoryComponent', () => {
  let component: ShipReservationHistoryComponent;
  let fixture: ComponentFixture<ShipReservationHistoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShipReservationHistoryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ShipReservationHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
