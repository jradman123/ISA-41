import { ResponseUtility } from "./response-utility";

export interface AdventureQuickReservationDto {
    startTime : string;
    endTime : string;
    validUntil : string;
    price : string;
    adventureId : string;
    guestLimit : string;
    utilities : ResponseUtility[];
}
