import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditCottageUtilityDialogComponent } from './edit-cottage-utility-dialog.component';

describe('EditCottageUtilityDialogComponent', () => {
  let component: EditCottageUtilityDialogComponent;
  let fixture: ComponentFixture<EditCottageUtilityDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditCottageUtilityDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditCottageUtilityDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
