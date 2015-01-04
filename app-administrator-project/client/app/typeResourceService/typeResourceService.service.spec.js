'use strict';

describe('Service: typeResourceService', function () {

  // load the service's module
  beforeEach(module('appAdministratorProjectApp'));

  // instantiate service
  var typeResourceService;
  beforeEach(inject(function (_typeResourceService_) {
    typeResourceService = _typeResourceService_;
  }));

  it('should do something', function () {
    expect(!!typeResourceService).toBe(true);
  });

});
