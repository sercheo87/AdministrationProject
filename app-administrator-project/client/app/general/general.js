'use strict';

angular.module('appAdministratorProjectApp')
  .config(function ($routeProvider) {
    $routeProvider
      .when('/General', {
        templateUrl: 'app/general/general.html',
        controller: 'GeneralCtrl'
      });
  });