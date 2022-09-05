import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CRulesComponent } from './c-rules.component';

describe('CRulesComponent', () => {
  let component: CRulesComponent;
  let fixture: ComponentFixture<CRulesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CRulesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CRulesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
