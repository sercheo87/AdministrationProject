'use strict';

var _ = require('lodash');
var http = require('http');
var module = 'typeResourceController';
var config = require('./../../config/environment');
var formatError = require('./../../components/format');

var underscore = require('underscore');
//logger configuration
var logger = require('./../../config/logger.js')
var log = logger.logger;

exports.getAll = function(req, result) {
  var optionServer = underscore.extend(config.serverRestTemplate, {
    path: '/ProjectAdminMng/rest/resources/getAll',
    method: 'GET'
  });

  formatError.executeRequest(optionServer, req, result);
};

exports.remove = function(req, result) {
  var optionServer = underscore.extend(config.serverRestTemplate, {
    path: '/ProjectAdminMng/rest/resources/delete/' + req.params.idActivity + '/' + req.params.idResource,
    method: 'DELETE'
  });

  formatError.executeRequest(optionServer, req, result);
};

exports.add = function(req, result) {

  var optionServer = underscore.extend(config.serverRestTemplate, {
    path: '/ProjectAdminMng/rest/resources/add/' + req.params.idActivity,
    method: 'PUT'
  });

  formatError.executeRequest(optionServer, req, result);
};