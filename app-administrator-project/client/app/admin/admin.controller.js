'use strict';

angular.module('appAdministratorProjectApp')
  .controller('AdminCtrl', function($scope, $http, $log, $translate, $filter, growl, projectService, beneficiaryService) {
    $scope.collectionProjects = [];
    $scope.collectionBeneficiary = [];
    $scope.beneficiary = {};
    $scope.beneficiary.selected = undefined;

    /* ------------------------------------------------------------------------------------------------------------- */
    /* Project Operations                                                                                            */
    /* ------------------------------------------------------------------------------------------------------------- */

    $scope.loadProjects = function() {
      $log.info('loadProjects');
      projectService.getResume(function(data, status, headers, config) {
        $scope.collectionProjects = data.data;
        $log.info($scope.collectionProjects);
      });
    };

    /* ------------------------------------------------------------------------------------------------------------- */

    /* ------------------------------------------------------------------------------------------------------------- */
    /* Beneficiary Operations                                                                                        */
    /* ------------------------------------------------------------------------------------------------------------- */

    $scope.loadBeneficiary = function() {
      $log.info('loadBeneficiary');
      beneficiaryService.getAll(function(data, status, headers, config) {
        $scope.collectionBeneficiary = data.data;
        $log.debug('Beneficiary:', $scope.collectionBeneficiary);
      });
    };

    $scope.loadByFilterBeneficiary = function(item, model) {
      $log.info('loadByFilterBeneficiary');
      if (angular.isUndefined(item)) {
        $scope.loadProjects();
      } else {
        projectService.getProjectsByBeneficiary(item, function(data, status, headers, config) {
          $scope.collectionProjects = data.data;
          $log.info($scope.collectionProjects);
        });
      }
    };
    /* ------------------------------------------------------------------------------------------------------------- */

    $scope.refresh = function() {
      $scope.searchInProjects = '';
      $scope.beneficiary.selected = undefined;
      $scope.loadProjects();
      $scope.loadBeneficiary();
    };

    $scope.refresh();
  });