import {
  Component,
  OnInit,
  ChangeDetectionStrategy,
  ViewChild,
  TemplateRef,
} from '@angular/core';
import {
  startOfDay,
  endOfDay,
  subDays,
  addDays,
  endOfMonth,
  isSameDay,
  isSameMonth,
  addHours,
} from 'date-fns';
import {
  CalendarEvent,
  CalendarEventAction,
  CalendarEventTimesChangedEvent,
  CalendarView,
} from 'angular-calendar';
import { EventColor } from 'calendar-utils';
import { Subject } from 'rxjs';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { InstructorAvailabilityService } from 'src/app/services/InstructorAvailabilityService/instructor-availability.service';
import { InstructorAvailabilityDto } from 'src/app/interfaces/instructor-availability-dto';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { NewInstructorAvailability } from 'src/app/interfaces/new-instructor-availability';
import { AdventureReservation } from 'src/app/interfaces/adventure-reservation';
import { ReservationService } from 'src/app/services/ReservationService/reservation.service';
import { DatePipe } from '@angular/common';
import { AdventureQuickReservationService } from 'src/app/services/AdventureQuickReservationService/adventure-quick-reservation.service';
import { AdventureQuickReservationDto } from 'src/app/interfaces/adventure-quick-reservation-dto';
import { AdventureQuickReservationResponse } from 'src/app/interfaces/adventure-quick-reservation-response';

const colors: Record<string, EventColor> = {
  red: {
    primary: '#ad2121',
    secondary: '#FAE3E3',
  },
  blue: {
    primary: '#1e90ff',
    secondary: '#D1E8FF',
  },
  yellow: {
    primary: '#e3bc08',
    secondary: '#FDF1BA',
  },
};


@Component({
  selector: 'app-instructor-availability',
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './instructor-availability.component.html',
  styleUrls: ['./instructor-availability.component.css']
})
export class InstructorAvailabilityComponent implements OnInit {

  range = new FormGroup({
    start: new FormControl(),
    end: new FormControl(),
  });
 

  @ViewChild('modalContent', { static: true }) modalContent: TemplateRef<any> | undefined;

  view: CalendarView = CalendarView.Month;

  CalendarView = CalendarView;

  viewDate: Date = new Date();

  modalData: {
    action: string;
    event: CalendarEvent;
  } | undefined;

  actions: CalendarEventAction[] = [
    {
      label: '<i class="fas fa-fw fa-pencil-alt"></i>',
      a11yLabel: 'Edit',
      onClick: ({ event }: { event: CalendarEvent }): void => {
        this.handleEvent('Edited', event);
      },
    },
    {
      label: '<i class="fas fa-fw fa-trash-alt"></i>',
      a11yLabel: 'Delete',
      onClick: ({ event }: { event: CalendarEvent }): void => {
        this.events = this.events.filter((iEvent) => iEvent !== event);
        this.handleEvent('Deleted', event);
      },
    },
  ];

  refresh = new Subject<void>();
  activeDayIsOpen: boolean = false;
  availabilities : InstructorAvailabilityDto[];
  startDate! : Date;
  endDate! : Date;
  chosenStartDate! : string;
  chosenEndDate! : string;
  newAvailability : NewInstructorAvailability;
  todayDate:Date = new Date();
  reservations : AdventureReservation[];
  clientEmail!: any;
  quickReservations : AdventureQuickReservationResponse[];

  constructor(private modal: NgbModal,private instructorAvailabilityService : InstructorAvailabilityService,private _formBuilder: FormBuilder,
    private reservationService : ReservationService,public datepipe: DatePipe, private adventureQuickReservationService : AdventureQuickReservationService) {
    this.availabilities = [] as InstructorAvailabilityDto[];
    this.newAvailability = {} as NewInstructorAvailability;
    this.reservations = [] as AdventureReservation[];
    this.quickReservations = [] as AdventureQuickReservationResponse[];
    this.range = this._formBuilder.group({
      start: ['',Validators.required],
      end: ['', Validators.required]
    });
  }

  events: CalendarEvent[] = [
  ];
  ngOnInit(): void {
    this.getAvailabilities();
    this.getReservations();
    this.getQuickReservations(); 
    
  }

  getQuickReservations() {
    this.adventureQuickReservationService.getAllForInstructor().subscribe({
      next: (res) => {
        this.quickReservations = res
        for (var i = 0; i < this.quickReservations.length; i++) {
          this.startDate = new Date(this.quickReservations[i].startTime);
          this.endDate = new Date(this.quickReservations[i].endTime);

          this.addQuickReservationEvent(this.startDate, this.endDate, this.quickReservations[i].guestLimit, this.quickReservations[i].price);
        
    }}}); 
  }
  getReservations(){
    this.reservationService.getReservationsForInstructor().subscribe({
      next: (res) => {
        this.reservations = res
        for (var i = 0; i < this.reservations.length; i++) {
          this.startDate = new Date(this.reservations[i].resStart);
          this.endDate = new Date(this.reservations[i].resEnd);
          this.clientEmail = this.reservations[i].clientEmail;

          this.addReservationEvent(this.startDate, this.endDate, this.clientEmail, this.reservations[i].numberOfPerson, this.reservations[i].price);
        
    }}}); 

  }
  getAvailabilities() {
    this.instructorAvailabilityService.getAllForInstructor().subscribe({
      next: (res) => {
        this.availabilities = res
        for(var i = 0; i < this.availabilities.length; i++){
          this.startDate =  new Date(this.availabilities[i].startDate);   
          this.endDate = new Date(this.availabilities[i].endDate);
          this.addEvent(this.startDate,this.endDate);
        }; 
        
    }}); 
  }


  dayClicked({ date, events }: { date: Date; events: CalendarEvent[] }): void {
    if (isSameMonth(date, this.viewDate)) {
      if (
        (isSameDay(this.viewDate, date) && this.activeDayIsOpen === true) ||
        events.length === 0
      ) {
        this.activeDayIsOpen = false;
      } else {
        this.activeDayIsOpen = true;
      }
      this.viewDate = date;
    }
  }

  eventTimesChanged({
    event,
    newStart,
    newEnd,
  }: CalendarEventTimesChangedEvent): void {
    this.events = this.events.map((iEvent) => {
      if (iEvent === event) {
        return {
          ...event,
          start: newStart,
          end: newEnd,
        };
      }
      return iEvent;
    });
    this.handleEvent('Dropped or resized', event);
  }

  handleEvent(action: string, event: CalendarEvent): void {
    this.modalData = { event, action };
    this.modal.open(this.modalContent, { size: 'lg' });
  }

  addEvent(start : Date,end : Date): void {
    this.events = [
      ...this.events,
      {
        title: 'Available',
        start: startOfDay(start),
        end: endOfDay(end),
        color: colors.blue,
        draggable: true,
        resizable: {
          beforeStart: true,
          afterEnd: true,
        },
      },
    ];
  }

  addReservationEvent(start: Date, end: Date, email: any, person: any, price: any): void {
    let s = start;
    let e = end;
    this.events = [
      ...this.events,
      {
        title: 'Reservation by ' + email + ' for ' + person + ' person - ' + price + ' €.Start date: ' + this.datepipe.transform(s, 'MMM d, y, h:mm a') + '. End date: ' + this.datepipe.transform(e, 'MMM d, y, h:mm a') + '.',
        start: startOfDay(start),
        end: endOfDay(end),
        color: colors.red,
        draggable: true,
        resizable: {
          beforeStart: true,
          afterEnd: true,
        },
      },
    ];
  }

  addQuickReservationEvent(start: Date, end: Date,person: any, price: any): void {
    let s = start;
    let e = end;
    this.events = [
      ...this.events,
      {
        title: 'Promotion for ' + person + ' person - ' + price + ' €.Start date: ' + this.datepipe.transform(s, 'MMM d, y, h:mm a') + '. End date: ' + this.datepipe.transform(e, 'MMM d, y, h:mm a') + '.',
        start: startOfDay(start),
        end: endOfDay(end),
        color: colors.yellow,
        draggable: true,
        resizable: {
          beforeStart: true,
          afterEnd: true,
        },
      },
    ];
  }

  deleteEvent(eventToDelete: CalendarEvent) {
    this.events = this.events.filter((event) => event !== eventToDelete);
  }

  setView(view: CalendarView) {
    this.view = view;
  }

  closeOpenMonthViewDay() {
    this.activeDayIsOpen = false;
  }

  dateRangeChange(dateRangeStart : HTMLInputElement, dateRangeEnd: HTMLInputElement){
    this.chosenStartDate=new Date(dateRangeStart.value).toISOString();
    console.log(dateRangeStart.value)
    this.chosenEndDate=new Date(dateRangeEnd.value).toISOString();
  }

  addAvailability() {
    const s = new Date( this.range.value.start.getTime() -  this.range.value.start.getTimezoneOffset() * 60000)
    const e = new Date( this.range.value.end.getTime() -  this.range.value.end.getTimezoneOffset() * 60000)
    this.newAvailability.startDate=s.toISOString();
    this.newAvailability.endDate = e.toISOString();
    this.instructorAvailabilityService.addNewAvailability(this.newAvailability).subscribe(
      {
        next: (res) => {
          this.events = []
          this.availabilities = []
          this.getAvailabilities();
          this.range.reset();
          
      }}
    );
  }

}


