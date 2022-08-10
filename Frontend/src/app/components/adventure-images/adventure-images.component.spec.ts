import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdventureImagesComponent } from './adventure-images.component';

describe('AdventureImagesComponent', () => {
  let component: AdventureImagesComponent;
  let fixture: ComponentFixture<AdventureImagesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdventureImagesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdventureImagesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
