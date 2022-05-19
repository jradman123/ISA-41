import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RequestsForDeletingAccountComponent } from './requests-for-deleting-account.component';

describe('RequestsForDeletingAccountComponent', () => {
  let component: RequestsForDeletingAccountComponent;
  let fixture: ComponentFixture<RequestsForDeletingAccountComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RequestsForDeletingAccountComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RequestsForDeletingAccountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
