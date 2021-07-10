import { Component } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  
  constructor(
    private _translationService: TranslateService,
    private _titleService: Title) { 
    _translationService.setDefaultLang('pl');
    _translationService.get('TITLE').subscribe((text: string) => _titleService.setTitle(text));
    
  }
}
