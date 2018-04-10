import {HttpClient, json} from 'aurelia-fetch-client';
let httpClient = new HttpClient();

export class Home {
    constructor() {
      this.author = "";
      this.date = "";
      this.searchRecipies = "";

      this.commentText = "";
      this.comments = [];

      this.dataToShow = {};
      this.getRecipiesAll();
    }

    addComment() {
      this.authorNoName = "Anon";
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
    }

    setUpModal() {

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

    /* Should modify if needed more advanced search for example several names */
    searchByName() {
      if (this.searchRecipies != "") {
        this.getRecipesByNe(this.searchRecipies.replace(" ", ""));
      }
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
      if (this.searchRecipies != "") {
        if (this.searchRecipies.charAt(this.searchRecipies.length - 1) == " ") {
          this.searchRecipies = this.searchRecipies.slice(0, this.searchRecipies.length - 1);
        }
        let jsonToSend = {}
        console.log(this.searchRecipies);
        this.searchRecipies.split(",").forEach(searchparameter => {
          if (searchparameter.charAt(0) == " ") {
            searchparameter = searchparameter.slice(1);
          }

          var jsonAmountAndUnit = {};

          searchparameter = searchparameter.replace(" (", "|");
          searchparameter = searchparameter.replace(")", "");
          searchparameter = searchparameter.split("|");

          jsonAmountAndUnit["amount"] = searchparameter[1].split(" ")[0];
          jsonAmountAndUnit["unit"] = searchparameter[1].split(" ")[1];

          jsonToSend[searchparameter[0]] = jsonAmountAndUnit;
        });

        let stringToSend = "{";
        let keys = Object.keys(jsonToSend);
        keys.forEach(key => {
          stringToSend += key + ":{amount:" + jsonToSend[key]["amount"] + ", unit:" + jsonToSend[key]["unit"] + "}, "
        });
        stringToSend = stringToSend.slice(0, stringToSend.length - 2) + "}";
        this.getRecipesByIngredients(stringToSend);
      } 
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
      let parsedRecipe = JSON.parse(this.dataToShow[recipeKey]);

      this.recipeName.push(this.capitalizeFirstLetter(parsedRecipe["name"]));
      this.descriptions.push(this.capitalizeFirstLetter(parsedRecipe["description"]));
      this.instructions.push(this.capitalizeFirstLetter(parsedRecipe["instructions"]));
      this.dishSize.push(parsedRecipe["size"]);
      this.ingredients.push(parsedRecipe["ingredients"]);
      this.timeToPrepare.push(parsedRecipe["time"]);
    });
    this.manageIngredientsJson();

  }

  manageIngredientsJson() {
    this.ingredientNames = [];
    this.ingredietAmounts = [];
    this.ingredientUnits = [];

    this.ingredients.forEach(ingred => {
      let keys = Object.keys(ingred);
      this.ingredientNames.push(keys);
      let amountsList = [];
      let unitsList = [];
      keys.forEach(key => {
        amountsList.push(ingred[key]["amount"]);
        unitsList.push(ingred[key]["unit"]);
      });
      this.ingredietAmounts.push(amountsList);
      this.ingredientUnits.push(unitsList);
    });
  }
    
}


      
