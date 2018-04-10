import {HttpClient, json} from 'aurelia-fetch-client';
let httpClient = new HttpClient();

export class Home {
    constructor() {
      this.author = "";
      this.date = "";
      this.searchRecipies = "";
      this.commentText = "";
      this.comments = [];
      this.dataToShow = {
        "id1": 
      {"instructions":"yolo","image":"egg.jpg","size":2,"name":"milk and milk","ingredients":"{egg:{amount:92, unit:pieces}, milk:{amount:8.5, unit:l}, water:{amount:8.5, unit:l}}","description":"Lahe description","time":"02:22"}, 
        "id2": 
      {"instructions":"yolo","image":"egg.jpg","size":2,"name":"donut and donut","ingredients":"{egg:{amount:12, unit:pieces}, milk:{amount:6.5, unit:l}}","description":"Cool description","time":"00:52"},
      "id3": 
      {"instructions":"ylo","image":"egg.jpg","size":5,"name":"donut and ","ingredients":"{egg:{amount:25, unit:pieces}, milk:{amount:0.5, unit:l}}","description":"Cool description","time":"00:02"}
    }
      this.manageDataToShow();
      //this.getRecipiesAll();
      //setInterval(() => this.setUpModal(), 1000);
    }

    addComment() {
      this.authorNoName = "Anon";
      //this.setDate();
      this.comments.push(this.commentText);
    }

    //Configure date to not update everytime (Make new class).
    setDate() {
      var newDate = new Date();
      var year = newDate.getFullYear();
      var mounth = newDate.getMonth();
      var day = newDate.getDate();
      var hours = newDate.getHours();
      var min = newDate.getMinutes();
      this.date =  hours + ":" + min + " " + day + "/" + mounth + "/" + year

    }

    attached() {
      this.setUpModal();
      //this.manageDataToShow();
      //console.log(this.dataToShow)
      //this.getRecipiesAll();
      //console.log(this.dataToShow[0]);
    }

    setUpModal() {
      console.log("created");

      // Get the modal
      var modal = document.getElementById('myModal');

      // Get the button that opens the modal
      var btn = document.getElementById("openRecipeModal");

      // Get the <span> element that closes the modal
      var span = document.getElementsByClassName("close")[0];

      // When the user clicks on <span> (x), close the modal
      span.onclick = function() {
          modal.style.display = "none";
      }

      // When the user clicks anywhere outside of the modal, close it
      window.onclick = function(event) {
          if (event.target == modal) {
              modal.style.display = "none";
          }
      }
    }

    openModal(index) {
      this.openedModalIndex = index.id;
      var modal = document.getElementById('myModal');
      modal.style.display = "block";
    }

    /* Search for all recipies*/
    getRecipiesAll() {     
      httpClient.fetch('http://localhost:8080/recipies/all')
      .then(response => response.json())
      .then(data => {
        console.log(data);
        this.dataToShow = data;
        this.manageDataToShow();
      });
    }

    searchRecipies() {
      console.log(this.searchRecipies)
    }
    /* Should modify if needed more advanced search for example several names */
    searchByName() {
      this.getRecipesByName(this.searchRecipies.replace(" ", ""));
    }

    /* Variable recipeName is name of recipe */
    getRecipesByName(recipeName) {
      console.log(recipeName);     
      httpClient.fetch('http://localhost:8080/search/name/' + recipeName)
      .then(response => response.json())
      .then(data => {
        console.log(data);
        this.dataToShow = data;
        this.manageDataToShow();
      });
    }

    searchByIngredients() {

    }
    /* Variable ingredientsToSearch is json of ingredients {ingredient1:{amount:number, unit:unit}, ingredient2:{jne}}*/
    getRecipesByIngredients(ingredientsToSearch) {
      httpClient.fetch('http://localhost:8080/search/ingredients/' + ingredientsToSearch)
      .then(response => response.json())
      .then(data => {
        console.log(data);
        this.dataToShow = data;
        this.manageDataToShow();
      });
    }

  capitalizeFirstLetter(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
  }

  //TODO make as one object (additional class)
  manageDataToShow() {
    var keys = Object.keys(this.dataToShow);

    this.avaibleRecipiesNumb = keys.length;
    this.recipeName = [];
    this.descriptions = [];
    this.instructions = [];
    this.dishSize = [];
    this.ingredients = [];
    this.timeToPrepare = [];

    console.log(keys);
    keys.forEach(recipeKey => {
      this.recipeName.push(this.capitalizeFirstLetter(this.dataToShow[recipeKey]["name"]));
      this.descriptions.push(this.capitalizeFirstLetter(this.dataToShow[recipeKey]["description"]));
      this.instructions.push(this.capitalizeFirstLetter(this.dataToShow[recipeKey]["instructions"]));
      this.dishSize.push(this.dataToShow[recipeKey]["size"]);
      this.ingredients.push(this.dataToShow[recipeKey]["ingredients"]);
      this.timeToPrepare.push(this.dataToShow[recipeKey]["time"]);
    });

    this.manageIngredientsJson();
  }

  manageIngredientsJson() {
    
  }
    
}


      
