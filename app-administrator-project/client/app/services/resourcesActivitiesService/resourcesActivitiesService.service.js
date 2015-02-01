'use strict';

angular.module('appAdministratorProjectApp')
  .service('resourcesActivitiesService', function($http, $log, $q, growl) {

    this.getAll = function(callback) {
      $http.get('/api/resources')
        .success(callback)
        .error(callback);
    }

    this.save = function(activity, item, callback) {
      $log.info('New object:', item);
      $http.put('/api/resources/add/' + activity.id, {
          'data': item
        })
        .success(callback)
        .error(callback);
    }

    this.remove = function(data, item, callback) {
      $log.info('Remove object:', item, data);
      $http.delete('/api/resources/delete/' + data.id + '/' + item.id, {
          'data': data
        })
        .success(callback)
        .error(callback);
    }
  });