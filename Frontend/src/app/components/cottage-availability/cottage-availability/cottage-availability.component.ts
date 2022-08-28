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
import { DatePipe } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { CottageAvailability } from 'src/app/interfaces/cottage-availability';
import { AvailabilityService } from 'src/app/services/availabilityService/availability.service';
import { CottageDto } from 'src/app/interfaces/cottage-list-view';
import { CottageService } from 'src/app/services/CottageService/cottage.service';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { CottageReservation } from 'src/app/interfaces/cottage-reservation';
import { ReservationService } from 'src/app/services/ReservationService/reservation.service';

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
  selector: 'app-cottage-availability',
  templateUrl: './cottage-availability.component.html',
  styleUrls: ['./cottage-availability.component.css']
})
export class CottageAvailabilityComponent implements OnInit {

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
  availabilities: CottageAvailability[];
  startDate!: Date;
  endDate!: Date;
  chosenStartDate!: string;
  chosenEndDate!: string;
  newAvailability: CottageAvailability;
  todayDate: Date = new Date();
  id!: any;
  pastReservations: CottageReservation[];
  currentReservations: CottageReservation[];

  constructor(private router: ActivatedRoute, private modal: NgbModal, private reservationService: ReservationService, private cottageAvailabilityService: AvailabilityService, private _formBuilder: FormBuilder) {
    this.availabilities = [] as CottageAvailability[];
    this.pastReservations = [] as CottageReservation[];
    this.newAvailability = {} as CottageAvailability;
    this.currentReservations = [] as CottageReservation[]
    this.range = this._formBuilder.group({
      start: ['', Validators.required],
      end: ['', Validators.required]
    });
  }

  events: CalendarEvent[] = [
  ];
  ngOnInit(): void {

    this.id = +this.router.snapshot.paramMap.get('id')!;
    this.getAvailabilities();
    this.getCottagePastReservations();
    this.getCottageCurrentReservations();

  }
  getCottagePastReservations() {
    this.reservationService.getPastCottageReservationById(this.id).subscribe({
      next: (res) => {
        this.pastReservations = res
        for (var i = 0; i < this.pastReservations.length; i++) {
          this.startDate = new Date(this.pastReservations[i].resStart);
          this.endDate = new Date(this.pastReservations[i].resEnd);
          this.addPastReservationEvent(this.startDate, this.endDate);
        }
      }
    });

  }

  getCottageCurrentReservations() {
    this.reservationService.getCottageReservationById(this.id).subscribe({
      next: (res) => {
        this.currentReservations = res
        for (var i = 0; i < this.currentReservations.length; i++) {
          this.startDate = new Date(this.currentReservations[i].resStart);
          this.endDate = new Date(this.currentReservations[i].resEnd);
          this.addReservationEvent(this.startDate, this.endDate);
        }
      }
    });
  }

  getAvailabilities() {
    this.cottageAvailabilityService.findAvailabilityByCottage(this.id).subscribe({
      next: (res) => {
        this.availabilities = res
        for (var i = 0; i < this.availabilities.length; i++) {
          this.startDate = new Date(this.availabilities[i].startDate);
          this.endDate = new Date(this.availabilities[i].endDate);
          this.addEvent(this.startDate, this.endDate);
        }
      }
    });
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

  addEvent(start: Date, end: Date): void {
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

  addReservationEvent(start: Date, end: Date): void {
    this.events = [
      ...this.events,
      {
        title: 'Reservation',
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

  addPastReservationEvent(start: Date, end: Date): void {
    this.events = [
      ...this.events,
      {
        title: 'Past reservation',
        start: startOfDay(start),
        end: endOfDay(end),
        color: colors.yellow,
        draggable: false,
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

  dateRangeChange(dateRangeStart: HTMLInputElement, dateRangeEnd: HTMLInputElement) {
    this.chosenStartDate = new Date(dateRangeStart.value).toISOString();
    console.log(dateRangeStart.value)
    this.chosenEndDate = new Date(dateRangeEnd.value).toISOString();
  }

  addAvailability() {
    const s = new Date(this.range.value.start.getTime() - this.range.value.start.getTimezoneOffset() * 60000)
    const e = new Date(this.range.value.end.getTime() - this.range.value.end.getTimezoneOffset() * 60000)
    this.newAvailability.startDate = s.toISOString();
    this.newAvailability.endDate = e.toISOString();
    this.newAvailability.cottageId = this.id;
    this.cottageAvailabilityService.addAvailabilityCottage(this.newAvailability).subscribe(
      {
        next: (res) => {
          this.events = []
          this.availabilities = []
          this.getAvailabilities();
          this.range.reset();

        }
      }
    );
  }

}


