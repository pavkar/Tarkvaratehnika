export class Registration {
    constructor() {
        this.email = '';
        this.password = '';
        this.passwordRepeate = '';
    }

    registrate() {
        if (this.password == this.passwordRepeate) {
            var myUser = { email: this.email, password: this.password }
            console.log(myUser);
        }
        
    }
}