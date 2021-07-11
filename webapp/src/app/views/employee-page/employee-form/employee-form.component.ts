import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TranslateService } from '@ngx-translate/core';
import { Employee } from 'src/app/models/employee/employee';
import { EmployeeRestService } from 'src/app/services/rest/employee-rest.service';

@Component({
	selector: 'app-employee-form',
	templateUrl: './employee-form.component.html',
	styleUrls: ['./employee-form.component.scss']
})
export class EmployeeFormComponent implements OnInit {

	private readonly _isEditMode: boolean;
	private _employeeToFill!: Employee;
	private _employeeForm!: FormGroup;

	get employeeForm(): FormGroup {
		return this._employeeForm;
	}

	get isEditMode(): boolean {
		return this._isEditMode;
	}

	constructor(@Inject(MAT_DIALOG_DATA) data:
		{
			editMode: boolean,
			employee?: Employee
		},
		private _dialogRef: MatDialogRef<EmployeeFormComponent>,
		private _formBuilder: FormBuilder,
		private _snackBarService: MatSnackBar,
		private _translateService: TranslateService,
		private _employeeRestService: EmployeeRestService
	) {
		this._isEditMode = data.editMode;
		this.checkIfExistIfYesAssign(data.employee);
	}

	ngOnInit(): void {
		this.createForm();
		if (this._isEditMode) { this.fillForm() }
	}

	public employeeFormHasError(alias: string, error: string): boolean {
		return this._employeeForm.controls[alias] && this._employeeForm.controls[alias].hasError(error);
	}

	public clearForms(): void {
		this.createForm();
		this._snackBarService.open(this._translateService.instant('GENERAL.INFO.FORM_CLEAR'), 'ok', { duration: 3000 });
	}

	public exit(): void {
		this._dialogRef.close(false);
	}

	private fillForm(): void {
		this._employeeForm.get('name')?.setValue(this._employeeToFill.name);
		this._employeeForm.get('lastname')?.setValue(this._employeeToFill.lastname);
		this._employeeForm.get('position')?.setValue(this._employeeToFill.position);
		this._employeeForm.get('regularPost')?.setValue(this._employeeToFill.regularPost);
		this._employeeForm.get('countOfChildrenCare')?.setValue(this._employeeToFill.countOfChildrenCare);
		this._employeeForm.get('countOfVacation')?.setValue(this._employeeToFill.countOfVacation);
	}

	private checkIfExistIfYesAssign(employee?: Employee): void {
		if (employee) {
			this._employeeToFill = employee;
		}
	}

	private createForm(): void {
		this._employeeForm = this._formBuilder.group({
			name: ['', Validators.required],
			lastname: ['', Validators.required],
			position: ['', Validators.required],
			regularPost: ['', Validators.required],
			countOfChildrenCare: [0, [Validators.required, Validators.min(0)]],
			countOfVacation: [0, [Validators.required, Validators.min(0)]],
		});
	}

	public save(): void {
		let employee: Employee = {
			id: this._isEditMode ? this._employeeToFill.id : 0,
			name: this._employeeForm.get('name')?.value,
			lastname: this._employeeForm.get('lastname')?.value,
			position: this._employeeForm.get('position')?.value,
			regularPost: this._employeeForm.get('regularPost')?.value,
			countOfChildrenCare: this._employeeForm.get('countOfChildrenCare')?.value,
			countOfVacation: this._employeeForm.get('countOfVacation')?.value
		}

		this._isEditMode ? this.sendToUpdate(employee) : this.sendToCreate(employee);
		this._dialogRef.close();
	}

	private sendToUpdate(employee: Employee): void {
		this._employeeRestService.queryPut(employee.id, employee).subscribe((response) => {
			this._snackBarService.open(
				this._translateService.instant('GENERAL.SUCCESS_SAVE'),
				this._translateService.instant('MESSAGE.SHORT_CONFIRM'),
				{ duration: 3000 });
		},
			(error) => {
				this._snackBarService.open(
					this._translateService.instant('ERRORS.SAVE'),
					this._translateService.instant('MESSAGE.SHORT_CONFIRM'),
					{ duration: 3000 });
			});
	}

	private sendToCreate(employee: Employee): void {
		this._employeeRestService.queryPost(employee).subscribe((response) => {
			this._snackBarService.open(
				this._translateService.instant('GENERAL.SAVE_SUCCES'),
				this._translateService.instant('MESSAGE.SHORT_CONFIRM'),
				{ duration: 3000 });
		},
			(error) => {
				this._snackBarService.open(
					this._translateService.instant('ERRORS.SAVE'),
					this._translateService.instant('MESSAGE.SHORT_CONFIRM'),
					{ duration: 3000 });
			});
	}

}
