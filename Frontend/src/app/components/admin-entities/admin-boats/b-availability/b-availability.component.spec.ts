import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BAvailabilityComponent } from './b-availability.component';

describe('BAvailabilityComponent', () => {
  let component: BAvailabilityComponent;
  let fixture: ComponentFixture<BAvailabilityComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BAvailabilityComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BAvailabilityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
