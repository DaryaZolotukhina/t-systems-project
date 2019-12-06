import { Component, OnInit } from '@angular/core';
import { ApiService } from '../api.service';
import {Event} from '../event';
import { Observable,Subject } from 'rxjs';
//import {FormControl,FormGroup,Validators} from '@angular/forms'; 
//import {DataTables} from 'angular-datatables' ; 

@Component({
  selector: 'app-event',
  templateUrl: './event.component.html',
  styleUrls: ['./event.component.css']
})
export class EventComponent implements OnInit {

data: Event[] = [];
isLoadingResults = true;
events: Observable<Event[]>;  
  event : Event=new Event();  
  

  constructor(private api: ApiService) { }

  ngOnInit() {
    this.api.getProducts(1)
    .subscribe(data =>{  
      this.events =data;   
      //this.data = res;
      console.log(this.data);
      this.isLoadingResults = false;
    }, err => {
      console.log(err);
      this.isLoadingResults = false;
    });
  }

  ajaxEngine(idEvent: number,idStaff: number,status:string){
    let result,
          self = this;
  this.api.ajaxEngine(idEvent,idStaff,status)
    .subscribe(
      data => {
        console.log('events' + data);
        result=data;
      },
      error => {
        console.log(error);
      }
    );
  }

}
