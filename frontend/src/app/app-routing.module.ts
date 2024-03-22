import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EmiComponent } from './components/emi/emi.component';

const routes: Routes = [
  { path: 'calc/emi', component: EmiComponent },
  { path: '', redirectTo: 'calc/emi', pathMatch: 'full' },
  { path: 'display-calculator', component: EmiComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
