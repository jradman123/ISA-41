import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShipReportComponent } from './ship-report.component';

describe('ShipReportComponent', () => {
  let component: ShipReportComponent;
  let fixture: ComponentFixture<ShipReportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShipReportComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ShipReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
