import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BReservationsComponent } from './b-reservations.component';

describe('BReservationsComponent', () => {
  let component: BReservationsComponent;
  let fixture: ComponentFixture<BReservationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BReservationsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BReservationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
