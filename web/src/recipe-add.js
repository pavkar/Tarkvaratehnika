export class RecipeAdd {
    constructor() {
        this.header = "Add Recipes";
        this.name = "";
        this.ingredientsList = ['Ingr1', 'Ingr2', 'Ingr3', 'Ingr4', 'Ingr5'];
        this.selectedIngredients = [];
        this.recipeDescription = "";
    }

    recipeInfo() {
        console.log(this.name);
        console.log(this.selectedIngredients);
        console.log(this.recipeDescription);
    }
    
}