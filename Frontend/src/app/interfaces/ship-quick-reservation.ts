import { ResponseUtility } from "./response-utility";

export interface ShipQuickReservationDto {
    startTime: string;
    endTime: string;
    validUntil: string;
    price: string;
    shipId: string;
    guestLimit: string;
    utilities: ResponseUtility[];
}
