import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Method, RestService } from '../rest.service';

const GENERATE_URL = '/generate';
@Injectable()
export class GenerateExcelRestService extends RestService {

    constructor(http: HttpClient){
        super(http);
    }

    queryGenerateAll(listOfId: Array<number>): Observable<void> {

        return this.request<void>({
            url: `${GENERATE_URL}` + '/all',
            data: listOfId,
            method: Method.POST
        });
    }

    queryGenerateOne(id: number, name: String): Observable<HttpResponse<any>> {
        const fileType = 'EXCEL';
        return this.request<HttpResponse<any>>({
            url: `${GENERATE_URL}` + '/' + id,
            params: {
                fileType: fileType,
                name: name
            },
            observe: 'response',
            responseType: 'blob',
            method: Method.POST
        });
    }
}