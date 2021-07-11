
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { TranslateService } from '@ngx-translate/core';
import { take } from 'rxjs/operators';
import { Employee } from 'src/app/models/employee/employee';
import { EmployeeRestService } from 'src/app/services/rest/employee-rest.service';
import { ConfirmDialogComponent } from 'src/app/shared/confirm-dialog/confirm-dialog.component';
import { EmployeeFormComponent } from './employee-form/employee-form.component';

@Component({
	selector: 'app-employee-page',
	templateUrl: './employee-page.component.html',
	styleUrls: ['./employee-page.component.scss']
})
export class EmployeePageComponent implements OnInit {

	private _columns: Array<string> = [
		'Lp',
		'Name',
		'LastName',
		'Position',
		'RegularPost',
		'CountOfChildrenCare',
		'CountOfVacation',
		'Actions'
	];

	get columns(): Array<string> {
		return this._columns;
	}

	private _dataSource: MatTableDataSource<Employee> = new MatTableDataSource();

	get dataSource(): MatTableDataSource<Employee> {
		return this._dataSource;
	}

	constructor(
		private _employeeRestSerivce: EmployeeRestService,
		private _translationService: TranslateService,
		private _snackBarService: MatSnackBar,
		private _dialog: MatDialog
	) { }

	ngOnInit(): void {
		this.getData();
	}

	private getData(): void {
		this._employeeRestSerivce
			.queryGet()
			.pipe(take(1))
			.subscribe((response: Array<Employee>) => {
				this._dataSource = new MatTableDataSource(response);
			}, (error) => {
				this._snackBarService.open(
					this._translationService.instant('ERRORS.GET_DATA'),
					this._translationService.instant('MESSAGE.SHORT_CONFIRM'),
					{ duration: 2000 })
			})
	}

	public applyFilter(event: Event) {
		const filterValue = (event.target as HTMLInputElement).value;
		this._dataSource.filter = filterValue.trim().toLowerCase();
	}

	public openDeleteForm(id: number): void {
		this._dialog.open(ConfirmDialogComponent, {
			data: {
				title: 'EMPLOYEE_PAGE.DELETE_EMPLOYEE_TITLE',
				message: 'EMPLOYEE_PAGE.DELETE_EMPLOYEE',
				onConfirm: this._employeeRestSerivce.queryDelete(id),
				error: this._translationService.instant('ERRORS.DELETE')
			}
		}).afterClosed()
			.subscribe((response) => {
				if (response) {
					this._snackBarService.open(
						this._translationService.instant('GENERAL.SUCCESS_DELETE'),
						this._translationService.instant('MESSAGE.SHORT_CONFIRM'),
						{ duration: 4000 }
					);
					this.getData();
				}
			});
	}

	public openEditForm(id: number): void {

		this._employeeRestSerivce.queryGetById(id)
			.pipe(take(1))
			.subscribe(response => {
				this._dialog.open(EmployeeFormComponent, {
					data: {
						editMode: true,
						employee: response
					}
				}).afterClosed().subscribe((response: boolean) => {
					this.getData();
				});
			},
				(error) => {
					this._snackBarService.open(
						this._translationService.instant('ERRORS.GET_DATA'),
						this._translationService.instant('MESSAGE.SHORT_CONFIRM'),
						{ duration: 2000 })
				});
	}

	public openAddForm(): void {
		this._dialog.open(EmployeeFormComponent, {
			data: {
				editMode: false
			}
		}).afterClosed().subscribe((response: boolean) => {
			this.getData();
		});
	}

}