import { Injectable } from '@angular/core';
import {
  CanActivate,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
  Router
} from '@angular/router';
import { AuthService } from '../services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private authService: AuthService, private router: Router) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): boolean {

    // 1. Check Authentication
    if (!this.authService.isAuthenticated()) {
      this.router.navigate(['/login']);
      return false;
    }

    // 2. Check Roles (if specified in the route data)
    const requiredRoles = route.data['roles'] as Array<string>;

    if (!requiredRoles || requiredRoles.length === 0) {
      return true; // No specific role required for this route
    }

    const userHasRole = requiredRoles.some(role => this.authService.hasRole(role));

    if (userHasRole) {
      return true;
    } else {
      // User is logged in but doesn't have the right permissions
      this.router.navigate(['/unauthorized']);
      return false;
    }
  }
}
