'use strict';

var _ = require('lodash');
var http = require('http');
var module = 'activityController';
var config = require('./../../config/environment');
var formatError = require('./../../components/format');

var underscore = require('underscore');
//logger configuration
var logger = require('./../../config/logger.js')
var log = logger.logger;

exports.getAll = function(req, result) {
  var optionServer = underscore.extend(config.serverRestTemplate, {
    path: '/ProjectAdminMng/rest/activity/getAll',
    method: 'GET'
  });

  formatError.executeRequest(optionServer, req, result);
};

exports.getActivity = function(req, result) {
  var optionServer = underscore.extend(config.serverRestTemplate, {
    path: '/ProjectAdminMng/rest/activity/get/' + req.params.idActivity,
    method: 'GET'
  });

  formatError.executeRequest(optionServer, req, result);
};

exports.add = function(req, result) {

  var optionServer = underscore.extend(config.serverRestTemplate, {
    path: '/ProjectAdminMng/rest/activity/' + req.params.idProject + '/add',
    method: 'PUT'
  });

  formatError.executeRequest(optionServer, req, result);
};

exports.update = function(req, result) {
  var optionServer = underscore.extend(config.serverRestTemplate, {
    path: '/ProjectAdminMng/rest/activity/update/' + req.params.id,
    method: 'POST'
  });

  formatError.executeRequest(optionServer, req, result);
};

exports.remove = function(req, result) {
  var optionServer = underscore.extend(config.serverRestTemplate, {
    path: '/ProjectAdminMng/rest/activity/delete/' + req.params.idActivity,
    method: 'DELETE'
  });

  formatError.executeRequest(optionServer, req, result);
};