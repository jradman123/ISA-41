import { TestBed } from '@angular/core/testing';

import { AdventureQuickReservationService } from './adventure-quick-reservation.service';

describe('AdventureQuickReservationService', () => {
  let service: AdventureQuickReservationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdventureQuickReservationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
