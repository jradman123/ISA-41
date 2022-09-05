import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BUtilitiesComponent } from './b-utilities.component';

describe('BUtilitiesComponent', () => {
  let component: BUtilitiesComponent;
  let fixture: ComponentFixture<BUtilitiesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BUtilitiesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BUtilitiesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
