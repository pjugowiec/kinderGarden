import { NgModule } from '@angular/core';
import { TranslateModule } from '@ngx-translate/core';
import { MaterialModule } from '../material.module';
import { SharedModule } from '../shared/shared.module';
import { EmployeePageComponent } from './employee-page/employee-page.component';

@NgModule({
    declarations: [
        EmployeePageComponent
    ],
    imports: [
        TranslateModule.forChild(),
        MaterialModule,
        SharedModule
    ],
    exports: [],
    providers: [],
})
export class ViewsModule { }