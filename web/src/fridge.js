import {Ingredients} from './ingredients';

export class Fridge {
    constructor() {
        this.recipesCount = 0;
        this.header = 'Fridge';
        this.ingredients = [];
        this.ingredientsDescription = '';
    }

<<<<<<< HEAD
    checkActiveIngredients(ingredient) {
        if (ingredient.done) {
          this.counterDown();
        } else {
          this.counterUp();
=======
    checkActiveIngredients() {
        this.recipesCount = this.ingredients.length;
        for (let ingrIndx = 0; ingrIndx < this.ingredients.length; ingrIndx++) {
            if (this.ingredients[ingrIndx].done) {
                --this.recipesCount;
            }
>>>>>>> f46c6a3ee1d8fec4127f446fb3cb479e74ca7d66
        }
    }

    addIngredient() {
        if (this.ingredientsDescription) {
            this.ingredients.push(new Ingredients(this.ingredientsDescription));
            this.ingredientsDescription = '';
            this.counterUp();
        }
        
    }

<<<<<<< HEAD
    removeAll() {
      this.ingredients = [];
      this.recipesCount = 0;
    }

    removeSelected() {
      let toStay = [];
      for (let ingrIndx = 0; ingrIndx < this.ingredients.length; ingrIndx++) {
        if (!this.ingredients[ingrIndx].done) {
          toStay.push(this.ingredients[ingrIndx]);
        }
      }
      this.ingredients = toStay;
    }

=======
>>>>>>> f46c6a3ee1d8fec4127f446fb3cb479e74ca7d66
    removeIngredient(ingredient) {
        let index = this.ingredients.indexOf(ingredient);
        if (index !== 1) {
            this.ingredients.splice(index, 1);
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
<<<<<<< HEAD
}
=======
}
>>>>>>> f46c6a3ee1d8fec4127f446fb3cb479e74ca7d66
