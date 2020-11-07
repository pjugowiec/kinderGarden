import { NgModule } from '@angular/core';
import { DatesRestSerivce } from './rest/dates-rest.service';
import { EmployeeRestSerivce } from './rest/employee-rest.service';
import { GenerateExcelRestService } from './rest/generate-excel-rest.service';
import { UserRestService } from './rest/user-rest.service';

@NgModule({
    providers: [
        UserRestService,
        EmployeeRestSerivce,
        GenerateExcelRestService,
        DatesRestSerivce
    ]
})
export class RestModule {}