'use strict';

angular.module('appAdministratorProjectApp')
  .controller('BeneficiaryCtrl', function($scope, $log, beneficiaryService, growl) {
    $scope.isEditBeneficiary = false;
    $scope.collectionBeneficiary = [];
    $scope.beneficiary = {
      name: '',
      community: '',
      president: '',
      uid: '',
      numberOfCitizens: 0
    };

    $scope.refresh = function() {
      $scope.isEditBeneficiary = false;
      $scope.isEditBeneficiary = false;
      $scope.beneficiary = {
        name: '',
        community: '',
        president: '',
        uid: '',
        numberOfCitizens: 0
      };
      $scope.getAllBeneficiaries();
    };

    $scope.getAllBeneficiaries = function() {
      beneficiaryService.getAll(function(data, status, headers, config) {
        $scope.collectionBeneficiary = data.data;
        $log.debug($scope.collectionBeneficiary);
      });
    };

    $scope.beneficiaryNew = function() {
      $log.info('New beneficiary');
      $scope.beneficiary = {
        name: '',
        community: '',
        president: '',
        uid: '',
        numberOfCitizens: 0
      };
      $scope.isNewBeneficiary = true;
    };

    $scope.beneficiaryEdit = function(item) {
      $scope.beneficiary = item;
      $scope.isEditBeneficiary = true;
    };

    $scope.beneficiarySave = function() {
      beneficiaryService.update($scope.beneficiary, function(data, status, headers, config) {
        $scope.refresh();
      });
    };

    $scope.beneficiaryCancel = function() {
      $scope.isEditBeneficiary = false;

    };

    $scope.refresh();
  });