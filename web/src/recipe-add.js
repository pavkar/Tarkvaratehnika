import {HttpClient, json} from 'aurelia-fetch-client';

let httpClient = new HttpClient();
export class RecipeAdd {
    constructor() {
        this.header = "Add Recipes";

        this.recipeName = "";
        this.neededTime = "";
        this.recipeDescription = "";

        this.ingredientsList = ['Ingr1', 'Ingr2', 'Ingr3', 'Ingr4', 'Ingr5'];
        this.selectedIngredients = [];
        
        this.peopleNumber = 0;
    }

    recipeInfo() {
        console.log(this.recipeName);
        console.log(this.selectedIngredients);
        console.log(this.recipeDescription);
        console.log(this.neededTime);
        console.log(this.peopleNumber);
    }

    myPostData = { 
      "name":this.recipeName,
      "ingredients":this.selectedIngredients,
      "instructions":this.recipeDescription,
      "time":this.neededTime,
      "size":this.peopleNumber
   }
	
   postData(myPostData) {
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
