import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CottagesViewComponent } from './cottages-view.component';

describe('CottagesViewComponent', () => {
  let component: CottagesViewComponent;
  let fixture: ComponentFixture<CottagesViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CottagesViewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CottagesViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
