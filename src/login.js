export class Login {
    constructor() {
        this.header = 'Login';
        this.email = '';
        this.password = '';
    }
    signup() {
        let myUser = { email: this.email, password: this.password }
        console.log(myUser);
    };


}

