'use strict';

angular.module('appAdministratorProjectApp', [
    'ngCookies',
    'ngResource',
    'ngSanitize',
    'ngRoute',
    'ui.bootstrap',
    'pascalprecht.translate',
    'xeditable',
    'tmh.dynamicLocale'
  ])
  .config(function($routeProvider, $locationProvider, $translateProvider, $httpProvider, tmhDynamicLocaleProvider) {

    $httpProvider.defaults.useXDomain = true;
    delete $httpProvider.defaults.headers.common['X-Requested-With'];

    $routeProvider
      .otherwise({
        redirectTo: '/'
      });

    $locationProvider.html5Mode(true);
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