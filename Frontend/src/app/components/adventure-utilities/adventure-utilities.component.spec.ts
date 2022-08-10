import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdventureUtilitiesComponent } from './adventure-utilities.component';

describe('AdventureUtilitiesComponent', () => {
  let component: AdventureUtilitiesComponent;
  let fixture: ComponentFixture<AdventureUtilitiesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdventureUtilitiesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdventureUtilitiesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
