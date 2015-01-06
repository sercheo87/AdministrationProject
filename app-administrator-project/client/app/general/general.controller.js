'use strict';

angular.module('appAdministratorProjectApp')
  .controller('GeneralCtrl', function($scope, $http, $log, typeResourceService, growl) {
    $scope.addTypeResource = function() {
      $scope.newTypeResource = {
        name: '',
        description: ''
      };
      $scope.collectionTypesResources.push($scope.newTypeResource);
    };

    $scope.updateTypeResource = function(typeResource) {
      $log.info('updateResource');
      var res = typeResourceService.updateResource(typeResource, function(data, status, headers, config) {
        $scope.getTypeResource();
      });
    };

    $scope.saveTypeResource = function(typeResource) {
      $log.info('saveTypeResource');
      var ret = typeResourceService.saveResource(typeResource, function(data, status, headers, config) {
        $scope.getTypeResource();
      });
    };

    $scope.removeTypeResource = function(typeResource) {
      $log.info('REMOVIENDO:', typeResource);
      typeResourceService.removeResource(typeResource, function(data, status, headers, config) {
        $scope.getTypeResource();
      });
    };

    $scope.getTypeResource = function() {
      typeResourceService.getAll(function(data, status, headers, config) {
        $scope.collectionTypesResources = data.data;
      });
    };

    $scope.getTypeResource();
  });