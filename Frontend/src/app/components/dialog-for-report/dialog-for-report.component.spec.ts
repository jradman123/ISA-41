import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogForReportComponent } from './dialog-for-report.component';

describe('DialogForReportComponent', () => {
  let component: DialogForReportComponent;
  let fixture: ComponentFixture<DialogForReportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DialogForReportComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogForReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
