'use strict';

angular.module('appAdministratorProjectApp')
  .controller('NavbarCtrl', function($scope, $location, $translate, tmhDynamicLocale) {
    $scope.menu = [{
      'title': 'LABEL_HOME',
      'link': '/'
    }, {
      'title': 'LABEL_PROJECT_ADMIN',
      'link': '/Administrar'
    }];

    $scope.collectionLanguaje = [{
      key: 'es-ec',
      description: 'Español'
    }, {
      key: 'en-us',
      description: 'English'
    }];

    $scope.collectionDataGeneral = [{
      title: 'LABEL_CATALOGS',
      link: '/General'
    }];

    $scope.isCollapsed = true;
    $scope.selectedLanguaje = $scope.collectionLanguaje[0];
    tmhDynamicLocale.set('es');

    $scope.isActive = function(route) {
      return route === $location.path();
    };

    $scope.changeLanguage = function(itemLanguaje) {
      $translate.use(itemLanguaje.key);
      $scope.selectedLanguaje = itemLanguaje;
      tmhDynamicLocale.set(itemLanguaje.key);
    };

  });