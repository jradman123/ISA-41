import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CottageAvailabilityComponent } from './cottage-availability.component';

describe('CottageAvailabilityComponent', () => {
  let component: CottageAvailabilityComponent;
  let fixture: ComponentFixture<CottageAvailabilityComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CottageAvailabilityComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CottageAvailabilityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
