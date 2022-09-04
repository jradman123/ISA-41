export interface ReviewResponse {
    id : string;
    reservationId : string;
    comment : string;
    mark : string;
    unapproved : boolean;
    approved : boolean;

}
