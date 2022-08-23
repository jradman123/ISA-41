import { ResponseUtility } from "./response-utility";

export interface ShipQuickReservationResponse {
    id: string;
    startTime: Date;
    endTime: Date;
    validUntil: Date;
    price: string;
    shipId: string
    guestLimit: string;
    utilities: ResponseUtility[];
    reserved: boolean;
}
