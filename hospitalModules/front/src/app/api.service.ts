import { Injectable } from '@angular/core';
import { Observable, of} from 'rxjs';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { catchError, tap} from 'rxjs/operators';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: HttpClient) { }

  private handleError(operation = 'operation', result?: any) {
    return (error: any): Observable<any> => {
      console.error(error); // log to console instead
      return of(result as any);
    };
  }

  getEvents(idStaff: number): Observable<any> {
    return this.http.get('http://localhost:18080/events/' + idStaff)
        .pipe(
            tap(event => console.log(event)),
            catchError(this.handleError('getEvents', []))
        );
  }

statusChange(idEvent: number, idStaff: number, status: string): Observable<any> {
    return this.http.get('http://localhost:18080/changeStatus/' + idEvent + '/' + idStaff + '/' + status);
  }

}
