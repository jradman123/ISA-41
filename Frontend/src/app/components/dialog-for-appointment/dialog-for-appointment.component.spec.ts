import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogForAppointmentComponent } from './dialog-for-appointment.component';

describe('DialogForAppointmentComponent', () => {
  let component: DialogForAppointmentComponent;
  let fixture: ComponentFixture<DialogForAppointmentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DialogForAppointmentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogForAppointmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
