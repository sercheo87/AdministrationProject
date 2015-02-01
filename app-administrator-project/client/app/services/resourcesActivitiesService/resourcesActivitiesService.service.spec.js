'use strict';

describe('Service: resourcesActivitiesService', function() {

  // load the service's module
  beforeEach(module('appAdministratorProjectApp'));

  // instantiate service
  var resourcesActivitiesService;
  beforeEach(inject(function(_resourcesActivitiesService_) {
    resourcesActivitiesService = _resourcesActivitiesService_;
  }));

  it('should do something', function() {
    expect(!!resourcesActivitiesService).toBe(true);
  });

});