'use strict';

describe('Controller: StateActivityCtrl', function () {

  // load the controller's module
  beforeEach(module('appAdministratorProjectApp'));

  var StateActivityCtrl, scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    StateActivityCtrl = $controller('StateActivityCtrl', {
      $scope: scope
    });
  }));

  it('should ...', function () {
    expect(1).toEqual(1);
  });
});
