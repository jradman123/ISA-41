import { TestBed } from '@angular/core/testing';

import { RegRequestsService } from './reg-requests.service';

describe('RegRequestsService', () => {
  let service: RegRequestsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RegRequestsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
