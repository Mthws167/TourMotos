import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { AppRoutingModule } from './app.routing';
import { ComponentsModule } from './components/components.module';
import { AppComponent } from './app.component';
import { AdminLayoutComponent } from './layouts/admin-layout/admin-layout.component';
import {MapsComponent} from "./maps/maps.component";
<<<<<<< HEAD
import { GoogleMapsModule } from '@angular/google-maps'
import { provideEnvironmentNgxMask} from 'ngx-mask'

=======
>>>>>>> cd1f735e56ea2ba545339d442cd9e608b70260cf

@NgModule({
  imports: [
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    ComponentsModule,
    RouterModule,
    AppRoutingModule,
    GoogleMapsModule,
  ],
  declarations: [
    AppComponent,
    AdminLayoutComponent,

  ],
  providers: [provideEnvironmentNgxMask(),],
  bootstrap: [AppComponent]
})
export class AppModule { }
