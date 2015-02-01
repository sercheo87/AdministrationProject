'use strict';

angular.module('appAdministratorProjectApp')
  .service('stateResponsibleService', function($http, $log, $q, growl) {

    this.getAll = function(callback) {
      $http.get('/api/stateResponsible')
        .success(callback)
        .error(callback);
    }

    this.save = function(item, callback) {
      $log.info('New object:', item);
      $http.put('/api/stateResponsible/add', {
          'data': item
        })
        .success(callback)
        .error(callback);
    }

    this.update = function(item, callback) {
      $log.info('Update object:', item);
      $http.post('/api/stateResponsible/update/' + item.id, {
          'data': item
        })
        .success(callback)
        .error(callback);
    }

    this.remove = function(item, callback) {
      $log.info('Remove object:', item);
      $http.delete('/api/stateResponsible/delete/' + item.id, {})
        .success(callback)
        .error(callback);
    }

  });