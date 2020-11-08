import { HttpResponse } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog, MatSnackBar, MatSort, MatTableDataSource } from '@angular/material';
import { TranslateService } from '@ngx-translate/core';
import { FileSaverService } from 'ngx-filesaver';
import { take } from 'rxjs/operators';
import { EmployeeRestSerivce } from '../../services/rest/employee-rest.service';
import { GenerateExcelRestService } from '../../services/rest/generate-excel-rest.service';
import { ConfirmDialogComponent } from '../general/confirm-dialog/confirm-dialog.component';
import { AddEmployeeComponent } from './add-employee/add-employee.component';
import { CalendarComponent } from './calendar/calendar.component';
import { EmployeeDetailsComponent } from './employee-details/employee-details.component';
import { InputDeatilsComponent } from './input-deatils/input-deatils.component';

@Component({
    selector: 'app-main-page',
    templateUrl: './main-page.component.html',
    styleUrls: ['./main-page.component.scss']
})
export class MainPageComponent implements OnInit {
    @ViewChild(MatSort, { static: true }) sort: MatSort;
    private _displayedColumns: string[] = ['lp', 'name', 'lastName', 'position', 'regularPost', 'action'];
    private _dataSource: MatTableDataSource<any>;

    constructor(
        private _employeeRestService: EmployeeRestSerivce,
        private _dialog: MatDialog,
        private _snackBarService: MatSnackBar,
        private _translate: TranslateService,
        private _generateExcelRestService: GenerateExcelRestService,
        private _fileSaverService: FileSaverService
    ) { }


    get displayedColumns() {
        return this._displayedColumns;
    }

    get dataSource() {
        return this._dataSource;
    }
    
    ngOnInit() {
        this.getData();

    }

    openCalendar(employeeId: number) {
        this._dialog.open(CalendarComponent, {
            data: { id: employeeId },
            width: '40vw'
        });
    }

    employeDetails(employeeID: number) {
        this._dialog.open(EmployeeDetailsComponent, {
            data: { id: employeeID }
        }).afterClosed().subscribe(response => {
            this.getData();
        });
    }

    getData() {
        this._employeeRestService.queryGet()
            .pipe(take(1))
            .subscribe(
                (response) => {
                    this._dataSource = new MatTableDataSource(response);
                    this._dataSource.sort = this.sort;
                },
                (error) => {
                    this._translate.get('EMPLOYEE_PAGE.ERRORS.ERROR_LOAD_DATA').subscribe((res: string) => {
                        this._snackBarService.open(res, 'ok', { duration: 3000 });
                    });
                });
    }

    applyFilter(event: Event) {
        const filterValue = (event.target as HTMLInputElement).value;
        this._dataSource.filter = filterValue.trim().toLowerCase();

        if (this._dataSource.paginator) {
            this._dataSource.paginator.firstPage();
        }
    }

    deleteEmployee(id: number) {
        this._dialog.open(ConfirmDialogComponent, {
            data: {
                title: 'EMPLOYEE_PAGE.CONFIRM_DELETE.DELETE_EMPLOYEE',
                message: 'EMPLOYEE_PAGE.CONFIRM_DELETE.DELETE_EMPLOYEE_MESSAGE',
                onConfirm: this._employeeRestService.queryDelete(id),
                error: 'EMPLOYEE_PAGE.CONFIRM_DELETE.ERROR'
            }
        }).afterClosed().subscribe(response => {
            if (response) {
                this._translate.get('EMPLOYEE_PAGE.CONFIRM_DELETE.SUCCESS').subscribe((res: string) => {
                    this._snackBarService.open(res, 'ok', { duration: 3000 });
                });

                this.getData();
            }

        });
    }

    addEmployee() {
        this._dialog.open(AddEmployeeComponent).afterClosed().subscribe(response => {
            this.getData();
        });
    }

    // TODO dodać zipowanie i obłsuge ogólną
    generateAll(dataSource: MatTableDataSource<any>) {
        const listOfid: Array<number> = Array<number>();
        dataSource.data.forEach(element => {
            listOfid.push(element.id);
        });

    }

    generateExcel(id: number, name: string) {
        this._dialog.open(InputDeatilsComponent).afterClosed().subscribe(response => {
            if (response) {
                this._generateExcelRestService.queryGenerateOne(id, response)
                    .pipe(take(1))
                    .subscribe((response: HttpResponse<any>) => {
                        const blob = new Blob([response.body], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' });
                        this._fileSaverService.save(blob, name);
                    });
            } else {

            }
        });


    }

}
