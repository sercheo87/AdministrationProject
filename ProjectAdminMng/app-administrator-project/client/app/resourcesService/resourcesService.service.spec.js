'use strict';

describe('Service: resourcesService', function () {

  // load the service's module
  beforeEach(module('appAdministratorProjectApp'));

  // instantiate service
  var resourcesService;
  beforeEach(inject(function (_resourcesService_) {
    resourcesService = _resourcesService_;
  }));

  it('should do something', function () {
    expect(!!resourcesService).toBe(true);
  });

});
