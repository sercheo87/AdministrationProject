'use strict';

describe('Controller: BeneficiaryCtrl', function () {

  // load the controller's module
  beforeEach(module('appAdministratorProjectApp'));

  var BeneficiaryCtrl, scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    BeneficiaryCtrl = $controller('BeneficiaryCtrl', {
      $scope: scope
    });
  }));

  it('should ...', function () {
    expect(1).toEqual(1);
  });
});
