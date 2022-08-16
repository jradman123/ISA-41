import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditShipNavigationComponent } from './edit-ship-navigation.component';

describe('EditShipNavigationComponent', () => {
  let component: EditShipNavigationComponent;
  let fixture: ComponentFixture<EditShipNavigationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditShipNavigationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditShipNavigationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
