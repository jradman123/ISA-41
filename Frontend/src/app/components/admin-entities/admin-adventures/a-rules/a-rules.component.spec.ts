import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ARulesComponent } from './a-rules.component';

describe('ARulesComponent', () => {
  let component: ARulesComponent;
  let fixture: ComponentFixture<ARulesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ARulesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ARulesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
