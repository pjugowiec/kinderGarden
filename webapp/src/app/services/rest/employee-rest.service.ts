import { Injectable } from '@angular/core';
import { Method, RestService } from '../rest.service';
import { HttpClient } from '@angular/common/http';
import { Employee } from 'src/app/models/employee/employee';
import { Observable } from 'rxjs';

const EMPLOYEE_URL = '/employee';

@Injectable({
    providedIn: 'root'
})
export class EmployeeRestService extends RestService {

    constructor(http: HttpClient) {
        super(http);
    }

    queryGet(): Observable<Array<Employee>> {
        return this.request<Array<Employee>>({
          url: `${EMPLOYEE_URL}`,
          method: Method.GET
        });
    }
}