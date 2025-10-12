export interface User {
    email?: string;
    exp?: number;
    iat?: number;
    [key: string]: any;
}