'use strict';

angular.module('appAdministratorProjectApp')
  .service('resourcesService', function($http, $log) {

    this.getAllResources = function(callback) {
      $http.get('/api/resources').success(callback);
    }

    this.saveResource = function(newResource) {
      $log.info('New object:', newResource);
    }
  });