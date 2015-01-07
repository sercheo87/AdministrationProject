'use strict';

angular.module('appAdministratorProjectApp')
  .controller('TypeResourceCtrl', function($scope, $http, $log, typeResourceService, growl) {
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
        var ret = typeResourceService.save(typeResource, function(data, status, headers, config) {
          $scope.getTypeResource();
        });
      } else {
        $log.info('Update new resource type');
        var res = typeResourceService.update(typeResource, function(data, status, headers, config) {
          $scope.getTypeResource();
        });
      }
    };

    $scope.removeTypeResource = function(typeResource) {
      $log.info('Delete resource type');
    typeResourceService.remove(typeResource, function(data, status, headers, config) {
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