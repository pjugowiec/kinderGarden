import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { MatDialog } from '@angular/material';
import { of } from 'rxjs';
import { tap } from 'rxjs/operators';
import { DateType } from 'src/app/application/domain/enums/DateType';
import { DatesRestSerivce } from 'src/app/application/services/rest/dates-rest.service';
import { ConfirmDialogComponent } from '../../../general/confirm-dialog/confirm-dialog.component';
import { Day } from '../calendar.component';


@Component({
    selector: 'callendar-day',
    templateUrl: './calendar-day.component.html',
    styleUrls: ['./calendar-day.component.scss']
})
export class CalendarDayComponent implements OnInit {
    @Input('day') private _day: Day;
    @Input('disable') private _disable;
    @Output() selectOption = new EventEmitter();
    private _dateType: any[];
    private _dateTypeToShow: string;

    constructor(
        private _datesService: DatesRestSerivce,
        private _dialog: MatDialog
    ) {
    }

    get disable() {
        return this._disable;
    }

    get day() {
        return this._day;
    }

    get dateType() {
        return this._dateType;
    }

    get dateTypeToShow() {
        return this._dateTypeToShow;
    }

    ngOnInit() {
        this.initDateType();
    }

    selectOne(type: any, day: Day) {
        if (type == 'Delete') {
            this._dialog.open(ConfirmDialogComponent, {
                data: {
                    title: 'CALENDAR.DELETE_DATE.TITLE',
                    message: 'CALENDAR.DELETE_DATE.DESCRIPTION',
                    onConfirm: of({}).pipe(tap(() => type)),
                    error: 'CALENDAR.DELETE_DATE.ERROR'
                }
            }).afterClosed().subscribe(response => {
                if(response){
                    const itemToSend: any = {
                        type: 'Delete',
                        date: day.date,
                        idFromData: day.idFromData
                    }
                    this.selectOption.emit(itemToSend);
                }
            });
        } else {
            const itemToSend: any = {
                type: type,
                date: day.date
            }
            this.selectOption.emit(itemToSend);
        }
    }

    initDateType() {
        this._dateType = Object.keys(DateType);
        this._dateType.push("Delete");

        if(this._day.dateType != undefined && this._day.dateType != null){
          this._dateTypeToShow = this._day.dateType;

        }
        else this._dateTypeToShow = "";
    }
}
