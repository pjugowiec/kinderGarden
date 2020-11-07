import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginPageComponent } from './application/views/general/login-page/login-page.component';
import { MainPageComponent } from './application/views/main-page/main-page.component';


const routes: Routes = [
    {path: '', component: LoginPageComponent},
    {path: 'employee', component: MainPageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
