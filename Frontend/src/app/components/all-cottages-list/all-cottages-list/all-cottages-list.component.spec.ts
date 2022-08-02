import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllCottagesListComponent } from './all-cottages-list.component';

describe('AllCottagesListComponent', () => {
  let component: AllCottagesListComponent;
  let fixture: ComponentFixture<AllCottagesListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AllCottagesListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AllCottagesListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
