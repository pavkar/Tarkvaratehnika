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
      { route: ['', 'home'],       name: 'home',       moduleId: 'home/index' },
      { route: 'fridge', name: 'fridge', moduleId: 'fridge', nav: true, settings: { roles: ['admin']} },
      { route: 'login', name: 'login', moduleId: 'login', nav: true },
      { route: 'registration', name: 'registration', moduleId: 'registration', nav: true },
      { route: 'recipe-add', name: 'recipe-add', moduleId: 'recipe-add', nav: true },

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
    if (navigationInstruction.getAllInstructions().some(i => i.config.settings.roles.indexOf('admin') !== -1)) {
      var isAdmin = /* insert magic here */false;
      if (!isAdmin) {
        return next.cancel(new Redirect('home'));
      }
    }

    return next();
  }
<<<<<<< HEAD
}
=======
}
>>>>>>> f46c6a3ee1d8fec4127f446fb3cb479e74ca7d66
