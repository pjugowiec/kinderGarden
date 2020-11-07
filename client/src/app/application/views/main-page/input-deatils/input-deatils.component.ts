import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material';

@Component({
  selector: 'app-input-deatils',
  templateUrl: './input-deatils.component.html',
  styleUrls: ['./input-deatils.component.scss']
})
export class InputDeatilsComponent implements OnInit {
private _inputForm: FormGroup;
  constructor(
    private _dialogRef: MatDialogRef<InputDeatilsComponent>,
    private _builder: FormBuilder,
  ) { }

  ngOnInit() {
      this.createForm();
  }

  createForm(){
      this._inputForm = this._builder.group({
        nameAndSurname: ['', Validators.required]
      });
  }

  get inputForm(): FormGroup {
      return this._inputForm;
  }

  closeDialog(){
      this._dialogRef.close(false);
  }

  submitDialog(){
      const nameAndSurname: String = this._inputForm.get('nameAndSurname').value;
      this._dialogRef.close(nameAndSurname);
  }
}
