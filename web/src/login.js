export class Login {
    constructor() {
        this.header = 'Login';
        this.username = '';
        this.password = '';
        this.remember = false;
    }
    signup() {
        let myUser = { password: this.password, username: this.username }
        console.log(myUser);
    };


}

