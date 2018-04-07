import {inject} from 'aurelia-framework';
import {Dependency} from './dependecy';
import {Redirect} from 'aurelia-router';

@inject(Dependency)

export class App {

  constructor() {
    this.header = 'Navigation';
    this.content = 'Page info';
    this.clicked = false;
    console.log(Dependency);
  }

  updateContent() {
    this.header = 'New Cool Name'
    this.content = 'New Content'
  }

  configureRouter(config, router) {
    this.router = router;
    config.title = 'Aurelia Recipe App';
    config.map([
      { route: ['', 'home'],       name: 'home',       moduleId: 'home/index', title: 'Main Page', nav: true },
      { route: 'login', name: 'login', moduleId: 'login', title: 'Log In', nav: true },
      { route: 'registration', name: 'registration', moduleId: 'registration', title: 'Registration', nav: true },
      { route: 'fridge', name: 'fridge', moduleId: 'fridge', title: 'Fridge', nav: false, settings: { roles: ['admin']} },
      { route: 'recipe-add', name: 'recipe-add', moduleId: 'recipe-add', title: 'Add Recipe', nav: false, },
      { route: 'recipe-view/:id', name: 'recipe-view', moduleId: 'recipe-view', title: 'recipe view', nav: false, },
    ]);
  }

  changeClicked() {
    if (this.clicked) {
      this.clicked = false;
    } else {
      this.clicked = true;
    }
  }

  manageSideBar() {
    this.changeClicked();
    if (this.clicked) {
      this.show_sideBarButton();
    } else {
      this.hide_sideBarButton();
    }
    document.getElementById("wrapper").classList.toggle("active");
  }

  show_sideBarButton() {
    document.getElementById("sideBarButton").style.display = "block";
  }
  
  hide_sideBarButton() {
    document.getElementById("sideBarButton").style.display = "none";
  }

  // When the user clicks on the button, scroll to the top of the document
  topFunction() {
      document.documentElement.scrollTop = 0; // For Chrome, Firefox, IE and Opera
  }

  created(owningView, myView) {
    // Invoked once the component is created...
  }

  bind(bindingContext, overrideContext) {
      // Invoked once the databinding is activated...
  }

  attached() {
      this.manageSideBar();
      window.onscroll = function() {
        if (document.body.scrollTop > 40 || document.documentElement.scrollTop > 40) {
        document.getElementById("scrollBtn").style.display = "block";
        
    } else {
        document.getElementById("scrollBtn").style.display = "none";
    }};
  }

  detached(argument) {
      // Invoked when component is detached from the dom
  }

  unbind(argument) {
      // Invoked when component is unbound...
  }
  

}



  class AuthorizeStep {
    run(navigationInstruction, next) {
      if (navigationInstruction.getAllInstructions().some(i => i.config.settings.auth)) {
        var isLoggedIn = false; // insert magic here;
        if (!isLoggedIn) {
          return next.cancel(new Redirect('login'));
        }
      }

      return next();
    }

    
}

