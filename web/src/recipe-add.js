import {HttpClient, json} from 'aurelia-fetch-client';

let httpClient = new HttpClient();
export class RecipeAdd {
    constructor() {
        this.header = "Add Recipes";
        this.ingredientsList = ['Ingr1', 'Ingr2', 'Ingr3', 'Ingr4', 'Ingr5'];
    }

    myPostData = {

    }
   
    recipeInfo() {
        console.log(this.myPostData)
    }
	
   postRecipe() {
      httpClient.fetch('http://localhost:9000/', {
         method: "POST",
         body: JSON.stringify(this.myPostData)
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
