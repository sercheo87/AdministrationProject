'use strict';

angular.module('appAdministratorProjectApp')
  .service('stateActivityService', function($http, $log, $q, growl) {

    this.getAll = function(callback) {
      $http.get('/api/stateActivity')
        .success(callback)
        .error(callback);
    }

    this.save = function(item, callback) {
      $log.info('New object:', item);
      $http.put('/api/stateActivity/add', {
          'data': item
        })
        .success(callback)
        .error(callback);
    }

    this.update = function(item, callback) {
      $log.info('Update object:', item);
      $http.post('/api/stateActivity/update/' + item.id, {
          'data': item
        })
        .success(callback)
        .error(callback);
    }

    this.remove = function(item, callback) {
      $log.info('Remove object:', item);
      $http.delete('/api/stateActivity/delete/' + item.id, {})
        .success(callback)
        .error(callback);
    }

  });