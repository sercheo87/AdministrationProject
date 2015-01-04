'use strict';

angular.module('appAdministratorProjectApp', [
    'ngCookies',
    'ngResource',
    'ngSanitize',
    'ngRoute',
    'ngAnimate',
    'ui.bootstrap',
    'pascalprecht.translate',
    'xeditable',
    'tmh.dynamicLocale',
    'angular-growl'
  ])
  .config(function($routeProvider, $locationProvider, $translateProvider, $httpProvider, tmhDynamicLocaleProvider, growlProvider) {

    $httpProvider.defaults.useXDomain = true;
    delete $httpProvider.defaults.headers.common['X-Requested-With'];

    $routeProvider
      .otherwise({
        redirectTo: '/'
      });

    $locationProvider.html5Mode(true);
    growlProvider.globalTimeToLive({
      success: 1000,
      error: 2000,
      warning: 3000,
      info: 4000
    });
    tmhDynamicLocaleProvider.localeLocationPattern('../bower_components/angular-i18n/angular-locale_{{locale}}.js');

    $translateProvider
      .translations('en-us', translations_en)
      .translations('es-ec', translations_es)
      .preferredLanguage('es-ec');
  })
  .run(function(editableOptions) {
    editableOptions.theme = 'bs3'; // bootstrap3 theme. Can be also 'bs2', 'default'
  });

angular.module('xeditable').directive('editableBsdateNew', ['editableDirectiveFactory',
  function(editableDirectiveFactory) {
    return editableDirectiveFactory({
      directiveName: 'editableBsdateNew',
      inputTpl: '<datepicker ng-model="dt" min-date="minDate" show-weeks="true" class="well well-sm"></datepicker>'
    });
  }
]);