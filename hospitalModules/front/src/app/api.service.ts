import { Injectable } from '@angular/core';
import { Observable, of, throwError } from 'rxjs';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { catchError, tap, map } from 'rxjs/operators';
import { Event } from './event';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};
//const apiUrl = "/api/v1/products";

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: HttpClient) { }

  private handleError (operation = 'operation', result?: any) {
    return (error: any): Observable<any> => {
  
      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead
  
      // Let the app keep running by returning an empty result.
      return of(result as any);
    };
  }

  getProducts(idStaff: number): Observable<any> {
    return this.http.get("http://localhost:18080/events/"+ idStaff)
      .pipe(
        tap(event => console.log(event)),
        catchError(this.handleError('getEvents', []))
      );
  }

  ajaxEngine(idEvent: number,idStaff: number,status:string): Observable<any> {
      return this.http.get('http://localhost:18080/changeStatus/' + idEvent + '/' + idStaff+'/'+status, httpOptions)
        .pipe(
          tap(event => console.log(event)),
          catchError(this.handleError('getEvents', []))
        );
    }

}
