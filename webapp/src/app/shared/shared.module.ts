import { NgModule } from '@angular/core';
import { NavigationComponent } from './navigation/navigation.component';
import { ConfirmDialogComponent } from './confirm-dialog/confirm-dialog.component';
import { MaterialModule } from '../material.module';
import { TranslateModule } from '@ngx-translate/core';

@NgModule({
    declarations: [
        NavigationComponent,
        ConfirmDialogComponent
    ],
    imports: [
        MaterialModule,
        TranslateModule.forChild()
    ],
    exports: [
        NavigationComponent,
        ConfirmDialogComponent
    ],
    providers: [],
    entryComponents: [ConfirmDialogComponent]
})
export class SharedModule {}