'use strict';

angular.module('appAdministratorProjectApp')
  .service('responsibleService', function($http, $log, $q, growl) {

    this.getAll = function(callback) {
      $log.info('Get all responsible collections');
      $http.get('/api/responsible')
        .success(callback)
        .error(callback);
    }

    this.save = function(data, item, callback) {
      $log.info('New object:', data);
      $http.put('/api/responsible/add/' + data.id, {
          'data': item
        })
        .success(callback)
        .error(callback);
    }

    this.update = function(data, item, callback) {
      $log.info('Update object:', item);
      $http.post('/api/responsible/update/' + data.id, {
          'data': item
        })
        .success(callback)
        .error(callback);
    }

    this.remove = function(data, item, callback) {
      $log.info('Remove object:', item);
      $http.delete('/api/responsible/delete/' + data.id + '/' + item.id, {})
        .success(callback)
        .error(callback);
    }

  });