'use strict';

angular.module('appAdministratorProjectApp')
  .controller('AdminCtrl', function ($scope, $http) {
    $scope.project=[];
    $scope.project.name = '';
    $scope.project.description = '';
    $scope.project.dateStart = '';
    $scope.project.duration = '';

    $scope.projectSave = function(){
        $scope.project.description = 'rrr';
    };

    $scope.projectCancel = function(){
        $scope.project.description = 'eeee';
    };
  });