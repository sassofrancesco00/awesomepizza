import { Routes } from '@angular/router';

export const routes: Routes = [
  { path: '', redirectTo: '/customer', pathMatch: 'full' },

  // Lazy loading dei componenti
  {
    path: 'customer',
    loadComponent: () => import('./components/customer/order-form/order-form.component')
      .then(m => m.OrderFormComponent)
  },
  {
    path: 'pizzaiolo',
    loadComponent: () => import('./components/pizzaiolo/orders-queue/orders-queue.component')
      .then(m => m.OrdersQueueComponent)
  },
  {
    path: 'order/:code',
    loadComponent: () => import('./components/customer/order-status/order-status.component')
      .then(m => m.OrderStatusComponent)
  }
];
