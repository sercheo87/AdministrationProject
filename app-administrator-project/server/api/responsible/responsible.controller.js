'use strict';

var _ = require('lodash');
var http = require('http');
var module = 'responsibleController';
var config = require('./../../config/environment');
var formatError = require('./../../components/format');

var underscore = require('underscore');
//logger configuration
var logger = require('./../../config/logger.js')
var log = logger.logger;

exports.getAll = function(req, result) {
  var optionServer = underscore.extend(config.serverRestTemplate, {
    path: '/ProjectAdminMng/rest/responsible/getAll',
    method: 'GET'
  });

  formatError.executeRequest(optionServer, req, result);
};

exports.add = function(req, result) {

  var optionServer = underscore.extend(config.serverRestTemplate, {
    path: '/ProjectAdminMng/rest/responsible/add/' + req.params.idActivity,
    method: 'PUT'
  });

  formatError.executeRequest(optionServer, req, result);
};

exports.update = function(req, result) {
  var optionServer = underscore.extend(config.serverRestTemplate, {
    path: '/ProjectAdminMng/rest/responsible/update/' + req.params.idActivity,
    method: 'POST'
  });

  formatError.executeRequest(optionServer, req, result);
};

exports.remove = function(req, result) {
  var optionServer = underscore.extend(config.serverRestTemplate, {
    path: '/ProjectAdminMng/rest/responsible/delete/' + req.params.idActivity + '/' + req.params.id,
    method: 'DELETE'
  });

  formatError.executeRequest(optionServer, req, result);
};