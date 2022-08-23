import { ResponseUtility } from "./response-utility";

export interface AppointmentDto {

    id: string;
    startDate: Date;
    endDate: Date;
    capacity: String;
    price: String;
    validUntil: Date;
    isReserved: Boolean;
    cottageId: String;
    shipId: String;
    utilities: ResponseUtility[];






}
