'use strict';

angular.module('appAdministratorProjectApp')
  .controller('NavbarCtrl', function($scope, $location, $translate) {
    $scope.menu = [{
      'title': 'LABEL_HOME',
      'link': '/'
    }, {
      'title': 'LABEL_PROJECT_ADMIN',
      'link': '/Administrar'
    }, {
      'title': 'LABEL_DATA_GENERAL',
      'link': '/General'
    }];

    $scope.collectionLanguaje = [{
    key: 'es_EC',
      description: 'Español'
    }, {
      key: 'en_US',
      description: 'English'
    }];

    $scope.isCollapsed = true;
    $scope.selectedLanguaje = $scope.collectionLanguaje[0];

    $scope.isActive = function(route) {
      return route === $location.path();
    };

    $scope.changeLanguage = function(itemLanguaje) {
      $translate.use(itemLanguaje.key);
      $scope.selectedLanguaje = itemLanguaje;
    };

  });