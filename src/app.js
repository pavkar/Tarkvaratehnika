export class App {
  constructor() {
    this.message = 'Hello World!';
  }



  configureRouter(config, router) {
    this.router = router;
    config.title = 'My Aurelia';
    config.map([
      { route: ['', 'home'],       name: 'home',       moduleId: 'home/index' },
      { route: 'login',            name: 'login',      moduleId: 'login', nav: true, title: 'login' },
      { route: 'test1',            name: 'test1',      moduleId: 'test1', nav: true, title: 'test1' },
      { route: 'app',            name: 'app',      moduleId: 'app', title: 'login' },
    
    ]);
  }
}

