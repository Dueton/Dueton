import { ValueConverter } from "@angular/compiler/src/render3/view/template"

export interface StatusResponse<T> {
    message: string;
    successfull: boolean;
    value: T;
}