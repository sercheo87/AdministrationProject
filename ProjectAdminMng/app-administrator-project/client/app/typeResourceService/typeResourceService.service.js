'use strict';

angular.module('appAdministratorProjectApp')
  .service('typeResourceService', function($http, $log, $q, growl) {

    this.getAll = function(callback) {
      $http.get('/api/typeResource')
        .success(callback)
        .error(callback);
    }

    this.saveResource = function(item, callback) {
      $log.info('New object:', item);
      $http.put('/api/typeResource/add', {
          'data': item
        })
        .success(callback)
        .error(callback);
    }

    this.updateResource = function(item, callback) {
      $log.info('Update object:', item);
      $http.post('/api/typeResource/update/' + item.id, {
          'data': item
        })
        .success(callback)
        .error(callback);
    }

    this.removeResource = function(item, callback) {
      $log.info('Remove object:', item);
      $http.delete('/api/typeResource/delete/' + item.id, {})
        .success(callback)
        .error(callback);
    }

  });