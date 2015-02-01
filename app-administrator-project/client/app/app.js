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
    'angular-growl',
    'angularMoment',
    'ngPatternRestrict',
    'swd.inspector-gadget',
    'angularFileUpload'
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
      success: 4000,
      error: 4000,
      warning: 2000,
      info: 1000
    });
    growlProvider.messagesKey("messages");
    growlProvider.messageTextKey("message");
    growlProvider.messageSeverityKey("severity");
    growlProvider.messageTitleKey('title');
    growlProvider.onlyUniqueMessages(true);

    $httpProvider.interceptors.push(growlProvider.serverMessagesInterceptor);

    tmhDynamicLocaleProvider.localeLocationPattern('../bower_components/angular-i18n/angular-locale_{{locale}}.js');

    $translateProvider
      .translations('en-us', translations_en)
      .translations('es-ec', translations_es)
      .preferredLanguage('es-ec');
  })
  .run(function(editableOptions) {
    editableOptions.theme = 'bs3'; // bootstrap3 theme. Can be also 'bs2', 'default'
  });

angular.module('appAdministratorProjectApp').directive('editableBsdateNew',
  function(editableDirectiveFactory) {
    return editableDirectiveFactory({
      directiveName: 'editableBsdateNew',
      inputTpl: '<datepicker ng-model="dt" min-date="minDate" show-weeks="true" class="well well-sm"></datepicker>'
    });
  });