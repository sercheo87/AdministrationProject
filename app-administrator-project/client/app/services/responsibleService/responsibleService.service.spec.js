'use strict';

describe('Service: responsibleService', function () {

  // load the service's module
  beforeEach(module('appAdministratorProjectApp'));

  // instantiate service
  var responsibleService;
  beforeEach(inject(function (_responsibleService_) {
    responsibleService = _responsibleService_;
  }));

  it('should do something', function () {
    expect(!!responsibleService).toBe(true);
  });

});
