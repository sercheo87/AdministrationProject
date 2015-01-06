'use strict';

angular.module('appAdministratorProjectApp')
  .service('typeResourceService', function($http, $log, $q, growl) {

    this.getAll = function(callback) {
      $http.get('/api/typeResource')
        .success(callback)
        .error(callback);
    }

    this.saveResource = function(newResource, callback) {
      $log.info('New object:', newResource);
      $http.put('/api/typeResource/add', {
          'data': newResource
        })
        .success(callback)
        .error(callback);
    }

    this.updateResource = function(itemResource, callback) {
      $log.info('Update object:', itemResource);
      $http.post('/api/typeResource/update/' + itemResource.id, {
          'data': itemResource
        })
        .success(callback)
        .error(callback);
    }

    this.removeResource = function(itemResource, callback) {
      $log.info('Remove object:', itemResource);
      $http.delete('/api/typeResource/delete/' + itemResource.id, {})
        .success(callback)
        .error(callback);
    }

  });