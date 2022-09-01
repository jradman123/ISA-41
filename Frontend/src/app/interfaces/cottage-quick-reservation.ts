import { ResponseUtility } from "./response-utility";

export interface CottageQuickReservationDto {
    startTime: string;
    endTime: string;
    validUntil: string;
    price: string;
    cottageId: string;
    guestLimit: string;
    utilities: ResponseUtility[];
}
