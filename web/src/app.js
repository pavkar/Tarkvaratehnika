import {Ingridients} from './ingredients'

export class App {
  constructor() {
    this.message = 'Cool Page Name.';
    this.ingredients = [];
    this.ingredientsDescription = '';
  }
  addIngredient() {
    if (this.ingredientsDescription) {
      this.ingredients.push(new ingredient(this.ingredientsDescription));
      this.ingredientsDescription = '';
    }
  }

  removeIngredient(ingredient) {
    let index = this.ingredients.indexOf(ingredient);
    if (index !== -1) {
      this.ingredients.splice(index, 1);
    }
  }
}
