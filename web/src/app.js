import {inject} from 'aurelia-framework';
import {Dependency} from './dependecy';

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
      { route: 'fridge', name: 'fridge', moduleId: 'fridge', nav: true },
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
