import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private userRoles: string[] = ['ROLE_USER', 'ROLE_ADMIN']; // Mock roles

  isAuthenticated(): boolean {
    // Check if token exists in localStorage or session
    return true;
  }

  hasRole(role: string): boolean {
    return this.userRoles.includes(role);
  }

  getUserId(): string {
    return 'f47ac10b-58cc-4372-a567-0e02b2c3d479';
  }
}
