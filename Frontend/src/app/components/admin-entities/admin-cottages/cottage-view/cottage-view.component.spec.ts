import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CottageViewComponent } from './cottage-view.component';

describe('CottageViewComponent', () => {
  let component: CottageViewComponent;
  let fixture: ComponentFixture<CottageViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CottageViewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CottageViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
