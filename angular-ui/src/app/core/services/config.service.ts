import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ConfigService {

  private parameters: Map<string, any> = new Map();

  constructor(private http: HttpClient) {}

  loadConfig() {
    return this.http.get<AppParameterDto[]>('/api/v1/parameters').pipe(
      tap(params => params.forEach(p => this.parameters.set(p.key, p.value)))
    );
  }

  get(key: string, defaultValue: any): any {
    return this.parameters.get(key) || defaultValue;
  }
}
