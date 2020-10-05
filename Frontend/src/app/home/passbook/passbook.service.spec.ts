import { TestBed, inject } from '@angular/core/testing';

import { PassbookService } from './passbook.service';

describe('PassbookService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PassbookService]
    });
  });

  it('should be created', inject([PassbookService], (service: PassbookService) => {
    expect(service).toBeTruthy();
  }));
});
