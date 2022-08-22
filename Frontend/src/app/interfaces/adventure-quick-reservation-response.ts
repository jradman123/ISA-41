import { ResponseUtility } from "./response-utility";

export interface AdventureQuickReservationResponse {
    id : string;
    startTime : Date;
    endTime : Date;
    validUntil : Date;
    price : string;
    adventureId : string
    guestLimit : string;
    utilities : ResponseUtility[];
    reserved : boolean;
}
