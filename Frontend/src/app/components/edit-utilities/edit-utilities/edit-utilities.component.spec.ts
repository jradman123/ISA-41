import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditUtilitiesComponent } from './edit-utilities.component';

describe('EditUtilitiesComponent', () => {
  let component: EditUtilitiesComponent;
  let fixture: ComponentFixture<EditUtilitiesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditUtilitiesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditUtilitiesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
