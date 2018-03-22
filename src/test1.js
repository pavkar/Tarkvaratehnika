import {login} from './login';
import {inject} from 'aurelia-framework';

@inject(login)
export class test1 {
    constructor(login) {
        this.login = login;
    }

    tryStatus() {
        return this.login.getStatus();
    }


}