import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdventuresViewComponent } from './adventures-view.component';

describe('AdventuresViewComponent', () => {
  let component: AdventuresViewComponent;
  let fixture: ComponentFixture<AdventuresViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdventuresViewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdventuresViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
