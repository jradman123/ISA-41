import { UserStateToken } from "./user-state-token";

export interface AuthenticatedUserDto {
    email : string;
    role : string;
    token : UserStateToken;
    firstLogin : boolean;
}
