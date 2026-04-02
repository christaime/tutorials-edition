import { Component, input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, FormArray, Validators, ReactiveFormsModule, FormsModule } from '@angular/forms';
import { CardEditorComponent } from '@features/admin/field-of-study/card-editor/card-editor.component';
import { SearchBarComponent, SearchCriteria } from '@features/admin/field-of-study/search-bar/search-bar.component';

@Component({
  selector: 'app-field-of-study',
  imports: [
    CommonModule , ReactiveFormsModule ,
    CardEditorComponent, SearchBarComponent
    ],
  templateUrl: './field-of-study.component.html',
  styleUrl: './field-of-study.component.css'
})
export class FieldOfStudyComponent {

    fieldOfStudyForm: FormGroup;

    constructor(private fb: FormBuilder) {
      this.fieldOfStudyForm = this.fb.group({
        fields: this.fb.array([]) // This is our FormArray
      });
    }

    get fieldsArray() {
      return this.fieldOfStudyForm.get('fields') as FormArray;
    }

    ngOnInit() {
      this.loadFields();
    }

    loadFields() {
      // Mocking a fetch from your Service
      const mockData = [
        { id: 1, name: 'Computer Science', description: 'Study of computation', imageUrl: '' },
        { id: 2, name: 'Civil Engineering', description: 'Infrastructure design', imageUrl: '' }
      ];

      mockData.forEach(item => this.addFieldToForm(item));
    }

    addFieldToForm(item?: any) {
      const fieldGroup = this.fb.group({
        id: [item?.id || 0],
        name: [item?.name || '', [Validators.required, Validators.minLength(3)]],
        description: [item?.description || ''],
        imageUrl: [item?.imageUrl || ''],
        isNew: [!item] // Helper flag to highlight new cards
      });

      // .insert(0, ...) puts the new card at the top, like CodeSignal
      this.fieldsArray.insert(0, fieldGroup);
    }

    saveField(index: number) {
      const group = this.fieldsArray.at(index);
      if (group.valid) {
        console.log('Saving to Backend:', group.value);
        group.markAsPristine(); // Removes the "dirty" state
      }
    }

  onSearch(event: SearchCriteria){
    console.log("onSearch ", event);
  }
}
