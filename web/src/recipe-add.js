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

        this.pictureInput = "";

    }

    //Manage uplodaed picture fix preview
    managePicture() {
      var preview = document.querySelector('img');
      var file    = document.getElementById("recipe-pic").files[0];
      var reader  = new FileReader();
    
      reader.addEventListener("load", function () {
        preview.src = reader.result;
      }, false);
    
      if (file) {
        reader.readAsDataURL(file);
      }
    }

    manageUnits() {
      this.ingredientsUnits = [];
      for (var i = 0; i < this.ingredientAmountSize; i++) {
        this.ingredientsUnits.push(document.getElementById("ingr-numb"+i).value);
      }
      this.recipeInfoCheck();
    }
    
    //Splits string in every \n
    //Checks if ingredients list have any "" empty ingredients
    //Checks if amount have something except for digits and replaces them with ""
    manageReview() {
      this.managePicture();
      var ingredientsReviewToCheck = this.ingredients.split("\n");
      this.ingredientsReview = [];
      for (var ingrIndx = 0; ingrIndx < ingredientsReviewToCheck.length; ingrIndx++) {
        if(ingredientsReviewToCheck[ingrIndx] != "") {
          this.ingredientsReview.push(ingredientsReviewToCheck[ingrIndx]);
        }
      }
      this.ingredinetsCheckedAmounts = [];
      var ingredientsAmountReview = this.ingredientsAmount.split("\n");

      for (var ingrIndx = 0; ingrIndx < ingredientsAmountReview.length; ingrIndx++) {
        var newAmount = "";
        for (var amount = 0; amount < ingredientsAmountReview[ingrIndx].length; amount++) {
          if (ingredientsAmountReview[ingrIndx].charCodeAt(amount) > 47 && ingredientsAmountReview[ingrIndx].charCodeAt(amount) < 58) {
            newAmount += "" + ingredientsAmountReview[ingrIndx].charAt(amount)
          }
        }
        if (newAmount == "") {
          newAmount = "0";
        }
        this.ingredinetsCheckedAmounts.push(newAmount);
      }
      this.checkIngredientAmountSize();
    }

    //Checks amount list be same size as ingredients replaces empty places with 0 and if too much delets
    checkIngredientAmountSize() {
      if (this.ingredientsReview.length > this.ingredinetsCheckedAmounts.length) {
        var needToAdd = this.ingredientsReview.length - this.ingredinetsCheckedAmounts.length;
        for (var i = 0; i < needToAdd; i++) {
          this.ingredinetsCheckedAmounts.push("0");
        }
      } else if (this.ingredientsReview.length < this.ingredinetsCheckedAmounts.length) {
        this.ingredinetsCheckedAmounts.length = this.ingredientsReview.length;
      }
      this.ingredientAmountSize = this.ingredinetsCheckedAmounts.length;
    }
    
    //Prepares data for sending
    recipeInfoCheck() {
      var ingredientsJson = {};
      for (var ingrIndx = 0; ingrIndx < this.ingredientAmountSize; ingrIndx++) {
        var ingrDescr = {};
        ingrDescr["amount"] = this.ingredinetsCheckedAmounts[ingrIndx];
        ingrDescr["unit"] = this.ingredientsUnits[ingrIndx];
        ingredientsJson[this.ingredientsReview[ingrIndx]] = ingrDescr;
      }
      
      //console.log(ingredientsJson);
      //var pictureInput = document.getElementById("recipe-pic").files[0];
      //console.log(pictureInput);

      this.myPostData = {
        "ingredients":JSON.stringify(ingredientsJson),
        "name":this.name,
        "description":this.description,
        "instructions":this.instructions,
        "size":parseInt(this.size),
        "time":this.timeHourCook + ":" + this.timeMinCook,
        "image":"notaimage.jpg"
      }
      console.log(this.myPostData)
      this.postRecipe(this.myPostData)
    }
  
  //TODO write normal error handling
   postRecipe(myPostData) {
    httpClient.fetch('http://localhost:8080/recipes/add', {
        method: "POST",
        body: JSON.stringify(myPostData)
    })
    .then(response => response.json())
    .then(data => {
        console.log(data);
        this.notifyUserAboutSuccessfulUpload();
    })
    .catch(err => {
      alert("Upload failed");
    });
   }

   myUpdateData = {
      id: 1
   }
	
   updateData(myUpdateData) {
    httpClient.fetch('', {
        method: "PUT",
        body: JSON.stringify(myUpdateData)
    })
  
    .then(response => response.json())
    .then(data => {
        console.log(data);
    });
   }

  attached() {
    this.setUpModal();
  }

  setUpModal() {
    // Get the modal
    var modal = document.getElementById('modalReview');

    // Get the <span> element that closes the modal
    var span = document.getElementsByClassName("close")[0];

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

  openReviewModal() {
    this.manageReview();
    var modal = document.getElementById('modalReview');
    modal.style.display = "block";
  }

  notifyUserAboutSuccessfulUpload() {
    this.timeHourCook = "";
    this.timeMinCook = "";
    this.size = 0;

    this.name = "";
    this.description = "";
    this.ingredients = "";
    this.ingredientsAmount = "";
    this.instructions = "";

    document.getElementById("recipe-pic").value = "";
    var modal = document.getElementById('modalReview');
    modal.style.display = "none";

    alert("Upload Successful");
  }
    
}
