
export class Home {
    constructor() {
      this.author = "";
      this.date = "";
      this.ingredients = "";
      this.commentText = "";
      this.comments = [];
      //setInterval(() => this.update(), 1000);
    }

    searchRecipies() {
      console.log(this.ingredients);
    }

    addComment() {
      this.authorNoName = "Anon";
      //this.setDate();
      this.comments.push(this.commentText);
    }

    //Configure date to not update everytime (Make new class).
    setDate() {
      var newDate = new Date();
      var year = newDate.getFullYear();
      var mounth = newDate.getMonth();
      var day = newDate.getDate();
      var hours = newDate.getHours();
      var min = newDate.getMinutes();
      this.date =  hours + ":" + min + " " + day + "/" + mounth + "/" + year
    }

    attached() {
      console.log("created");
      // Get the modal
      var modal = document.getElementById('myModal');

      // Get the button that opens the modal
      var btn = document.getElementById("myBtn");

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
    
}


      
