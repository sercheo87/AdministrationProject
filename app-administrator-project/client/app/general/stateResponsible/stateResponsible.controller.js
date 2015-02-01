'use strict';

angular.module('appAdministratorProjectApp')
  .controller('StateResponsibleCtrl', function($scope, $http, $log, stateResponsibleService, growl) {
    $scope.addStateResponsible = function() {
      $scope.newStateResponsible = {
        state: ''
      };
      $scope.collectionStateResponsible.push($scope.newStateResponsible);
    };

    $scope.saveStateResponsible = function(item) {
      if (typeof(item.id) === 'undefined') {
        $log.info('Adding action', item);
        var ret = stateResponsibleService.save(item, function(data, status, headers, config) {
          $scope.getStateResponsible();
        });
      } else {
        $log.info('Update action', item);
        var res = stateResponsibleService.update(item, function(data, status, headers, config) {
          $scope.getStateResponsible();
        });
      }
    };

    $scope.removeStateResponsible = function(item) {
      $log.info('Delete action', item);
      stateResponsibleService.remove(item, function(data, status, headers, config) {
        $scope.getStateResponsible();
      });
    };

    $scope.getStateResponsible = function() {
      stateResponsibleService.getAll(function(data, status, headers, config) {
        $scope.collectionStateResponsible = data.data;
      });
    };

    $scope.getStateResponsible();
  });