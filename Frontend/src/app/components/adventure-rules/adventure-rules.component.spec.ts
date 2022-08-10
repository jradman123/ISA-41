import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdventureRulesComponent } from './adventure-rules.component';

describe('AdventureRulesComponent', () => {
  let component: AdventureRulesComponent;
  let fixture: ComponentFixture<AdventureRulesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdventureRulesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdventureRulesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
