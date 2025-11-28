import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrdersQueue } from './orders-queue';

describe('OrdersQueue', () => {
  let component: OrdersQueue;
  let fixture: ComponentFixture<OrdersQueue>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OrdersQueue]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OrdersQueue);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
