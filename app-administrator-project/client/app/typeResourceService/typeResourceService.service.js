'use strict';

angular.module('appAdministratorProjectApp')
  .service('typeResourceService', function($http, $log) {

    this.getAll = function(callback) {
      $http.get('/api/typeResource').success(callback);
    }

    this.saveResource = function(newResource, callback) {
      $log.info('New object:', newResource);
      $http.put('/api/typeResource/add', {
        'data': newResource
      }).success(callback);
    }

    this.removeResource = function(itemResource, callback) {
      $log.info('Remove object:', itemResource);
      $http.put('/api/typeResource/delete', {
        'data': itemResource
      }).success(callback);
    }
  });