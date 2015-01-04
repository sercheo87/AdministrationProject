'use strict';

angular.module('appAdministratorProjectApp')

.controller('GeneralCtrl', function($scope, $http, $log, typeResourceService) {

  typeResourceService.getAll(function(results) {
    $log.info('data devuelta', results);
    $scope.collectionTypesResources = angular.fromJson(results);
  });

  $scope.addTypeResource = function() {
    $scope.newTypeResource = {
      name: '',
      description: ''
    };
    $scope.collectionTypesResources.push($scope.newTypeResource);
  };

  $scope.saveResource = function(data) {
    typeResourceService.saveResource(data, function(results) {
      $log.info('data devuelta', results);
      $scope.collectionTypesResources = angular.fromJson(results);
    });
  };

});