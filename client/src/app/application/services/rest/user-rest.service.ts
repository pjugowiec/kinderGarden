import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../../domain/external/User';
import { Method, RestService } from './../rest.service';
const USER_URL = 'localhost:8080';
@Injectable()
export class UserRestService extends RestService {

    constructor(http: HttpClient){
        super(http);
    }

    queryGet (): Observable<Array<User>> {
        return this.request<Array<User>>({
            url: `${USER_URL}`,
            method: Method.GET
        });
    }
}