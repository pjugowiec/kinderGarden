import { Component } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.scss']
})
export class NavigationComponent {

  private _langsToChoose: Array<String>;

  constructor(
    private _translateService: TranslateService,
    private _router: Router,
    private _snackBarService: MatSnackBar
  ) {
    this._langsToChoose = _translateService.getLangs();
  }

  get langsToChoose(): Array<String> {
    return this._langsToChoose;
  }

  public changeLang(choose: string) {
    if(!this._langsToChoose.includes(choose)) {
      this._snackBarService.open(
        this._translateService.instant('MESSAGE.WRONG_LANGUAGE'),
        this._translateService.instant('MESSAGE.SHORT_CONFIRM'), 
        {duration: 2000})
      return;
    }

    this._translateService.use(choose);

  }
}
