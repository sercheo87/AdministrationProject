'use strict';

describe('Service: beneficiaryService', function () {

  // load the service's module
  beforeEach(module('appAdministratorProjectApp'));

  // instantiate service
  var beneficiaryService;
  beforeEach(inject(function (_beneficiaryService_) {
    beneficiaryService = _beneficiaryService_;
  }));

  it('should do something', function () {
    expect(!!beneficiaryService).toBe(true);
  });

});
