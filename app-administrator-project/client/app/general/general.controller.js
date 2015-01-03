'use strict';

angular.module('appAdministratorProjectApp')
  .factory('fruitsFactory', function($http) {
    return {
      getFruitsAsync: function(callback) {
        $http.get('/api/resources').success(callback);
      }
    };
  })
  .controller('GeneralCtrl', function($scope, $http, $log, $translate, $filter, fruitsFactory) {

    $scope.resource = [];
    $scope.resource.name = '';
    $scope.resource.type = '';
    $scope.typesResources = [{
      id: 1,
      name: 'Humano',
    }, {
      id: 2,
      name: 'Tecnologico'
    }];

    //$scope.collectionResources = {};
    $http.get('/api/resources')
      .then(function(res) {
        $log.info('respuesta then', res.data);
        $scope.collectionResources = angular.fromJson(res.data);
      });
    // Simple POST request example (passing data) :
    $http.get('/api/resources').
    success(function(data, status, headers, config) {
      // this callback will be called asynchronously
      // when the response is available
      $log.info('data', data);
      $log.info('data', status);
      //$scope.collectionResources = JSON.stringify(data);
      //$log.info('data2', $scope.collectionResources);

      $scope.collectionResources2 = [{
        id: 1,
        name: 'Recurso 1',
        typeResource: {
          id: 1,
          name: 'Humano'
        }
      }, {
        id: 2,
        name: 'Recurso 2',
        typeResource: {
          id: 1,
          name: 'Humano'
        }
      }];
    }).
    error(function(data, status, headers, config) {
      // called asynchronously if an error occurs
      // or server returns response with an error status.
      $log.info('data', data);
      $log.info('data', status);
    });
    fruitsFactory.getFruitsAsync(function(results) {
      console.log('fruitsController async returned value', results);
      //$scope.collectionResources = results.fruits;
    });
    $scope.showTypeResource = function(typeResource) {
      var selected = [];
      if (typeResource.typeResource) {
        selected = $filter('filter')($scope.typesResources, {
          id: typeResource.typeResource.id
        });
      }
      return selected.length ? selected[0].name : 'Not set';
    };

    $scope.addResource = function() {
      $scope.inserted = {
        id: $scope.collectionResources.length + 1,
        name: '',
        typeResource: null
      };
      $scope.collectionResources.push($scope.inserted);
    };

    $scope.saveResource = function() {
      return [200, {
        status: 'ok'
      }];
    };

    $scope.selectedResourceItem = function(resourceItem) {
      $scope.resource = resourceItem;
    };

  });