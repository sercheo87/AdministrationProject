'use strict';

angular.module('appAdministratorProjectApp')
  .controller('ActivityCtrl', function($rootScope, $routeParams, $scope, $http, $log, $filter, activityService, growl, $modal, stateActivityService, typeResourceService, resourcesActivitiesService, stateResponsibleService, responsibleService, jobsService, moment, projectService) {
    $scope.format = 'yyyy-MM-dd';
    $scope.typesResources = [];
    $scope.collectionStateResponsible = [];
    $scope.collectionJobs = [];
    $scope.isNewActivity = false;
    $scope.dateOptions = {
      formatYear: 'yyyy',
      startingDay: 1
    };

    $scope.projectid = $routeParams.projectId;

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
      projectService.getActivities($scope.projectid, function(data, status, headers, config) {
        $scope.collectionActivity = data.data;
        $log.debug($scope.collectionActivity);
      });
    };

    $scope.$on('findActivity', function(event, args) {
      args = parseInt(args);
      $scope.getActivities();

      $log.debug('objeto a buscar', args, $scope.collectionActivity);
      var single_object = $filter('filter')($scope.collectionActivity, function(d) {
        return d.id === args;
      })[0];
      $scope.open(single_object);
    });

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
        resources: [],
        responsibles: []
      };
      $scope.activity.dateStart = moment().format('YYYY-MM-DD');
      $scope.activity.dateFinish = moment().format('YYYY-MM-DD');
    };

    $scope.getActivity = function() {
      $log.info('getActivity');
      if (!angular.isUndefined($scope.activity) && !angular.isUndefined($scope.activity.id)) {
        activityService.getActivity($scope.activity, function(data, status, headers, config) {
          $scope.activity = data.data;
        });
      }
    };

    $scope.saveActivity = function() {
      $log.info('saveActivity');
      $('#detailActivityModal').modal('hide');
      $log.debug('Activity to saving:', $scope.activity);

      activityService.save($scope.activity, $scope.projectid, function(data, status, headers, config) {
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
        typeResource: {}
      };
      $scope.activity.resources.push($scope.inserted);
      $log.debug('Activity modify:', $scope.activity);
    };

    $scope.saveResource = function(data, user) {
      $log.info('saveResource');
      var insertedTemp = {
        quantity: data.quantity,
        typeResource: {
          id: data.dtype
        }
      };

      $log.debug('Add resource action:', insertedTemp);
      if (!$scope.isNewActivity) {
        resourcesActivitiesService.save($scope.activity, insertedTemp, function(data, status, headers, config) {
          $scope.refresh();
          $rootScope.$broadcast('refreshChartsExternal', '');
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
        address: '',
        email: '',
        lastName: '',
        name: '',
        phone: '',
        state: {},
        job: {}
      };
      $log.debug('Responsible inserted:', $scope.insertedResponsible);
      $scope.activity.responsibles.push($scope.insertedResponsible);

      $log.debug('Activity modify:', $scope.activity);
      $scope.isNewResponsible = true;
    };

    $scope.saveResponsible = function(data) {
      $log.info('saveReponsible');

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

      $log.debug('Responsible item saving:', insertedTemp);
      if (!$scope.isNewActivity) {
        if ($scope.isNewResponsible) {
          $log.debug('Add responsible action:', insertedTemp, $scope.isNewResponsible);
          responsibleService.save($scope.activity, insertedTemp, function(data, status, headers, config) {
            $rootScope.$broadcast('refreshChartsExternal', '');
            $scope.refresh();
          });
        } else {
          $log.debug('Update responsible action:', insertedTemp, $scope.isNewResponsible);
          responsibleService.update($scope.activity, insertedTemp, function(data, status, headers, config) {
            $rootScope.$broadcast('refreshChartsExternal', '');
            $scope.refresh();
          });

        }
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
      return $scope.collectionStateResponsible.length ? null : stateResponsibleService.getAll(function(data, status, headers, config) {
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