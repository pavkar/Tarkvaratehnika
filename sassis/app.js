import {HttpClient, json} from 'aurelia-fetch-client'


export class App {
  constructor() {
    this.message = 'Hello World!';
  }

  adduser() {

    let client = new HttpClient();

    let userData = {
      "name": "Name",
      "serialnr": 12
    }

    client.fetch('http://localhost:8080/cars/add', {
			'method': "POST",
			'body': json(userData)
		})
			.then(response => response.json())
			.then(data => {
				console.log("Server saatis " + data.name);
		});


    console.log("Yasss")
  }
}
