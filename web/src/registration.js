export class Registration {
    constructor() {
        this.header = 'Registartion';
        this.username = '';
        this.email = '';
        this.password = '';
        this.passwordRepeate = '';
    }

    registrate() {
        if (this.password == this.passwordRepeate && this.password.length > 0) {
            let myUser = { email: this.email, username: this.username, password: this.password }
            console.log(myUser);
        }
        
    }
}
