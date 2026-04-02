import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // For now, we mock the user ID.
    // Later, this will come from your Keycloak/Auth service.
    const userId = 'f47ac10b-58cc-4372-a567-0e02b2c3d479';

    const authReq = req.clone({
      setHeaders: {
        'X-User-Id': userId
      }
    });

    return next.handle(authReq);
  }
}
