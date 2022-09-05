import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BRulesComponent } from './b-rules.component';

describe('BRulesComponent', () => {
  let component: BRulesComponent;
  let fixture: ComponentFixture<BRulesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BRulesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BRulesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
