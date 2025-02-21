
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
import { AvailabilityService } from 'src/app/services/availabilityService/availability.service';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ShipAvailability } from 'src/app/interfaces/ship-availability';
import { ReservationService } from 'src/app/services/ReservationService/reservation.service';
import { CottageReservation } from 'src/app/interfaces/cottage-reservation';
import { AppointmentService } from 'src/app/services/AppointmentService/appointment.service';
import { ShipQuickReservationResponse } from 'src/app/interfaces/ship-quick-reservation-response';

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
  selector: 'app-ship-availability',
  templateUrl: './ship-availability.component.html',
  styleUrls: ['./ship-availability.component.css']
})
export class ShipAvailabilityComponent implements OnInit {

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
  availabilities: ShipAvailability[];
  reservations: CottageReservation[]
  pastReservations: CottageReservation[]
  startDate!: Date;
  endDate!: Date;
  chosenStartDate!: string;
  chosenEndDate!: string;
  newAvailability: ShipAvailability;
  todayDate: Date = new Date();
  id!: any;
  quickReservations: ShipQuickReservationResponse[];


  constructor(private router: ActivatedRoute, private appointmentService: AppointmentService, private reservationService: ReservationService, private modal: NgbModal, private cottageAvailabilityService: AvailabilityService, private _formBuilder: FormBuilder) {
    this.availabilities = [] as ShipAvailability[];
    this.quickReservations = [] as ShipQuickReservationResponse[];

    this.pastReservations = [] as CottageReservation[];
    this.reservations = [] as CottageReservation[];
    this.newAvailability = {} as ShipAvailability;
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
    this.getShipPastReservations();
    this.getShipCurrentReservations();
    this.getCottageQuickReservations();

  }
  getShipPastReservations() {
    this.reservationService.getPastShipReservationById(this.id).subscribe({
      next: (res) => {
        this.pastReservations = res
        for (var i = 0; i < this.pastReservations.length; i++) {
          this.startDate = new Date(this.pastReservations[i].resStart);
          this.endDate = new Date(this.pastReservations[i].resEnd);
          this.addPastReservationEvent(this.startDate, this.endDate, this.pastReservations[0].clientEmail, this.pastReservations[0].numberOfPerson, this.pastReservations[i].price);
        }
      }
    });
  }

  getCottageQuickReservations() {
    this.appointmentService.findAppByShip(this.id).subscribe({
      next: (res) => {
        this.quickReservations = res
        for (var i = 0; i < this.quickReservations.length; i++) {
          this.startDate = new Date(this.quickReservations[i].startTime);
          this.endDate = new Date(this.quickReservations[i].endTime);
          this.addQuickReservations(this.startDate, this.endDate, this.quickReservations[i].guestLimit, this.quickReservations[i].price, this.quickReservations[i].validUntil);
        }
      }
    });
  }

  getShipCurrentReservations() {
    this.reservationService.getShipReservationById(this.id).subscribe({
      next: (res) => {
        this.reservations = res
        for (var i = 0; i < this.reservations.length; i++) {
          this.startDate = new Date(this.reservations[i].resStart);
          this.endDate = new Date(this.reservations[i].resEnd);

          this.addReservationEvent(this.startDate, this.endDate, this.reservations[i].clientEmail, this.reservations[i].numberOfPerson, this.reservations[i].price);
        }
      }
    });
  }
  getAvailabilities() {
    this.cottageAvailabilityService.findAvailabilityByShip(this.id).subscribe({
      next: (res) => {
        this.availabilities = res
        for (var i = 0; i < this.availabilities.length; i++) {
          this.startDate = new Date(this.availabilities[i].startDate);
          this.endDate = new Date(this.availabilities[i].endDate);
          this.addEvent(this.startDate, this.endDate);
        };

      }
    });
  }
  addReservationEvent(start: Date, end: Date, email: any, person: any, price: any): void {
    this.events = [
      ...this.events,
      {
        title: 'Reservation by ' + email + ' for ' + person + ' person for ' + price + ' €',
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

  addPastReservationEvent(start: Date, end: Date, email: any, person: any, price: any): void {
    this.events = [
      ...this.events,
      {
        title: 'Past reservation by ' + email + ' for ' + person + ' person for ' + price + ' €',
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
  addQuickReservations(start: Date, end: Date, person: any, price: any, valid: Date): void {
    this.events = [
      ...this.events,
      {
        title: 'Appointment ' + ' for ' + person + ' person for ' + price + ' €' + '.Valid until ' + valid + '.',
        start: startOfDay(start),
        end: endOfDay(end),
        color: colors.black,
        draggable: false,
        resizable: {
          beforeStart: true,
          afterEnd: true,
        },
      },
    ];
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
    this.newAvailability.shipId = this.id;
    this.cottageAvailabilityService.addAvailabilityShip(this.newAvailability).subscribe(
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



