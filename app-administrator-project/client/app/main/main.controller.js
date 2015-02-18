'use strict';

angular.module('appAdministratorProjectApp')
  .controller('MainCtrl', function($scope, $http, beneficiaryService, $log) {
    $scope.awesomeThings = [];
    $scope.summaryBeneficiaries = [];

    $http.get('/api/things').success(function(awesomeThings) {
      $scope.awesomeThings = awesomeThings;
    });

    $scope.refresh = function() {
      $log.info('Get summary beneficiaries');
      beneficiaryService.getSummary(function(data, status, headers, config) {
        $log.info(data);
        $scope.summaryBeneficiaries = data.data;
      });
    };

    $scope.refresh();
  });