'use strict';

describe('Service: stateResponsibleService', function () {

  // load the service's module
  beforeEach(module('appAdministratorProjectApp'));

  // instantiate service
  var stateResponsibleService;
  beforeEach(inject(function (_stateResponsibleService_) {
    stateResponsibleService = _stateResponsibleService_;
  }));

  it('should do something', function () {
    expect(!!stateResponsibleService).toBe(true);
  });

});
