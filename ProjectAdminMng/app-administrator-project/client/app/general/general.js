'use strict';

angular.module('appAdministratorProjectApp')
  .config(function($routeProvider) {
    $routeProvider
      .when('/General/TypeResources', {
        templateUrl: 'app/general/typeResource.html',
        controller: 'TypeResourceCtrl'
      })
      .when('/General/StateActivity', {
        templateUrl: 'app/general/stateActivity.html',
        controller: 'StateActivityCtrl'
      })
      .when('/General', {
        templateUrl: 'app/general/general.html'
      });
  });