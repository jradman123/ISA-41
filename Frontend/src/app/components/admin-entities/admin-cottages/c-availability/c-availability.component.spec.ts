import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CAvailabilityComponent } from './c-availability.component';

describe('CAvailabilityComponent', () => {
  let component: CAvailabilityComponent;
  let fixture: ComponentFixture<CAvailabilityComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CAvailabilityComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CAvailabilityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
