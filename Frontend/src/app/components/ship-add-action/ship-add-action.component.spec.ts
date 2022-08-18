import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShipAddActionComponent } from './ship-add-action.component';

describe('ShipAddActionComponent', () => {
  let component: ShipAddActionComponent;
  let fixture: ComponentFixture<ShipAddActionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShipAddActionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ShipAddActionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
