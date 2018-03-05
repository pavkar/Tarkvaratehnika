export class Registration {
    constructor() {
        this.header = 'Registartion';
        this.email = '';
        this.password = '';
        this.passwordRepeate = '';
    }

    registrate() {
        if (this.password == this.passwordRepeate && this.password.length > 0) {
            let myUser = { email: this.email, password: this.password }
            console.log(myUser);
        }
        
    }
}
