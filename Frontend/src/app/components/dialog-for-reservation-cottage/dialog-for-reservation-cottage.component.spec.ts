import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogForReservationCottageComponent } from './dialog-for-reservation-cottage.component';

describe('DialogForReservationCottageComponent', () => {
  let component: DialogForReservationCottageComponent;
  let fixture: ComponentFixture<DialogForReservationCottageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DialogForReservationCottageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogForReservationCottageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
