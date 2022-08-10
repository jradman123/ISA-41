import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditUtilityDialogComponent } from './edit-utility-dialog.component';

describe('EditUtilityDialogComponent', () => {
  let component: EditUtilityDialogComponent;
  let fixture: ComponentFixture<EditUtilityDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditUtilityDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditUtilityDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
