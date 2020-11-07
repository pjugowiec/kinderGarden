import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Dates } from 'src/app/application/domain/external/Dates';
import { DatesRestSerivce } from 'src/app/application/services/rest/dates-rest.service';

export class Day {
    id: number;
    color: string;
    date: ParsedDate;
    dateType: string;
    idFromData: number;
}
export class ParsedDate {
    day: number;
    month: number;
    year: number;
}

@Component({
    selector: 'app-calendar',
    templateUrl: './calendar.component.html',
    styleUrls: ['./calendar.component.scss']
})
export class CalendarComponent implements OnInit {
    private _todayDate: ParsedDate;
    private _selectedDate: ParsedDate;
    private _noDays: number;
    private _startingWeekDay: number;
    private _endingWeekDay: number;
    private _nextMonth: number;
    private _prevMonth: number;
    private _days: Day[];
    private _daysToCheck: Day[] = [];
    private _startingOffsetDays: Day[];
    private _endingOffsetDays: Day[];
    private _nameplates: String[];
    private _monthName: string;
    private _employeeId: number;
    private _dates: Date[];
    private _datesToShow: Dates[] = [];
    private _datesToSend: Dates[] = [];

    constructor(
        @Inject(MAT_DIALOG_DATA) public data: any,
        private _datesService: DatesRestSerivce,
        private _dialogRef: MatDialogRef<CalendarComponent>,
    ) {
        this._employeeId = data.id;
    }

    get selectedDate() {
        return this._selectedDate;
    }

    get nameplates() {
        return this._nameplates;
    }

    get monthName() {
        return this._monthName;
    }

    get days() {
        return this._days;
    }

    get startingOffsetDays() {
        return this._startingOffsetDays;
    }

    get endingOffsetDays() {
        return this._endingOffsetDays;
    }

    ngOnInit() {
        this.initDates();
        this.updateCallendar();
        this.updateFromServer();
    }

    filterReports(date: ParsedDate) {
        return this._dates.filter(el =>
            new Date(el).toLocaleDateString() == new Date(date.year, date.month, date.day).toLocaleDateString()
        );
    }

    initDates(): void {
        this._nameplates = ['Pon', 'Wt', 'Śr', 'Czw', 'Pt', 'Sob', 'Niedz'];

        this._todayDate = {
            day: new Date().getDate(),
            month: new Date().getMonth(),
            year: new Date().getFullYear()
        }

        this._selectedDate = this._todayDate;
        this._selectedDate.day = 1;
    }

    updateFromServer() {
        this._datesService.queryGet(this._employeeId).subscribe(response => {
            response.forEach(value => {
                this._days.forEach(day => {
                    if (day.date.day == value.day && day.date.month == value.month && day.date.year == value.year) {
                        if (value.dateType == "Sunday") {
                            day.color = "warn";
                        } else if (value.dateType == "Saturday") {
                            day.color = "success";
                        } else {
                            day.dateType = value.dateType;
                            day.color = "primary";
                        }
                        day.idFromData = value.id;
                    }
                });
            });

        });
    }

    updateCallendar(): void {
        this.updateDays();
        this.updateMonths();
        this.updateLayout();
    }

    updateDays(): void {
        this._noDays = this.getDaysInMonth(this._selectedDate.month);
        this._startingWeekDay = new Date(this._selectedDate.year, this._selectedDate.month, 0).getDay() + 1;
        this._endingWeekDay = new Date(this._selectedDate.year, this._selectedDate.month, this._noDays).getDay();
    }

    updateMonths(): void {
        this._monthName = new Date(this._selectedDate.year, this._selectedDate.month).toLocaleString('default', { month: 'long' });
        this._prevMonth = this._selectedDate.month <= 0 ? 11 : this._selectedDate.month - 1;
        this._nextMonth = this._selectedDate.month >= 11 ? 0 : this._selectedDate.month + 1;
    }

    updateLayout(): void {
        const prevMonthDate = { year: this._selectedDate.year, month: this._prevMonth, day: this.getDaysInMonth(this._prevMonth) };
        const nextMonthDate = { year: this._selectedDate.year, month: this._nextMonth, day: this.selectedDate.day };

        this._startingOffsetDays = this.setDays(prevMonthDate, this._startingWeekDay - 1, true);
        this._days = this.setDays(this._selectedDate, this._noDays);

        if ((7 - this._endingWeekDay) != 7) {
            this._endingOffsetDays = this.setDays(nextMonthDate, 7 - this._endingWeekDay);
        } else {
            this._endingOffsetDays = this.setDays(nextMonthDate, 0);
        }

    }

    getDaysInMonth(month): number {
        return new Date(this._selectedDate.year, month + 1, 0).getDate();
    }

    setDays(date: ParsedDate, range: number, descending: boolean = false) {
        if (range <= 0) return null;
        const arr = descending ? [...Array(range).keys()].reverse() : [...Array(range).keys()];

        return arr.map(x => {
            const queryDay = descending ? date.day - x : date.day + x;
            const queryDate: ParsedDate = { year: date.year, month: date.month, day: queryDay }
            const dateToCheck: Date = new Date(date.year, date.month, queryDay);
            return {
                id: queryDate.day,
                date: queryDate,
                color: '',
                dateType: '',
                idFromData: 0
            }
        });
    }

    nextMonth() {
        this._selectedDate.year = this._selectedDate.month == 11 ? this._selectedDate.year + 1 : this._selectedDate.year;
        this._selectedDate.month = this._nextMonth;
        this.updateCallendar();
        this.updateFromServer();
    }

    prevMonth() {
        this._selectedDate.year = this._selectedDate.month == 0 ? this._selectedDate.year - 1 : this._selectedDate.year;
        this._selectedDate.month = this._prevMonth;
        this.updateCallendar();
        this.updateFromServer();
    }

    isCurrent(day_no: number) {
        return day_no == this._todayDate.day && this._selectedDate.month == this._todayDate.month && this._selectedDate.year == this._todayDate.year;
    }

    saveAllDates() {
        this._datesService.queryUpdateDates(this._datesToSend, this._employeeId).subscribe(response => {
        });
        this._dialogRef.close();
    }

    selectOption(selected) {
        console.log(selected)
        if(selected.type == 'Delete') {
            this._days.map(day => {
                if(selected.date == day.date)
                {
                    day.color = '';
                    day.dateType = '';
                }
            });
        } else {
            this._days.map(day => {
                if(selected.date == day.date) 
                {
                    day.color = 'primary';
                    day.dateType = selected.type;
                }
            });
        }

        const newDate: Date = new Date(selected.date.year, selected.date.month, selected.date.day);
        const dateToSend: Dates = {
            id: selected.idFromData,
            date: newDate,
            dateType: selected.type,
            employeeDto: {
                id: this._employeeId
            }
        };

        this._datesToSend.push(dateToSend);

        

    }

    closeDialog() {
      this._dialogRef.close(false);
  }
}
