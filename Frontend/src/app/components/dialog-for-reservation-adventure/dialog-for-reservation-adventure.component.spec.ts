import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogForReservationAdventureComponent } from './dialog-for-reservation-adventure.component';

describe('DialogForReservationAdventureComponent', () => {
  let component: DialogForReservationAdventureComponent;
  let fixture: ComponentFixture<DialogForReservationAdventureComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DialogForReservationAdventureComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogForReservationAdventureComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
