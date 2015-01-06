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
    path: '/ProjectAdminMng/rest/typeResource/getAll',
    method: 'GET'
  });

  formatError.executeRequest(optionServer, req, result);
};

exports.addTypeResource = function(req, result) {

  var optionServer = underscore.extend(config.serverRestTemplate, {
    path: '/ProjectAdminMng/rest/typeResource/add',
    method: 'PUT'
  });

  formatError.executeRequest(optionServer, req, result);
};

exports.updateTypeResource = function(req, result) {
  var optionServer = underscore.extend(config.serverRestTemplate, {
    path: '/ProjectAdminMng/rest/typeResource/update',
    method: 'POST'
  });

  formatError.executeRequest(optionServer, req, result);
};

exports.removeTypeResource = function(req, result) {
  var optionServer = underscore.extend(config.serverRestTemplate, {
    path: '/ProjectAdminMng/rest/typeResource/delete',
    method: 'DELETE'
  });

  formatError.executeRequest(optionServer, req, result);
};