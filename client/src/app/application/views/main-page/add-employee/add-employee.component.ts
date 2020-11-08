import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MatSnackBar } from '@angular/material';
import { TranslateService } from '@ngx-translate/core';
import { Employee } from 'src/app/application/domain/external/Employee';
import { EmployeeRestSerivce } from 'src/app/application/services/rest/employee-rest.service';

@Component({
    selector: 'app-add-employee',
    templateUrl: './add-employee.component.html',
    styleUrls: ['./add-employee.component.scss']
})
export class AddEmployeeComponent implements OnInit {
    private _employeeForm: FormGroup;
    private _regularPost: Array<string> = ['1/2','3/4', '1'];

    constructor(
        private _dialogRef: MatDialogRef<AddEmployeeComponent>,
        private _snackBarService: MatSnackBar,
        private _translate: TranslateService,
        private _builder: FormBuilder,
        private _employeeSerivce: EmployeeRestSerivce
    ) { }

    ngOnInit() {
        this.createForm();
    }

    createForm() {
        this._employeeForm = this._builder.group({
            name: ['', [Validators.required, Validators.maxLength(50), Validators.minLength(1)]],
            lastName: ['', [Validators.required, Validators.maxLength(50), Validators.minLength(1)]],
            regularPost: ['', Validators.required],
            countOfVacation: ['', Validators.required],
            countOfChildrenCare: ['', Validators.required],
            position: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(50)]]
        });
    }

    get employeeForm(): FormGroup {
        return this._employeeForm;
    }

    get regularPost(): any {
        return this._regularPost;
    }

    getError(alias: string, error: string): boolean {
        return this._employeeForm && this._employeeForm.controls && this._employeeForm.controls[alias] && this._employeeForm.controls[alias].hasError(error);
    }


    submitEmployee() {
        this._employeeSerivce.queryPost(this.colectData()).subscribe(
            () => {
                this._translate.get('EMPLOYEE_PAGE.ADD_EMPLOYEE.SUCCESS').subscribe((res: string) => {
                    this._snackBarService.open(res, 'ok', { duration: 3000 });
                });
                this.ngOnInit();
            },
            (error) => {
                this._translate.get('EMPLOYEE_PAGE.ADD_EMPLOYEE.ERROR').subscribe((res: string) => {
                    this._snackBarService.open(res, 'ok', { duration: 3000 });
                });
            });
        this._dialogRef.close(true);
    }

    closeDialog() {
        this._dialogRef.close(false);
    }

    colectData(): Employee {

        const employee: Employee = {
            name: this._employeeForm.get('name').value,
            lastName: this._employeeForm.get('lastName').value,
            regularPost: this._employeeForm.get('regularPost').value,
            id: null,
            countOfVacation: this._employeeForm.get('countOfVacation').value,
            countOfChildrenCare: this._employeeForm.get('countOfChildrenCare').value,
            position: this._employeeForm.get('position').value,
        };

        return employee;
    }
}
