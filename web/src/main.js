import environment from './environment';
<<<<<<< HEAD
import 'fetch';
=======
>>>>>>> f46c6a3ee1d8fec4127f446fb3cb479e74ca7d66

export function configure(aurelia) {
  aurelia.use
    .standardConfiguration()
    .basicConfiguration()
    .feature('resources')
    .defaultBindingLanguage()
    .defaultResources()
    .developmentLogging()
    .router()
    .history()
    .eventAggregator()

  if (environment.debug) {
    aurelia.use.developmentLogging();
  }

  if (environment.testing) {
    aurelia.use.plugin('aurelia-testing');
  }

  aurelia.start().then(() => aurelia.setRoot());

  
}
