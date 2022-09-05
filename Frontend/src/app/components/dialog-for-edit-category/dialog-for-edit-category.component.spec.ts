import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogForEditCategoryComponent } from './dialog-for-edit-category.component';

describe('DialogForEditCategoryComponent', () => {
  let component: DialogForEditCategoryComponent;
  let fixture: ComponentFixture<DialogForEditCategoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DialogForEditCategoryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogForEditCategoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
