import {inject} from 'aurelia-framework';
import {Dependency} from './dependecy';
import {Redirect} from 'aurelia-router';

@inject(Dependency)

export class App {

  constructor() {
    this.header = 'Navigation';
    this.content = 'Page info';
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
      { route: 'fridge', name: 'fridge', moduleId: 'fridge', title: 'Fridge', nav: true, settings: { roles: ['admin']} },
      { route: 'recipe-add', name: 'recipe-add', moduleId: 'recipe-add', title: 'Add Recipe', nav: true },

    ]);
  }
  

  created(owningView, myView) {
    // Invoked once the component is created...
  }

  bind(bindingContext, overrideContext) {
      // Invoked once the databinding is activated...
  }

  attached(argument) {
      // Invoked once the component is attached to the DOM...
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
