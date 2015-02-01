'use strict';

angular.module('appAdministratorProjectApp')
  .service('jobsService', function($http, $log, $q, growl) {

    this.getAll = function(callback) {
      $http.get('/api/jobs')
        .success(callback)
        .error(callback);
    }

    this.save = function(item, callback) {
      $log.info('New object:', item);
      $http.put('/api/jobs/add', {
          'data': item
        })
        .success(callback)
        .error(callback);
    }

    this.update = function(item, callback) {
      $log.info('Update object:', item);
      $http.post('/api/jobs/update', {
          'data': item
        })
        .success(callback)
        .error(callback);
    }

    this.remove = function(item, callback) {
      $log.info('Remove object:', item);
      $http.delete('/api/jobs/delete/' + item.id, {})
        .success(callback)
        .error(callback);
    }

  });