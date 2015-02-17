'use strict';

angular.module('appAdministratorProjectApp')
  .service('beneficiaryService', function($http, $log, $q, growl) {

    this.getAll = function(callback) {
      $http.get('/api/beneficiary')
        .success(callback)
        .error(callback);
    }

    this.save = function(item, callback) {
      $log.info('New object:', item);
      $http.put('/api/beneficiary/add', {
        'data': item
      })
        .success(callback)
        .error(callback);
    }

    this.update = function(item, callback) {
      $log.info('Update object:', item);
      $http.post('/api/beneficiary/update/' + item.id, {
        'data': item
      })
        .success(callback)
        .error(callback);
    }

    this.remove = function(item, callback) {
      $log.info('Remove object:', item);
      $http.delete('/api/beneficiary/delete/' + item.id, {})
        .success(callback)
        .error(callback);
    }

  });