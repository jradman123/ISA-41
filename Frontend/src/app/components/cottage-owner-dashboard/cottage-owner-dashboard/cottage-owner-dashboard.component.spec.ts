import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CottageOwnerDashboardComponent } from './cottage-owner-dashboard.component';

describe('CottageOwnerDashboardComponent', () => {
  let component: CottageOwnerDashboardComponent;
  let fixture: ComponentFixture<CottageOwnerDashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CottageOwnerDashboardComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CottageOwnerDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
