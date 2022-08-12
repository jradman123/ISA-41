export interface CottageReservation {
    resStart: Date,
    resEnd: Date,
    numberOfPerson: number,
    price: string,
    clientEmail: string,
    objectId: string,
    typeOfRes: string
    haveReport: boolean;
}