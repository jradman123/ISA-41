import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogForReservationShipComponent } from './dialog-for-reservation-ship.component';

describe('DialogForReservationShipComponent', () => {
  let component: DialogForReservationShipComponent;
  let fixture: ComponentFixture<DialogForReservationShipComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DialogForReservationShipComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogForReservationShipComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
