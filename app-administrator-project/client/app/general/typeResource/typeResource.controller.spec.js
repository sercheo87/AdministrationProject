'use strict';

describe('Controller: TypeResourceCtrl', function () {

  // load the controller's module
  beforeEach(module('appAdministratorProjectApp'));

  var TypeResourceCtrl, scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    TypeResourceCtrl = $controller('TypeResourceCtrl', {
      $scope: scope
    });
  }));

  it('should ...', function () {
    expect(1).toEqual(1);
  });
});
