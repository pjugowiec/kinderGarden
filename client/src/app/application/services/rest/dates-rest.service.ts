import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Dates } from '../../domain/external/Dates';
import { ParsedDateWithDateType } from '../../domain/external/ParsedDateWithDateType';
import { Method, RestService } from './../rest.service';
const DATES_URL = '/dates';
@Injectable()
export class DatesRestSerivce extends RestService {

    constructor(http: HttpClient) {
        super(http);
    }

    queryGet(employeeId: number): Observable<Array<ParsedDateWithDateType>> {
        return this.request<Array<ParsedDateWithDateType>>({
            url: `${DATES_URL}`,
            params: {
                employeeId: employeeId
            },
            method: Method.GET
        });
    }

    queryUpdateDates(dates: Dates[], employeeId: number): Observable<void> {
        return this.request<void>({
            url: `${DATES_URL}`,
            params: {
                employeeId: employeeId
            },
            data: dates,
            method: Method.PUT
        });
    }

    queryDelete(id: number): Observable<void>{
        return this.request<void>({
            url: `${DATES_URL}`,
            params:{
                dateId: id
            },
            method: Method.DELETE
        })
    }

}
