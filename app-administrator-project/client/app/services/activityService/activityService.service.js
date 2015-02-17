'use strict';

angular.module('appAdministratorProjectApp')
  .service('activityService', function($http, $log, $q, growl) {

    this.getAll = function(callback) {
      $http.get('/api/activity')
        .success(callback)
        .error(callback);
    }

    this.getActivity = function(item, callback) {
      $http.get('/api/activity/' + item.id)
        .success(callback)
        .error(callback);
    }

    this.save = function(item, idProject, callback) {
      $log.info('New object:', item);
      $http.put('/api/activity/add/' + idProject, {
          'data': item
        })
        .success(callback)
        .error(callback);
    }

    this.update = function(item, callback) {
      $log.info('Update object:', item);
      $http.post('/api/activity/update/' + item.id, {
          'data': item
        })
        .success(callback)
        .error(callback);
    }

    this.remove = function(item, callback) {
      $log.info('Remove object:', item);
      $http.delete('/api/activity/delete/' + item.id, {})
        .success(callback)
        .error(callback);
    }

  });