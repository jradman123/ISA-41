import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdventureBasicInfoComponent } from './adventure-basic-info.component';

describe('AdventureBasicInfoComponent', () => {
  let component: AdventureBasicInfoComponent;
  let fixture: ComponentFixture<AdventureBasicInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdventureBasicInfoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdventureBasicInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
