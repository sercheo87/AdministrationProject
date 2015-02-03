'use strict';

angular.module('appAdministratorProjectApp')
  .controller('AdminCtrl', function($scope, $http, $log, $translate, $filter, growl) {

    $scope.projectCancel = function() {
      $scope.project.description = 'eeee';
    };

  });