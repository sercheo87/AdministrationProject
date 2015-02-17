'use strict';

angular.module('appAdministratorProjectApp')
  .controller('ProjectsCtrl', function($window, $scope, $http, $log, $translate, $filter, growl, FileUploader, $routeParams, projectService, beneficiaryService, $location) {
    $scope.projectid = $routeParams.projectId;
    $scope.project = {};
    $scope.isNewProject = false;
    $scope.collectionBeneficiary = [];
    $scope.beneficiary = {};
    $scope.beneficiary.selected = undefined;
    /* ------------------------------------------------------------------------------------------------------------- */
    /* Charts                                                                                                        */
    /* ------------------------------------------------------------------------------------------------------------- */
    $scope.dataSummaryResources = [];
    $scope.dataSummaryResponsibles = [];
    $scope.titleResources = '';
    $scope.titleResponsibles = '';
    $scope.messageSelectBeneficiary = '';
    $scope.keysColumns = {};

    $translate(['LABEL_RESOURCES', 'LABEL_RESPONSIBLIE', 'LABEL_SELECT_BENEFICIARY']).then(function(translations) {
      $scope.titleResources = translations.LABEL_RESOURCES;
      $scope.titleResponsibles = translations.LABEL_RESPONSIBLIE;
      $scope.messageSelectBeneficiary = translations.LABEL_SELECT_BENEFICIARY;
    });

    $scope.refreshCharts = function() {
      projectService.getSummary($scope.projectid, function(data, status, headers, config) {
        $log.info('Get Summary');
        $scope.collectionSummary = data.data;

        angular.forEach($scope.collectionSummary, function(item, index) {
          $scope.dataSummaryResources.push([item.id, item.totalResources]);
          $scope.dataSummaryResponsibles.push([item.id, item.totalResponsibles]);

          var temp = JSON.parse(' { "' + item.id + '":"' + item.name + '" } ');
          $scope.keysColumns = angular.extend({}, $scope.keysColumns, temp);
        });

        $scope.createCharts();
      });
    };

    var chart = c3.generate({
      bindto: '#chart',
      data: {
        x: 'x',
        //        xFormat: '%Y%m%d', // 'xFormat' can be used as custom format of 'x'
        columns: [
          ['x', '2013-01-01', '2013-01-02', '2013-01-03', '2013-01-04', '2013-01-05', '2013-01-06'],
          ['data1', 30, 200, 100, 400, 150, 250],
          ['data2', 130, 340, 200, 500, 250, 350],
          ['data3', 400, 500, 450, 700, 600, 500]
        ]
      },
      axis: {
        x: {
          type: 'timeseries',
          tick: {
            format: '%Y-%m-%d'
          }
        }
      }
    });

    $scope.createCharts = function() {
      var chartResources = c3.generate({
        bindto: '#chartSummaryResources',
        data: {
          type: 'donut',
          columns: $scope.dataSummaryResources,
          names: $scope.keysColumns,
          onclick: function(d, element) {

            $scope.$broadcast('findActivity', d.id);
          }

        },
        donut: {
          title: $scope.titleResources
        },
        size: {
          height: 400
        }
      });

      var chartResponsibles = c3.generate({
        bindto: '#chartSummaryResponsibles',
        data: {
          type: 'donut',
          columns: $scope.dataSummaryResponsibles,
          names: $scope.keysColumns,
          onclick: function(d, element) {

            $scope.$broadcast('findActivity', d.id);

          }

        },
        donut: {
          title: $scope.titleResponsibles
        },
        size: {
          height: 400
        }
      });

    };
    $scope.refreshCharts();
    /* ------------------------------------------------------------------------------------------------------------- */

    /* ------------------------------------------------------------------------------------------------------------- */
    /* Files Manager                                                                                                 */
    /* ------------------------------------------------------------------------------------------------------------- */
    $scope.reftemp = '';
    var uploader = $scope.uploader = new FileUploader({
      url: '/api/files/upload'
    });

    $scope.getFile = function() {
      $http.get('/api/files/getFile')
        .success(function(data, status, headers, config) {
          var file = new Blob([data], {
            type: 'application/vnd.openxmlformats-officedocument.wordprocessingml.document'
          });

          //trick to download store a file having its URL
          var fileURL = (window.URL || window.webkitURL || {}).createObjectURL(file);
          var a = document.createElement('a');
          a.href = fileURL;
          a.target = '_blank';
          a.download = 'yourfilename.docx';
          document.body.appendChild(a);
          a.click();

        })
        .error(function(data, status, headers, config) {
          $log.debug('DATA RETURN:', data);
        });
    };

    // FILTERS
    uploader.filters.push({
      name: 'customFilter',
      fn: function(item, options) {
        var type = '|' + item.type.slice(item.type.lastIndexOf('/') + 1) + '|';
        return '|jpg|png|jpeg|bmp|gif|doc|docx|xls|xlsx|txt|pdf|zip|rar|'.indexOf(type) !== -1;
      }
    });

    // CALLBACKS

    uploader.onWhenAddingFileFailed = function(item /*{File|FileLikeObject}*/ , filter, options) {
      console.info('onWhenAddingFileFailed', item, filter, options);
      growl.error('{{"LABEL_FILE_NOT_SUPPORTED" | translate}}' + ': ' + item.name);
    };
    uploader.onAfterAddingFile = function(fileItem) {
      console.info('onAfterAddingFile', fileItem);
    };
    uploader.onAfterAddingAll = function(addedFileItems) {
      console.info('onAfterAddingAll', addedFileItems);
    };
    uploader.onBeforeUploadItem = function(item) {
      console.info('onBeforeUploadItem', item);
    };
    uploader.onProgressItem = function(fileItem, progress) {
      console.info('onProgressItem', fileItem, progress);
    };
    uploader.onProgressAll = function(progress) {
      console.info('onProgressAll', progress);
    };
    uploader.onSuccessItem = function(fileItem, response, status, headers) {
      console.info('onSuccessItem', fileItem, response, status, headers);
    };
    uploader.onErrorItem = function(fileItem, response, status, headers) {
      console.info('onErrorItem', fileItem, response, status, headers);
    };
    uploader.onCancelItem = function(fileItem, response, status, headers) {
      console.info('onCancelItem', fileItem, response, status, headers);
    };
    uploader.onCompleteItem = function(fileItem, response, status, headers) {
      console.info('onCompleteItem', fileItem, response, status, headers);
      growl.info('{{"LABEL_FILE_UPLOADED" | translate}}' + ':' + response.file, {
        ttl: 3000
      })
    };
    uploader.onCompleteAll = function() {
      console.info('onCompleteAll');
    };

    console.info('uploader', uploader);
    /* ------------------------------------------------------------------------------------------------------------- */

    /* ------------------------------------------------------------------------------------------------------------- */
    /* Project managment                                                                                             */
    /* ------------------------------------------------------------------------------------------------------------- */
    $scope.projectSave = function() {
      $scope.$broadcast('show-errors-check-validity');

      if ($scope.formProject.$valid) {
        $log.info('Saving project');
        $log.debug('Item to saving:', $scope.project);
        $scope.isNewProject = false;
        projectService.save($scope.project, function(data, status, headers, config) {
          $location.path('/Projects');
        });
      } else {
        $scope.reset();
      }
    };

    $scope.projectCancel = function() {
      $log.info('Cancel project');
      $log.debug('Item to cancel:', $scope.project);
      $scope.isNewProject = false;
    };

    $scope.projectEdit = function() {
      $log.info('Editing project');
      $scope.isNewProject = true;
    };

    if ($scope.projectid == 0) {
      $scope.project = {};
      $scope.projectEdit();
    };

    $scope.openStart = function($event) {
      $log.info('ccc');
      $event.preventDefault();
      $event.stopPropagation();

      $scope.openedStart = true;
    };

    $scope.openFinish = function($event) {
      $event.preventDefault();
      $event.stopPropagation();

      $scope.openedFinish = true;
    };

    $scope.updateDuration = function() {
      var dateStart = moment($scope.project.dateStart);
      var dateFinish = moment($scope.project.dateFinish);

      if (dateStart.isAfter(dateFinish)) {
        $scope.project.dateStart = moment().format('DD/MM/YYYY');
        $scope.project.dateFinish = moment().format('DD/MM/YYYY');
      } else {
        $scope.project.duration = dateFinish.diff(dateStart, 'days');
      }
    }

    $scope.reset = function() {
      $scope.project = {};
    }

    $scope.getInformationProject = function(idProject) {
      projectService.getProjectById($scope.projectid, function(data, status, headers, config) {
        $log.debug('DATA RETURN:', data);
        $scope.project = data.data;
      });
    };

    if ($scope.projectid != 0) {
      $scope.getInformationProject($scope.projectid);
    } else {
      $scope.project.dateStart = moment().format('DD/MM/YYYY');
      $scope.project.dateFinish = moment().format('DD/MM/YYYY');
      $scope.updateDuration();
    }

    $scope.dateOptions = {
      formatYear: 'yyyy',
      formatMonth: 'mm',
      startingDay: 1
    };

    $scope.$on('refreshChartsExternal', function(event, args) {
      $log.debug('Updating chart remoting on demand');
      $scope.refreshCharts();
    });

    /* ------------------------------------------------------------------------------------------------------------- */

    /* ------------------------------------------------------------------------------------------------------------- */
    /* Beneficiary Operations                                                                                        */
    /* ------------------------------------------------------------------------------------------------------------- */

    $scope.loadBeneficiary = function() {
      $log.info('loadBeneficiary');
      beneficiaryService.getAll(function(data, status, headers, config) {
        $scope.collectionBeneficiary = data.data;
        $log.debug('Beneficiary:', $scope.collectionBeneficiary);
      });
    };

    $scope.loadByFilterBeneficiary = function(item, model) {
      $log.info('loadByFilterBeneficiary');
      if (angular.isUndefined(item)) {
        $scope.loadProjects();
      } else {
        projectService.getProjectsByBeneficiary(item, function(data, status, headers, config) {
          $scope.collectionProjects = data.data;
          $log.info($scope.collectionProjects);
        });
      }
    };

    $scope.loadBeneficiary();
    /* ------------------------------------------------------------------------------------------------------------- */

    /* ------------------------------------------------------------------------------------------------------------- */
    /* Beneficiary managment                                                                                             */
    /* ------------------------------------------------------------------------------------------------------------- */
    $scope.removeBeneficiary = function(beneficiary) {
      projectService.removeBeneficiary($scope.project, beneficiary, function(data, status, headers, config) {
        $scope.loadBeneficiary();
        $scope.getInformationProject($scope.projectid);
      });
    };

    $scope.addBeneficiary = function() {
      if (angular.isDefined($scope.beneficiary.selected)) {
        projectService.addBeneficiary($scope.project, $scope.beneficiary.selected, function(data, status, headers, config) {
          $scope.beneficiary = {};
          $scope.beneficiary.selected = undefined;
          $scope.loadBeneficiary();
          $scope.getInformationProject($scope.projectid);
        });
      } else {
        $log.info($scope.messageSelectBeneficiary);
        growl.warning($scope.messageSelectBeneficiary);
      }
    };

    /* ------------------------------------------------------------------------------------------------------------- */

  });