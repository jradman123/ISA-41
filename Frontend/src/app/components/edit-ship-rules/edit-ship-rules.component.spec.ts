import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditShipRulesComponent } from './edit-ship-rules.component';

describe('EditShipRulesComponent', () => {
  let component: EditShipRulesComponent;
  let fixture: ComponentFixture<EditShipRulesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditShipRulesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditShipRulesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
