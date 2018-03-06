import {HttpClient, json} from 'aurelia-fetch-client';

let httpClient = new HttpClient();
export class RecipeAdd {
    constructor() {
        this.header = "Add Recipes";
        this.name = "";
        this.time = "";
        this.instructions = "";
        this.size = 0;
        
        this.ingredientsList = ['Ingr1', 'Ingr2', 'Ingr3', 'Ingr4', 'Ingr5'];
        this.ingredientsSelected = [];
    }

    myPostData = {

    }
   
    recipeInfoCheck() {
        var ingrString = "";
        for (let ingr of this.ingredientsSelected) {
          ingrString += ingr + ","
        }
        ingrString = ingrString.slice(0, ingrString.length-1);
        this.myPostData = {
          "ingredients":ingrString,
          "name":this.name,
          "instructions":this.instructions,
          "size":parseInt(this.size),
          "time":this.time
        }
        console.log(this.myPostData)
        this.postRecipe(this.myPostData)
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
