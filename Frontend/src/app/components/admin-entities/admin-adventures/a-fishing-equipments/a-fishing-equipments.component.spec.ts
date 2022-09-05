import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AFishingEquipmentsComponent } from './a-fishing-equipments.component';

describe('AFishingEquipmentsComponent', () => {
  let component: AFishingEquipmentsComponent;
  let fixture: ComponentFixture<AFishingEquipmentsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AFishingEquipmentsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AFishingEquipmentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
