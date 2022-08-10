import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdventureFishingEquipmentsComponent } from './adventure-fishing-equipments.component';

describe('AdventureFishingEquipmentsComponent', () => {
  let component: AdventureFishingEquipmentsComponent;
  let fixture: ComponentFixture<AdventureFishingEquipmentsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdventureFishingEquipmentsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdventureFishingEquipmentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
