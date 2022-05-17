import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShipOwnerDashboardComponent } from './ship-owner-dashboard.component';

describe('ShipOwnerDashboardComponent', () => {
  let component: ShipOwnerDashboardComponent;
  let fixture: ComponentFixture<ShipOwnerDashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShipOwnerDashboardComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ShipOwnerDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
