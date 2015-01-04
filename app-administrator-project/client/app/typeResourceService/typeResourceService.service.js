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
  });