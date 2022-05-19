import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogForResponseDeletingComponent } from './dialog-for-response-deleting.component';

describe('DialogForResponseDeletingComponent', () => {
  let component: DialogForResponseDeletingComponent;
  let fixture: ComponentFixture<DialogForResponseDeletingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DialogForResponseDeletingComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogForResponseDeletingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
