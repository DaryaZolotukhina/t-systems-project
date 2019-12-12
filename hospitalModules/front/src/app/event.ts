import { StatusEvent } from './status';
export class Event{
    id: number;
    dateTimeEvent: Date;
    medicine: string;
    procedure: string;
    statusEvent: StatusEvent;
    idPatient: number;
    surnamePatient: string;
    idStaff:number;
}