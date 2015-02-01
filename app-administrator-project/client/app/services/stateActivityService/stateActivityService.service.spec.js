'use strict';

describe('Service: stateActivityService', function () {

  // load the service's module
  beforeEach(module('appAdministratorProjectApp'));

  // instantiate service
  var stateActivityService;
  beforeEach(inject(function (_stateActivityService_) {
    stateActivityService = _stateActivityService_;
  }));

  it('should do something', function () {
    expect(!!stateActivityService).toBe(true);
  });

});
