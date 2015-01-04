'use strict';

angular.module('appAdministratorProjectApp')
  .controller('GeneralCtrl', function($scope, $http, $log, typeResourceService) {

    $scope.addTypeResource = function() {
      $scope.newTypeResource = {
        name: '',
        description: ''
      };
      $scope.collectionTypesResources.push($scope.newTypeResource);
    };

    $scope.saveTypeResource = function(data) {
      typeResourceService.saveResource(data, function(results) {
        $log.info('RESPUESTA SERVIDOR:', results);
      });
      $scope.getTypeResource();
    };

    $scope.removeTypeResource = function(data) {
      typeResourceService.removeResource(data, function(results) {
        $log.info('RESPUESTA SERVIDOR:', results);
        $scope.getTypeResource();
      });
    };

    $scope.getTypeResource = function() {
      typeResourceService.getAll(function(results) {
        $log.info('RESPUESTA SERVIDOR:', results);
        $scope.collectionTypesResources = angular.fromJson(results);
      });
    };

    $scope.getTypeResource();
  });