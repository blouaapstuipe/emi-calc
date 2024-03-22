import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { EmiComponent } from './components/emi/emi.component';
import { CalcService } from './services/calc.service';

@NgModule({
  declarations: [
    AppComponent,
    EmiComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [CalcService],
  bootstrap: [AppComponent]
})
export class AppModule { }
