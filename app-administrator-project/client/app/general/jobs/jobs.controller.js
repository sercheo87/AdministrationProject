'use strict';

angular.module('appAdministratorProjectApp')
  .controller('JobsCtrl', function($scope, $http, $log, jobsService, growl) {
    $scope.addJob = function() {
      $scope.newJob = {
        job: '',
        description: ''
      };
      $scope.collectionJobs.push($scope.newJob);
    };

    $scope.saveJob = function(item) {
      if (typeof(item.id) === 'undefined') {
        $log.info('Adding action', item);
        var ret = jobsService.save(item, function(data, status, headers, config) {
          $scope.getJob();
        });
      } else {
        $log.info('Update action', item);
        var res = jobsService.update(item, function(data, status, headers, config) {
          $scope.getJob();
        });
      }
    };

    $scope.removeJob = function(item) {
      $log.info('Delete action', item);
      jobsService.remove(item, function(data, status, headers, config) {
        $scope.getJob();
      });
    };

    $scope.getJob = function() {
      jobsService.getAll(function(data, status, headers, config) {
        $scope.collectionJobs = data.data;
      });
    };

    $scope.getJob();
  });