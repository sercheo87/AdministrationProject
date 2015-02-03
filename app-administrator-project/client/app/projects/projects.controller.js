'use strict';

angular.module('appAdministratorProjectApp')
  .controller('ProjectsCtrl', function($scope, $http, $log, $translate, $filter, growl, FileUploader, $routeParams) {

    $scope.projectid = $routeParams.projectId;

    //-------------------------------------------------------------------------------------------------------
    var uploader = $scope.uploader = new FileUploader({
      url: '/api/files/upload'
    });

    // FILTERS

    uploader.filters.push({
      name: 'customFilter',
      fn: function(item /*{File|FileLikeObject}*/ , options) {
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
    //-------------------------------------------------------------------------------------------------------

  });