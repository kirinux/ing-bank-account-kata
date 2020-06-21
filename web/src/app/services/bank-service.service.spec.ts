import { TestBed, inject } from '@angular/core/testing';

import { BankServiceService } from './bank-service.service';

describe('BankServiceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [BankServiceService]
    });
  });

  it('should be created', inject([BankServiceService], (service: BankServiceService) => {
    expect(service).toBeTruthy();
  }));
});
