'use strict';

angular.module('appAdministratorProjectApp')
  .controller('ActivityCtrl', function($scope, $http, $log, $filter, activityService, growl, $modal, stateActivityService, typeResourceService, resourcesActivitiesService, stateResponsibleService, responsibleService, jobsService, moment) {
    $scope.format = 'yyyy-MM-dd';
    $scope.typesResources = [];
    $scope.collectionStateResponsible = [];
    $scope.collectionJobs = [];
    $scope.isNewActivity = false;
    $scope.dateOptions = {
      formatYear: 'yyyy',
      startingDay: 1
    };

    $scope.dateStartOpen = function($event) {
      $log.info('dateStartOpen');
      $event.preventDefault();
      $event.stopPropagation();

      $scope.openedStart = true;
    };

    $scope.dateFinishOpen = function($event) {
      $log.info('dateFinishOpen');
      $event.preventDefault();
      $event.stopPropagation();

      $scope.openedFinish = true;
    };

    $scope.getActivities = function() {
      $log.info('getActivities');
      activityService.getAll(function(data, status, headers, config) {
        $scope.collectionActivity = data.data;
        $log.log($scope.collectionActivity);
      });
    };

    $scope.open = function(item) {
      $log.info('open');
      $('#detailActivityModal').modal('show');
      $scope.activity = item;
      $log.info('Activity selected:', item);
    };

    $scope.addActivity = function() {
      $log.info('addActivity');
      $scope.isNewActivity = true;
      $('#detailActivityModal').modal('show');
      $scope.activity = {
        resources: []
      };
      $scope.activity.dateStart = moment().format('YYYY-MM-DD');
      $scope.activity.dateFinish = moment().format('YYYY-MM-DD');
    };

    $scope.getActivity = function() {
      $log.info('getActivity');
      $log.info('preub', $scope.activity);
      if (!angular.isUndefined($scope.activity) && !angular.isUndefined($scope.activity.id)) {
        $log.info('xxxxxxx');
        activityService.getActivity($scope.activity, function(data, status, headers, config) {
          $scope.activity = data.data;
        });
      }
    };

    $scope.saveActivity = function() {
      $log.info('saveActivity');
      $('#detailActivityModal').modal('hide');
      activityService.save($scope.activity, function(data, status, headers, config) {
        $scope.refresh();
      });
    };

    $scope.removeActivity = function(activity) {
      $log.info('removeActivity', activity);
      activityService.remove(activity, function(data, status, headers, config) {
        $scope.refresh();
      });
    };

    /* ------------------------------------------------------------------------------------------------------------- */
    /* Responsible Operations                                                                                        */
    /* ------------------------------------------------------------------------------------------------------------- */
    $scope.addResource = function() {
      $log.info('addResource');
      $scope.inserted = {
        quantity: 0,
        typeResource: $scope.collectionTypesResource
      };
      $scope.activity.resources.push($scope.inserted);
    };

    $scope.saveResource = function(data, user) {
      $log.info('saveResource');
      var insertedTemp = {
        quantity: data.quantity,
        typeResource: {
          id: data.dtype
        }
      };

      $log.info('Add resource action:', insertedTemp);
      if (!$scope.isNewActivity) {
        resourcesActivitiesService.save($scope.activity, insertedTemp, function(data, status, headers, config) {
          $scope.refresh();
        });
      }
    };

    $scope.cancelResource = function() {
      $log.info('cancelResource');
      $scope.refresh();
    };

    $scope.removeResource = function(idResource) {
      $log.info('removeResource');
      $log.info('Delete resource action:', idResource);
      resourcesActivitiesService.remove($scope.activity, idResource, function(data, status, headers, config) {
        $scope.refresh();
      });
    };

    $scope.loadTypeResources = function() {
      return $scope.typesResources.length ? null : typeResourceService.getAll(function(data, status, headers, config) {
        $scope.typesResources = data.data;
      });
    };

    $scope.showTypeResource = function(typeResourceId) {
      $log.info('showTypeResource');
      var selected = $filter('filter')($scope.typesResources, {
        id: typeResourceId
      });
      return ($scope.typesResources && selected.length) ? selected[0].name : 'Not set';
    };
    /* ------------------------------------------------------------------------------------------------------------- */

    /* ------------------------------------------------------------------------------------------------------------- */
    /* Responsible Operations                                                                                        */
    /* ------------------------------------------------------------------------------------------------------------- */

    $scope.addResponsible = function() {
      $log.info('addResponsible');
      $scope.insertedResponsible = {
        state: $scope.collectionStateResponsible
      };
      $scope.activity.responsibles.push($scope.insertedResponsible);
      $scope.isNewResponsible = true;
    };

    $scope.saveResponsible = function(data) {
      $log.info('saveReponsible');
      $log.info('Responsible item saving:', data);

      var insertedTemp = {
        id: data.id,
        address: data.address,
        email: data.email,
        lastName: data.lastName,
        name: data.name,
        phone: data.phone,
        state: {
          id: data.dstateResponsible
        },
        job: {
          id: data.djob
        }
      };

      if ($scope.isNewResponsible) {
        $log.info('Add responsible action:', insertedTemp, $scope.isNewResponsible);
        responsibleService.save($scope.activity, insertedTemp, function(data, status, headers, config) {
          $scope.refresh();
        });
      } else {
        $log.info('Update responsible action:', insertedTemp, $scope.isNewResponsible);
        responsibleService.update($scope.activity, insertedTemp, function(data, status, headers, config) {
          $scope.refresh();
        });

      }
    };

    $scope.cancelResponsible = function() {
      $log.info('cancelResponsible');
      $scope.refresh();
    };

    $scope.removeResponsible = function(idResponsible) {
      $log.info('removeResponsible');
      $log.info('Delete responsible action:', idResponsible);
      responsibleService.remove($scope.activity, idResponsible, function(data, status, headers, config) {
        $scope.refresh();
      });
    };

    $scope.loadStatesResponsible = function() {
      $log.info('loadStatesResponsible');
      $scope.collectionStateResponsible.length ? null : stateResponsibleService.getAll(function(data, status, headers, config) {
        $scope.collectionStateResponsible = data.data;
      });
    };

    $scope.loadJobs = function() {
      return $scope.collectionJobs.length ? null : jobsService.getAll(function(data, status, headers, config) {
        $scope.collectionJobs = data.data;
      });
    };

    $scope.showStatesResponsible = function(stateResponsibleId) {
      $log.info('showTypeResponsible');
      var selected = $filter('filter')($scope.collectionStateResponsible, {
        id: stateResponsibleId
      });
      return ($scope.collectionStateResponsible && selected.length) ? selected[0].state : 'Not set';
    };

    $scope.showJobs = function(jobId) {
      $log.info('showJobs');
      var selected = $filter('filter')($scope.collectionJobs, {
        id: jobId
      });
      return ($scope.collectionJobs && selected.length) ? selected[0].job : 'Not set';
    };

    $scope.checkEnabledImage = function(item) {
      if (!angular.isUndefined(item)) {
        return item.state == 'Inactivo';
      } else {
        return true;
      }
    };
    /* ------------------------------------------------------------------------------------------------------------- */

    $scope.refresh = function() {
      $log.info('refresh');
      $scope.isNewActivity = false;
      $scope.isNewResponsible = false;
      $scope.getActivities();
      $scope.getActivity();
      $scope.loadStatesResponsible();
      $scope.loadJobs();
      $scope.showStatesResponsible();
      $scope.loadTypeResources();
      stateActivityService.getAll(function(data, status, headers, config) {
        $scope.collectionStateActivity = data.data;
      });
    };

    $scope.refresh();

  });