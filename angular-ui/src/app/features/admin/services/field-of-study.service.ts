import { Injectable } from '@angular/core';
import { environment } from '@env/environment';

@Injectable({ providedIn: 'root' })
export class FieldOfStudyService {
  private readonly baseUrl = `${environment.apiUrl}/fields-of-study`;

  constructor(private http: HttpClient) {}

  getAll() {
    return this.http.get<FieldOfStudyDto[]>(this.baseUrl);
  }
}
