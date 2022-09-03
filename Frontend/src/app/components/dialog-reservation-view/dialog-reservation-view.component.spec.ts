import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogReservationViewComponent } from './dialog-reservation-view.component';

describe('DialogReservationViewComponent', () => {
  let component: DialogReservationViewComponent;
  let fixture: ComponentFixture<DialogReservationViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DialogReservationViewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogReservationViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
