import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogForGuestDataComponent } from './dialog-for-guest-data.component';

describe('DialogForGuestDataComponent', () => {
  let component: DialogForGuestDataComponent;
  let fixture: ComponentFixture<DialogForGuestDataComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DialogForGuestDataComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogForGuestDataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
