'use strict';

angular.module('appAdministratorProjectApp')
  .config(function ($routeProvider) {
    $routeProvider
      .when('/Administrar', {
        templateUrl: 'app/admin/admin.html',
        controller: 'AdminCtrl'
      });
  });