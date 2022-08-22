import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShipAvailabilityComponent } from './ship-availability.component';

describe('ShipAvailabilityComponent', () => {
  let component: ShipAvailabilityComponent;
  let fixture: ComponentFixture<ShipAvailabilityComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShipAvailabilityComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ShipAvailabilityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
