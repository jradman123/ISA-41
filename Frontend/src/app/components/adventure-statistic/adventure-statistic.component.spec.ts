import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdventureStatisticComponent } from './adventure-statistic.component';

describe('AdventureStatisticComponent', () => {
  let component: AdventureStatisticComponent;
  let fixture: ComponentFixture<AdventureStatisticComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdventureStatisticComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdventureStatisticComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
