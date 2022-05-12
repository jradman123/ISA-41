import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogForReasonComponent } from './dialog-for-reason.component';

describe('DialogForReasonComponent', () => {
  let component: DialogForReasonComponent;
  let fixture: ComponentFixture<DialogForReasonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DialogForReasonComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogForReasonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
