import { Component, input, output } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-search-bar',
  imports: [ReactiveFormsModule],
  templateUrl: './search-bar.component.html',
  styleUrl: './search-bar.component.css'
})
export class SearchBarComponent {

    addClicked = output<void>();
    searchChanged = output<SearchCriteria>();

    searchForm = this.fb.group({
      query: [''],
      sortBy: ['name'],
      sortDir: ['asc'] // 'asc' or 'desc'
    });

    constructor(private fb: FormBuilder) {}

    toggleSortDirection() {
      const current = this.searchForm.get('sortDir')?.value;
      this.searchForm.patchValue({
        sortDir: current === 'asc' ? 'desc' : 'asc'
      });
    }

    onSearch() {
      const criteria = this.searchForm.getRawValue() ;
      this.searchChanged.emit(criteria as SearchCriteria);
      console.log("onSearch ", criteria);
    }
}

export interface SearchCriteria {
  query?: string;
  sortBy?: string;
  sortDir?: 'asc' | 'desc';
}
