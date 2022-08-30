import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogForDeletingAdventureComponent } from './dialog-for-deleting-adventure.component';

describe('DialogForDeletingAdventureComponent', () => {
  let component: DialogForDeletingAdventureComponent;
  let fixture: ComponentFixture<DialogForDeletingAdventureComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DialogForDeletingAdventureComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogForDeletingAdventureComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
