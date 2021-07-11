import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { TranslateModule } from '@ngx-translate/core';
import { MaterialModule } from '../material.module';
import { SharedModule } from '../shared/shared.module';
import { EmployeeFormComponent } from './employee-page/employee-form/employee-form.component';
import { EmployeePageComponent } from './employee-page/employee-page.component';
import { FlexLayoutModule } from '@angular/flex-layout';
import { CommonModule } from '@angular/common';

@NgModule({
    declarations: [
        EmployeePageComponent,
        EmployeeFormComponent
    ],
    imports: [
        CommonModule,
        TranslateModule.forChild(),
        MaterialModule,
        SharedModule,
        FormsModule,
        ReactiveFormsModule,
        FlexLayoutModule
    ],
    exports: [],
    providers: [],
    entryComponents: [
        EmployeeFormComponent
    ]
})
export class ViewsModule { }