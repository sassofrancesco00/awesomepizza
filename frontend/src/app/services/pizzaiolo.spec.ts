import { TestBed } from '@angular/core/testing';

import { Pizzaiolo } from './pizzaiolo';

describe('Pizzaiolo', () => {
  let service: Pizzaiolo;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Pizzaiolo);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
