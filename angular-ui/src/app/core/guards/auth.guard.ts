import { ActivatedRouteSnapshot, CanActivateFn, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { inject } from '@angular/core';
import { AuthGuardData, createAuthGuard } from 'keycloak-angular';


const isAccessAllowed = async (route: ActivatedRouteSnapshot, state: RouterStateSnapshot, authData: AuthGuardData): Promise<boolean | UrlTree> => {

  const router = inject(Router);
  const { authenticated, grantedRoles, keycloak } = authData;

  // 1. Check if user is logged in
  if (!authenticated) {
    await keycloak.login({
      redirectUri: window.location.origin + state.url
    });
    return false;
  }

  // 2. Check for required roles from route data
  const requiredRoles = route.data['roles'] as string[];
  const strategy = route.data['strategy'] || 'any';

  // If no specific roles are required, allow access
  if (!requiredRoles || requiredRoles.length === 0) {
    return true;
  }

  // 3. Apply the Strategy
  let hasAccess = false;

  if (strategy === 'all') {
    // AND logic: Every required role must be in the user's realmRoles
    hasAccess = requiredRoles.every(role => 
      grantedRoles.realmRoles.includes(role)
    );
  } else {
    // OR logic: At least one required role must be present
    hasAccess = requiredRoles.some(role => 
      grantedRoles.realmRoles.includes(role)
    );
  }

  if (!hasAccess) {
    return router.parseUrl('/unauthorized');
  }

  return true;
};


export const AuthGuard = createAuthGuard<CanActivateFn>(isAccessAllowed);
