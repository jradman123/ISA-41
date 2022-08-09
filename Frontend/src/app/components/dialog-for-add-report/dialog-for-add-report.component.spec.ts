import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogForAddReportComponent } from './dialog-for-add-report.component';

describe('DialogForAddReportComponent', () => {
  let component: DialogForAddReportComponent;
  let fixture: ComponentFixture<DialogForAddReportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DialogForAddReportComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogForAddReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
