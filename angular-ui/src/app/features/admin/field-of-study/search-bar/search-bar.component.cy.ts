import { SearchBarComponent } from './search-bar.component';
import { ReactiveFormsModule } from '@angular/forms';

describe('SearchBarComponent', () => {

  it('should mount with correct default values', () => {
    cy.mount(SearchBarComponent, {
      imports: [ReactiveFormsModule]
    });

    cy.get('input[formControlName="query"]').should('have.value', '');
    cy.get('select[formControlName="sortBy"]').should('have.value', 'name');
  });

  it('should toggle sort direction and change icon', () => {
    cy.mount(SearchBarComponent, { imports: [ReactiveFormsModule] });

    // Initial state is 'north' (asc)
    cy.get('[data-cy="sortDir-btn"]').should('contain', 'north');

    // Click toggle
    cy.get('[data-cy="sortDir-btn"]').click();

    // Should change to 'south' (desc)
    cy.get('[data-cy="sortDir-btn"]').should('contain', 'south');
  });

  it('should trigger search when the user presses Enter in the input field', () => {
    const searchSpy = cy.spy().as('searchSpy');

    cy.mount(
      `<app-search-bar (searchChanged)="onSearch($event)"></app-search-bar>`,
      {
        imports: [SearchBarComponent,ReactiveFormsModule],
        componentProperties: {
          onSearch: searchSpy
        }
      }
    );

    // Type the search term AND the Enter key in one command
    cy.get('[data-cy="query-input"]').type('Cypress Testing{enter}');

    // Assert that onSearch was triggered without clicking the button
    cy.get('@searchSpy').should('have.been.calledWith', {
      query: 'Cypress Testing',
      sortBy: 'name',
      sortDir: 'asc'
    });
  });

it('should emit addClicked when the green Plus button is clicked', () => {
    const addSpy = cy.spy().as('addSpy');

    cy.mount(
      `<app-search-bar (addClicked)="onAdd()"></app-search-bar>`,
      {
        imports: [SearchBarComponent,ReactiveFormsModule],
        componentProperties: {
          onAdd: addSpy
        }
      }
    );

    cy.get('[data-cy="add-btn"]').click();
    cy.get('@addSpy').should('have.been.calledOnce');
  });

  it('should emit searchChanged with form values when Search button is clicked', () => {
    const searchSpy = cy.spy().as('searchSpy');

    cy.mount(
      `<app-search-bar (searchChanged)="onSearch($event)"></app-search-bar>`,
      {
        imports: [SearchBarComponent,ReactiveFormsModule],
        componentProperties: {
          onSearch: searchSpy
        }
      }
    );

    // 1. Type into the query input
    cy.get('[data-cy="query-input"]').type('Angular Signals');

    // 2. Change the select dropdown
    cy.get('[data-cy="sortBy-select"]').select('recent');

    // 3. Click the search button
    cy.get('[data-cy="search-btn"]').click();

    // 4. Verify the emitted object matches the Form's RawValue
    cy.get('@searchSpy').should('have.been.calledWith', {
      query: 'Angular Signals',
      sortBy: 'recent',
      sortDir: 'asc'
    });
  });
});
