import { Injectable,Injector } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable, from } from 'rxjs';
import { mergeMap } from 'rxjs/operators';
import { KeycloakService } from 'keycloak-angular';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private injector: Injector) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
     const keycloak = this.injector.get(KeycloakService);
     return from(keycloak.getToken()).pipe(
       mergeMap(token => {
         if (token) {
           const authReq = request.clone({
             setHeaders: {
               Authorization: `Bearer ${token}`
             }
           });
           return next.handle(authReq);
         }
         return next.handle(request);
       })
     );
  }
}
