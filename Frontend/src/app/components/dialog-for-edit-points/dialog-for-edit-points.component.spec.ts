import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogForEditPointsComponent } from './dialog-for-edit-points.component';

describe('DialogForEditPointsComponent', () => {
  let component: DialogForEditPointsComponent;
  let fixture: ComponentFixture<DialogForEditPointsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DialogForEditPointsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogForEditPointsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
