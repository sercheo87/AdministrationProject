'use strict';

angular.module('appAdministratorProjectApp', [
    'ngCookies',
    'ngResource',
    'ngSanitize',
    'ngRoute',
    'ui.bootstrap',
    'pascalprecht.translate',
    'xeditable'
  ])
  .config(function($routeProvider, $locationProvider, $translateProvider, $httpProvider) {

    $httpProvider.defaults.useXDomain = true;
    delete $httpProvider.defaults.headers.common['X-Requested-With'];

    $routeProvider
      .otherwise({
        redirectTo: '/'
      });

    $locationProvider.html5Mode(true);

    $translateProvider
      .translations('en_US', translations_en)
      .translations('es_EC', translations_es)
      .preferredLanguage('es_EC');

  })
  .run(function(editableOptions) {
    editableOptions.theme = 'bs3'; // bootstrap3 theme. Can be also 'bs2', 'default'
  });