import {Ingredients} from './ingredients';

export class Fridge {
    constructor() {
        this.recipesCount = 0;
        this.header = 'Fridge';
        this.ingredients = [];
        this.ingredientsDescription = '';
    }

    addIngredient() {
        if (this.ingredientsDescription) {
            this.recipesCount += 1;
            this.ingredients.push(new Ingredients(this.ingredientsDescription));
            this.ingredientsDescription = '';
        }
    }

    removeIngredient(ingredient) {
        let index = this.ingredients.indexOf(ingredient);
        if (index !== 1) {
            this.recipesCount -= 1;
            this.ingredients.splice(index, 1);
        }
    }
}