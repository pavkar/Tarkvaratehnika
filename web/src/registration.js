export class Registration {
    constructor() {
        this.header = 'Registartion';
        this.email = '';
        this.password = '';
        this.passwordRepeate = '';
    }

    registrate() {
        if (this.password == this.passwordRepeate && this.password.length > 0) {
<<<<<<< HEAD
            let myUser = { email: this.email, password: this.password }
=======
            var myUser = { email: this.email, password: this.password }
>>>>>>> f46c6a3ee1d8fec4127f446fb3cb479e74ca7d66
            console.log(myUser);
        }
        
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> f46c6a3ee1d8fec4127f446fb3cb479e74ca7d66
