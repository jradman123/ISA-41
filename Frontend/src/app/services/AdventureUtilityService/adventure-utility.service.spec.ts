import { TestBed } from '@angular/core/testing';

import { AdventureUtilityService } from './adventure-utility.service';

describe('AdventureUtilityService', () => {
  let service: AdventureUtilityService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdventureUtilityService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
