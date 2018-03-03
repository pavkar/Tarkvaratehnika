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
            this.counterUp();
            this.ingredients.push(new Ingredients(this.ingredientsDescription));
            this.ingredientsDescription = '';
        }
    }

    removeIngredient(ingredient) {
        let index = this.ingredients.indexOf(ingredient);
        if (index !== 1) {
            this.counterDown();
            this.ingredients.splice(index, 1);
        }
    }

    counterUp() {
        this.recipesCount += 1;
    }

    counterDown() {
        this.recipesCount -= 1;
    }

    checkRecipes() {
        console.log(this.ingredients);
        for (let ingr in this.ingredients) {
            console.log();
            if (ingr.done) {
                console.log("done");
            }
        }
    }
}