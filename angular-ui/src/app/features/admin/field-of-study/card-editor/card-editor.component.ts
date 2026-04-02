import { Component, Input, output } from '@angular/core';
import { FormGroup, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-card-editor',
  imports: [ReactiveFormsModule],
  templateUrl: './card-editor.component.html',
  styleUrl: './card-editor.component.css'
})
export class CardEditorComponent {
    @Input() fieldGroup!: FormGroup;
    saveClicked = output<any>();
}
