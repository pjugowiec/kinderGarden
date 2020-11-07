import { Employee } from 'src/app/application/domain/external/Employee';
import { DateType } from '../enums/DateType';

export class Dates {
    id?: number;
    date: Date;
    dateType: DateType;
    employeeDto: Employee;
}
