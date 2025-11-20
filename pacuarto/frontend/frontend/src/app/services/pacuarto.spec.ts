import { TestBed } from '@angular/core/testing';

import { Pacuarto } from './pacuarto';

describe('Pacuarto', () => {
  let service: Pacuarto;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Pacuarto);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
