import {HttpClient, json} from 'aurelia-fetch-client';

let httpClient = new HttpClient();
export class RecipeAdd {
    constructor() {
        this.header = "Add Recipes";

        this.timeHourCook = "";
        this.timeMinCook = "";
        this.size = 0;

        this.name = "";
        this.description = "";
        this.ingredients = "";
        this.ingredientsAmount = "";
        this.instructions = "";

    }

    myPostData = {

    }
    
   
    recipeInfoCheck() {
        var ingrString = this.ingredients.replace("\n", ",");
        var ingrAmountString = this.ingredientsAmount.replace("\n", ",");

        var inputElement = document.getElementById("recipe-pic").files[0];;

        console.log(inputElement);

        this.myPostData = {
          "ingredients":ingrString,
          "amount":ingrAmountString,
          "name":this.name,
          "description":this.description,
          "instructions":this.instructions,
          "size":parseInt(this.size),
          "time":this.timeHourCook + ":" + this.timeMinCook
        }
        console.log(this.myPostData)
        //this.postRecipe(this.myPostData)
    }
	
   postRecipe(myPostData) {
      httpClient.fetch('http://localhost:9000/', {
         method: "POST",
         body: JSON.stringify(myPostData)
      })
      .then(response => response.json())
      .then(data => {
         console.log(data);
      });
   }

   myUpdateData = {
      id: 1
   }
	
   updateData(myUpdateData) {
      httpClient.fetch('http://jsonplaceholder.typicode.com/posts/1', {
         method: "PUT",
         body: JSON.stringify(myUpdateData)
      })
		
      .then(response => response.json())
      .then(data => {
         console.log(data);
      });
   }

   
    
}
