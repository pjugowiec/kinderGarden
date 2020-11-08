import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MatSnackBar } from '@angular/material';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { TranslateService } from '@ngx-translate/core';
import { take } from "rxjs/operators";
import { Employee } from 'src/app/application/domain/external/Employee';
import { EmployeeRestSerivce } from 'src/app/application/services/rest/employee-rest.service';

@Component({
    selector: 'app-employee-details',
    templateUrl: './employee-details.component.html',
    styleUrls: ['./employee-details.component.scss']
})
export class EmployeeDetailsComponent implements OnInit {
    private _employeeData: Employee;
    private _employeeForm: FormGroup;
    private _regularPost: Array<String> = ['1/2','3/4', '1'];
    private _inputDisabled: boolean = true;
    private _cancelButton: boolean = false;
    private _checkbox: boolean;

    constructor(
        @Inject(MAT_DIALOG_DATA) public data: any,
        private _employeeRestService: EmployeeRestSerivce,
        private _dialogRef: MatDialogRef<EmployeeDetailsComponent>,
        private _snackBarService: MatSnackBar,
        private _translate: TranslateService,
        private _builder: FormBuilder,
        private _employeeSerivce: EmployeeRestSerivce
    ) { }

    ngOnInit() {
        this.createForm();
        this.getData();
    }

    getData() {
        this._employeeRestService.queryEmployee(this.data.id).pipe(take(1)).subscribe(response => {
            this._employeeData = response;
            this.insertValues();
            (error) => {
                this._snackBarService.open('Problem z pobraniem danych', 'ok', { duration: 3000 });
            }
        });
    }

    createForm() {
        this._employeeForm = this._builder.group({
            name: ['', [Validators.required, Validators.minLength(1), Validators.maxLength(50)]],
            regularPost: ['', Validators.required],
            countOfVacation: ['', Validators.required],
            countOfChildrenCare: ['', Validators.required],
            position: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(50)]]
        });
        this._employeeForm.disable();
    }

    insertValues() {
        this._employeeForm.get('name').setValue(this._employeeData.name);
        this._employeeForm.get('regularPost').setValue(this._employeeData.regularPost);
        this._employeeForm.get('countOfVacation').setValue(this._employeeData.countOfVacation);
        this._employeeForm.get('countOfChildrenCare').setValue(this._employeeData.countOfChildrenCare);
        this._employeeForm.get('position').setValue(this._employeeData.position);
    }

    editEmployee() {
        if (this._checkbox) {
            this._employeeForm.disable();

        } else {
            this._employeeForm.enable();
        }
    }

    cancel() {
        this._cancelButton = false;
        this._inputDisabled = true;
        this._employeeForm.disable();
    }
    get checkbox(): boolean {
        return this._checkbox;
    }
    get employeeForm(): FormGroup {
        return this._employeeForm;
    }

    get regularPost(): any {
        return this._regularPost;
    }

    get inputDisabled(): any {
        return this._inputDisabled;
    }
    get cancelButton(): any {
        return this._cancelButton;
    }

    set checkbox(value: boolean) {
        this._checkbox = value;
    }

    getError(alias: string, error: string): boolean {
        return this._employeeForm && this._employeeForm.controls && this._employeeForm.controls[alias] && this._employeeForm.controls[alias].hasError(error);
    }

    submitEmployee() {
        this._cancelButton = false;
        this._inputDisabled = true;
        this._employeeForm.disable();
        this._employeeSerivce.queryPut(this.colectData(), this._employeeData.id).subscribe((response) => {
            this._translate.get('EMPLOYEE_PAGE.EDIT_EMPLOYEE.SUCCESS').subscribe((res: string) => {
                this._snackBarService.open(res, 'ok', { duration: 3000 });
            });
        },
        (error) => {
            this._translate.get('EMPLOYEE_PAGE.EDIT_EMPLOYEE.ERROR').subscribe((res: string) => {
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
