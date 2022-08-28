import { ResponseUtility } from "./response-utility";

export interface AdventureReservation {
    resStart: Date,
    resEnd: Date,
    numberOfPerson: number,
    price: string,
    clientEmail: string,
    objectId: string,
    typeOfRes: string
    haveReport: boolean;
    utilities : ResponseUtility[];
}
