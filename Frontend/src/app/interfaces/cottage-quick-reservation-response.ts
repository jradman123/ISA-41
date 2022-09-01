import { ResponseUtility } from "./response-utility";

export interface CottageQuickReservationResponse {
    id: string;
    startTime: Date;
    endTime: Date;
    validUntil: Date;
    price: string;
    cottageId: string
    guestLimit: string;
    utilities: ResponseUtility[];
    reserved: boolean;
}
