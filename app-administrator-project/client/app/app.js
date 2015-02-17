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
  'angularFileUpload',
  'ui.select'
])
  .config(function($routeProvider, $locationProvider, $translateProvider, $httpProvider, tmhDynamicLocaleProvider, growlProvider) {

    $httpProvider.defaults.useXDomain = true;
    delete $httpProvider.defaults.headers.common['X-Requested-With'];

    $routeProvider
      .when('/Administrar', {
        templateUrl: 'app/admin/admin.html',
        controller: 'AdminCtrl'
      })
      .when('/Projects/:projectId', {
        templateUrl: 'app/projects/projects.html',
        controller: 'ProjectsCtrl'
      })
      .when('/Main', {
        templateUrl: 'app/main/main.html',
        controller: 'MainCtrl'
      })
      .when('/Beneficiary', {
        templateUrl: 'app/beneficiary/beneficiary.html',
        controller: 'BeneficiaryCtrl'
      })
      .otherwise({
        redirectTo: '/Main'
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
      .usePostCompiling(true)
      .preferredLanguage('es-ec');
  })
  .filter('propsFilter', function() {
    return function(items, props) {
      var out = [];

      if (angular.isArray(items)) {
        items.forEach(function(item) {
          var itemMatches = false;

          var keys = Object.keys(props);
          for (var i = 0; i < keys.length; i++) {
            var prop = keys[i];
            var text = props[prop].toLowerCase();
            if (item[prop].toString().toLowerCase().indexOf(text) !== -1) {
              itemMatches = true;
              break;
            }
          }

          if (itemMatches) {
            out.push(item);
          }
        });
      } else {
        // Let the output be the input untouched
        out = items;
      }

      return out;
    }
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