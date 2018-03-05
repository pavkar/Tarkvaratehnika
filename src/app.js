import { HttpClient } from 'aurelia-fetch-client'


export class App {
  constructor() {
    this.message = 'Hello World!';
  }

  adduser() {

    let client = new HttpClient();

    let userData = {
      "firstName": "MyFirstName",
      "lastName": "lastNAME"
    }

    client.fetch('http://localhost:8080/', {
      'method': "POST",
      'body': {}
    }).then(response => response.json())
      .then(data => {console.log("Server saatis: " + data.firstName)});


    console.log("Yasss")
  }
}
