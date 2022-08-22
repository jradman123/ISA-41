import { ResponseUtility } from "./response-utility";

export interface AdventureQuickReservationResponse {
    id : String;
    startTime : Date;
    endTime : Date;
    validUntil : Date;
    price : String;
    adventureId : String
    guestLimit : String;
    utilities : ResponseUtility[];
}
