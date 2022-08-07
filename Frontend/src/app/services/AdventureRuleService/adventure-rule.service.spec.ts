import { TestBed } from '@angular/core/testing';

import { AdventureRuleService } from './adventure-rule.service';

describe('AdventureRuleService', () => {
  let service: AdventureRuleService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdventureRuleService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
