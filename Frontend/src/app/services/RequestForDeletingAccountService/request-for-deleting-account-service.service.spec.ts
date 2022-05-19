import { TestBed } from '@angular/core/testing';

import { RequestForDeletingAccountServiceService } from './request-for-deleting-account-service.service';

describe('RequestForDeletingAccountServiceService', () => {
  let service: RequestForDeletingAccountServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RequestForDeletingAccountServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
