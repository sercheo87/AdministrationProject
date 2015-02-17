'use strict';

angular.module('appAdministratorProjectApp')
  .service('projectService', function($http, $log, $q, growl) {

    this.getAll = function(callback) {
      $http.get('/api/projects')
        .success(callback)
        .error(callback);
    }

    this.getActivities = function(idProject, callback) {
      $http.get('/api/projects/' + idProject + '/activities')
        .success(callback)
        .error(callback);
    }

    this.getSummary = function(idProject, callback) {
      $http.get('/api/projects/' + idProject + '/summary')
        .success(callback)
        .error(callback);
    }

    this.getProjectById = function(idProject, callback) {
      $http.get('/api/projects/detail/' + idProject)
        .success(callback)
        .error(callback);
    }

    this.getResume = function(callback) {
      $http.get('/api/projects/resume')
        .success(callback)
        .error(callback);
    }

    this.getProjectsByBeneficiary = function(beneficiary, callback) {
      $http.get('/api/projects/beneficiary/' + beneficiary.id)
        .success(callback)
        .error(callback);
    }

    this.save = function(item, callback) {
      $log.info('New object:', item);
      $http.put('/api/projects/add', {
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

    this.addBeneficiary = function(project, beneficiary, callback) {
      $log.info('New object:', beneficiary);
      $http.put('/api/projects/' + project.id + '/beneficiary/add/' + beneficiary.id)
        .success(callback)
        .error(callback);
    }

    this.removeBeneficiary = function(project, beneficiary, callback) {
      $log.info('Remove object:', beneficiary);
      $http.delete('/api/projects/' + project.id + '/beneficiary/remove/' + beneficiary.id)
        .success(callback)
        .error(callback);
    }
  });