'use strict';

describe('Service: typeResourceService', function() {

  // load the service's module
  beforeEach(module('appAdministratorProjectApp'));

  // instantiate service
  var typeResourceService;
  beforeEach(inject(function(_typeResourceService_) {
    typeResourceService = _typeResourceService_;
  }));

  var $httpBackend;

  beforeEach(inject(function(_$httpBackend_) {
    $httpBackend = _$httpBackend_;
  }));

  it('should do something', inject(function($http, $httpBackend) {

    var $injector = angular.injector(['appAdministratorProjectApp']);
    var myService = $injector.get('typeResourceService');
    var successCallback = jasmine.createSpy();
    myService.getAll(successCallback);

    // callback called only after flush
    expect(successCallback).not.toHaveBeenCalled();
    console.log('XXX');
    console.log(successCallback.args);

    var url = '/path/to/resource',
      successCallback = jasmine.createSpy();
    // Create expectation
    $httpBackend.expectGET(url).respond(200, 'mock data');

    // Call http service
    $http.get(url).success(successCallback);

    // callback called only after flush
    expect(successCallback).not.toHaveBeenCalled();

    // flush response
    $httpBackend.flush();

    // Verify expectations
    // Actual response is  [ 'mock data', 200, Function, { method : 'GET', url : '/path/to/resource' } ]
    expect(successCallback.mostRecentCall.args).toContain('mock data');
    expect(successCallback.mostRecentCall.args[1]).toBe(200);
    console.log(successCallback.mostRecentCall.args);

    expect(!!typeResourceService).toBe(true);

  }));

});