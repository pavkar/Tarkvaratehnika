import {Ingredients} from './ingredients';

export class Fridge {
    constructor() {
        this.recipesCount = 0;
        this.header = 'Fridge';
        this.ingredients = [];
        this.ingredientsDescrpList = [];
        this.ingredientsDescription = '';
    }

    checkActiveIngredients(ingredient) {
        if (ingredient.done) {
          this.counterDown();
        } else {
          this.counterUp();
        }
    }

    addIngredient() {
        if (this.ingredientsDescrpList.indexOf(this.ingredientsDescription) <= -1) {
            this.ingredientsDescrpList.push(this.ingredientsDescription);
            this.ingredients.push(new Ingredients(this.ingredientsDescription));
            this.ingredientsDescription = '';
            this.counterUp();
        }
        
    }

    removeAll() {
      this.ingredients = [];
      this.ingredientsDescrpList = [];
      this.recipesCount = 0;
    }
    
    removeSelected() {
      let toStay = [];
      let toDescrStay = [];
      for (let ingrIndx = 0; ingrIndx < this.ingredients.length; ingrIndx++) {
        if (!this.ingredients[ingrIndx].done) {
          toStay.push(this.ingredients[ingrIndx]);
          toDescrStay.push(this.ingredients[ingrIndx].description); // Check this
        }
      }
      console.log(toDescrStay);
      this.ingredients = toStay;
      this.ingredientsDescrpList = toDescrStay; 
    }

    removeIngredient(ingredient) {
        let index = this.ingredients.indexOf(ingredient);
        this.ingredients.splice(index, 1);
        this.ingredientsDescrpList.splice(this.ingredientsDescrpList.indexOf(ingredient.description), 1);
        if(!ingredient.done) {
          this.counterDown();
        }
        
    }

    counterUp() {
        this.recipesCount += 1;
    }

    counterDown() {
        this.recipesCount -= 1;
    }

    checkRecipes() {
        for (let ingrIndx = 0; ingrIndx < this.ingredients.length; ingrIndx++) {
            if (!this.ingredients[ingrIndx].done) {
                console.log(this.ingredients[ingrIndx].description); 
            }
        }
    }
    
    attached() {
      this.setUpModal();
    }

    setUpModal() {
      console.log("created");
      // Get the modal
      var modal = document.getElementById('myModal');

      // Get the button that opens the modal
      var btn = document.getElementById("openRecipeModal");

      // Get the <span> element that closes the modal
      var span = document.getElementsByClassName("close")[0];

      // When the user clicks on the button, open the modal 
      btn.onclick = function() {
          modal.style.display = "block";
      }

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

    openModal() {
      var modal = document.getElementById('myModal');
      modal.style.display = "block";
    }
}
