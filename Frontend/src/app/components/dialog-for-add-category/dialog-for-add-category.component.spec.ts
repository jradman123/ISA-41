import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogForAddCategoryComponent } from './dialog-for-add-category.component';

describe('DialogForAddCategoryComponent', () => {
  let component: DialogForAddCategoryComponent;
  let fixture: ComponentFixture<DialogForAddCategoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DialogForAddCategoryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogForAddCategoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
