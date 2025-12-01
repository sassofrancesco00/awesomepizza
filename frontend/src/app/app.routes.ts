import { Routes } from '@angular/router';

export const routes: Routes = [
  { path: '', redirectTo: '/customer', pathMatch: 'full' },

  // Lazy loading dei componenti
  {
    path: 'customer',
    loadComponent: () => import('./components/customer/order-form/order-form.ts')
      .then(m => m.OrderForm)
  },
  {
    path: 'pizzaiolo',
    loadComponent: () => import('./components/pizzaiolo/orders-queue/orders-queue.ts')
      .then(m => m.OrdersQueue)
  },
  {
    path: 'order/:code',
    loadComponent: () => import('./components/customer/order-status/order-status.ts')
      .then(m => m.OrderStatus)
  }
];
