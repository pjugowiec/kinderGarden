import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { TranslateModule } from '@ngx-translate/core';
import { MaterialModule } from 'src/app/modules/material.module';
import { RestModule } from './../services/rest.module';
import { ConfirmDialogComponent } from './general/confirm-dialog/confirm-dialog.component';
import { LoginPageComponent } from './general/login-page/login-page.component';
import { NavigationComponent } from './general/navigation/navigation.component';
import { AddEmployeeComponent } from './main-page/add-employee/add-employee.component';
import { CalendarDayComponent } from './main-page/calendar/calendar-day/calendar-day.component';
import { CalendarComponent } from './main-page/calendar/calendar.component';
import { EmployeeDetailsComponent } from './main-page/employee-details/employee-details.component';
import { MainPageComponent } from './main-page/main-page.component';

@NgModule({
    declarations: [
        MainPageComponent,
        NavigationComponent,
        CalendarComponent,
        ConfirmDialogComponent,
        AddEmployeeComponent,
        EmployeeDetailsComponent,
        CalendarDayComponent,
        LoginPageComponent
    ],
    imports: [
        MaterialModule,
        CommonModule,
        RestModule,
        ReactiveFormsModule,
        FormsModule,
        TranslateModule.forChild()
    ],
    entryComponents: [
        EmployeeDetailsComponent,
        CalendarComponent,
        ConfirmDialogComponent,
        AddEmployeeComponent
    ]
})
export class ViewsModule { }
