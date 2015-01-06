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

    $scope.saveTypeResource = function(typeResource) {
      $log.info('typeResource:', typeResource);
      if (typeof(typeResource.id) === 'undefined') {
        $log.info('Adding new resource type');
        var ret = typeResourceService.saveResource(typeResource, function(data, status, headers, config) {
          $scope.getTypeResource();
        });
      } else {
        $log.info('Update new resource type');
        var res = typeResourceService.updateResource(typeResource, function(data, status, headers, config) {
          $scope.getTypeResource();
        });
      }
    };

    $scope.removeTypeResource = function(typeResource) {
      $log.info('Delete resource type');
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