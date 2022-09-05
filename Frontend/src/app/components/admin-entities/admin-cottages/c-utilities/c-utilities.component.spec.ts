import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CUtilitiesComponent } from './c-utilities.component';

describe('CUtilitiesComponent', () => {
  let component: CUtilitiesComponent;
  let fixture: ComponentFixture<CUtilitiesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CUtilitiesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CUtilitiesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
