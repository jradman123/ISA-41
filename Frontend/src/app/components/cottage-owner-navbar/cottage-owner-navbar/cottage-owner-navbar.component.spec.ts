import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CottageOwnerNavbarComponent } from './cottage-owner-navbar.component';

describe('CottageOwnerNavbarComponent', () => {
  let component: CottageOwnerNavbarComponent;
  let fixture: ComponentFixture<CottageOwnerNavbarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CottageOwnerNavbarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CottageOwnerNavbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
