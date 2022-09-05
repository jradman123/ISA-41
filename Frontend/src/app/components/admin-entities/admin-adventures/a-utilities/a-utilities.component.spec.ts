import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AUtilitiesComponent } from './a-utilities.component';

describe('AUtilitiesComponent', () => {
  let component: AUtilitiesComponent;
  let fixture: ComponentFixture<AUtilitiesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AUtilitiesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AUtilitiesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
