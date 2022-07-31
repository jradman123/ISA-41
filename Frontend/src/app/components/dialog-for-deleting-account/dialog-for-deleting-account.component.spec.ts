import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogForDeletingAccountComponent } from './dialog-for-deleting-account.component';

describe('DialogForDeletingAccountComponent', () => {
  let component: DialogForDeletingAccountComponent;
  let fixture: ComponentFixture<DialogForDeletingAccountComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DialogForDeletingAccountComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogForDeletingAccountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
