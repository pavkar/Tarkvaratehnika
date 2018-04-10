export class Login {
    constructor() {
        this.header = 'Login';
        this.username = '';
        this.password = '';
        this.remember = false;

        this.appName = "adPortal"
        this.count = 3
        this.fbstarter();

    }

    signup() {
        let myUser = { password: this.password, username: this.username }
        console.log(myUser);
    };

    getStatus() {
      this.getName();
      FB.getLoginStatus(function(response) {
          if (response.status === 'connected') {
              console.log("We are logged in");
          } else if (response.status === 'not_authorized') {
              console.log("Not logged in");
          } else {
              console.log("You are not logged into Facebook");
          }
      });
  }

  getName() {
    FB.api('/me', function(response) {
      console.log(JSON.stringify(response));
    });
  }


  login() {
      FB.getLoginStatus(function(response) {
          if (response.status === 'connected') {
            console.log('Logged in.');
          }
          else {
            FB.login();
          }
        });
        this.getStatus();
    
  }

  logout() {
      FB.getLoginStatus(function(response) {
          if (response.status === 'not_authorized') {
            console.log('Logged out.');
          }
          else {
            FB.logout();
          }
        });
        this.getStatus();
  }



  fbstarter() {
      window.fbAsyncInit = function() {
      FB.init({
        appId            : '1555636977846703',
        autoLogAppEvents : true,
        xfbml            : true,
        cookie           : true,
        version          : 'v2.12'
      });

      FB.getLoginStatus(function(response) {
          if (response.status === 'connected') {
              console.log("We are logged in");
          } else if (response.status === 'not_authorized') {
              console.log("Not logged in");
          } else {
              console.log("You are not logged into Facebook");
          }
      });
    };

  
    (function(d, s, id){
       var js, fjs = d.getElementsByTagName(s)[0];
       if (d.getElementById(id)) {return;}
       js = d.createElement(s); js.id = id;
       js.src = "https://connect.facebook.net/en_US/sdk.js";
       fjs.parentNode.insertBefore(js, fjs);
     }(document, 'script', 'facebook-jssdk'));
  }

}

