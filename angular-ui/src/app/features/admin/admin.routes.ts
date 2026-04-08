import { Routes } from '@angular/router';
import { FieldOfStudyComponent } from '@features/admin/field-of-study/field-of-study.component';
import { LayoutComponent } from '@features/admin/layout/layout.component';
import { AuthGuard } from '@core/guards/auth.guard';

export const adminRoutes: Routes = [
  { path: 'admin',
      component: LayoutComponent,
      canActivate: [AuthGuard],
      children: [
          { path: 'field-of-study',
              component: FieldOfStudyComponent,
              canActivate: [AuthGuard],
              data: { roles: ['ADMIN','USER'] , strategy: "any"}
          }
      ]
  }
];
