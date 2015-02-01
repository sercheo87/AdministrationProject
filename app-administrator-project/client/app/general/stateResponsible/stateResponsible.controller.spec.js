'use strict';

describe('Controller: StateResponsibleCtrl', function () {

  // load the controller's module
  beforeEach(module('appAdministratorProjectApp'));

  var StateResponsibleCtrl, scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    StateResponsibleCtrl = $controller('StateResponsibleCtrl', {
      $scope: scope
    });
  }));

  it('should ...', function () {
    expect(1).toEqual(1);
  });
});
