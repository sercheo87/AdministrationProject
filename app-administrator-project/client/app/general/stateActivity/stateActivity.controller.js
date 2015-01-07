'use strict';

angular.module('appAdministratorProjectApp')
  .controller('StateActivityCtrl', function($scope, $http, $log, stateActivityService, growl) {
    $scope.addStateActivity = function() {
      $scope.newStateActivity = {
        state: ''
      };
      $scope.collectionStateActivity.push($scope.newStateActivity);
    };

    $scope.saveStateActivity = function(stateActivity) {
      $log.info('stateActivity:', stateActivity);
      if (typeof(stateActivity.id) === 'undefined') {
        $log.info('Adding action');
        var ret = stateActivityService.save(stateActivity, function(data, status, headers, config) {
          $scope.getStateActivity();
        });
      } else {
        $log.info('Update action');
        var res = stateActivityService.update(stateActivity, function(data, status, headers, config) {
          $scope.getStateActivity();
        });
      }
    };

    $scope.removeStateActivity = function(stateActivity) {
      $log.info('Delete action');
      stateActivityService.remove(stateActivity, function(data, status, headers, config) {
        $scope.getStateActivity();
      });
    };

    $scope.getStateActivity = function() {
      stateActivityService.getAll(function(data, status, headers, config) {
        $scope.collectionStateActivity = data.data;
      });
    };

    $scope.getStateActivity();
  });