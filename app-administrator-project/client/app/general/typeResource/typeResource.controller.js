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
      if (typeof(typeResource.id) === 'undefined') {
        $log.info('Adding action', typeResource);
        var ret = typeResourceService.save(typeResource, function(data, status, headers, config) {
          $scope.getTypeResource();
        });
      } else {
        $log.info('Update action', typeResource);
        var res = typeResourceService.update(typeResource, function(data, status, headers, config) {
          $scope.getTypeResource();
        });
      }
    };

    $scope.removeTypeResource = function(typeResource) {
      $log.info('Delete action', typeResource);
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