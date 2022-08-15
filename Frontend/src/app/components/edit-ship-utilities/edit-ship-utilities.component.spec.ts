import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditShipUtilitiesComponent } from './edit-ship-utilities.component';

describe('EditShipUtilitiesComponent', () => {
  let component: EditShipUtilitiesComponent;
  let fixture: ComponentFixture<EditShipUtilitiesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditShipUtilitiesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditShipUtilitiesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
