import {Component, NgModule, OnInit} from '@angular/core';
import { ApiService } from '../api.service';
import {Event} from '../event';
import { Observable} from 'rxjs';
import {MatDialog} from '@angular/material';
import {DialogService} from "../dialog.service";

@Component({
  selector: 'app-event',
  templateUrl: './event.component.html',
  styleUrls: ['./event.component.css']
})
export class EventComponent implements OnInit {

    data: Event[] = [];
    events: Observable<Event[]>;
    event: Event = new Event();

    constructor(private api: ApiService,
                public dialog: MatDialog,
                private dialogService: DialogService) {
    }

    ngOnInit() {
        this.api.getEvents(1)
            .subscribe(data => {
                this.events = data;
                console.log(this.data);
            }, err => {
                console.log(err);
            });
    }

    statusChange(idEvent: number, idStaff: number, status: string, i: number) {

        /* this.api.statusChange(idEvent, idStaff, status)
             .subscribe(data => {
                 console.log(data);
                 this.events[i] = data;
             }, error => console.log(error));
     }*/

        this.dialogService.openConfirmDialog('Are you sure to change this event status?')
            .afterClosed().subscribe(res => {
                if(res){
                    this.api.statusChange(idEvent, idStaff, status)
                        .subscribe(data => {
                            console.log(data);
                            this.events[i] = data;
                        }, error => console.log(error));
                }
        });

    }
}

