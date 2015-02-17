'use strict';

var _ = require('lodash');
var http = require('http');
var module = 'projectsController';
var config = require('./../../config/environment');
var formatError = require('./../../components/format');

var underscore = require('underscore');
//logger configuration
var logger = require('./../../config/logger.js')
var log = logger.logger;

exports.getAll = function(req, result) {
  var optionServer = underscore.extend(config.serverRestTemplate, {
    path: '/ProjectAdminMng/rest/project/getAll',
    method: 'GET'
  });

  formatError.executeRequest(optionServer, req, result);
};

exports.getActivities = function(req, result) {
  var optionServer = underscore.extend(config.serverRestTemplate, {
    path: '/ProjectAdminMng/rest/project/' + req.params.id + '/getActivities',
    method: 'GET'
  });

  formatError.executeRequest(optionServer, req, result);
};

exports.getResume = function(req, result) {
  var optionServer = underscore.extend(config.serverRestTemplate, {
    path: '/ProjectAdminMng/rest/project/getResume',
    method: 'GET'
  });

  formatError.executeRequest(optionServer, req, result);
};

exports.getProjectById = function(req, result) {
  var optionServer = underscore.extend(config.serverRestTemplate, {
    path: '/ProjectAdminMng/rest/project/detail/' + req.params.id,
    method: 'GET'
  });

  formatError.executeRequest(optionServer, req, result);
};

exports.getSummary = function(req, result) {
  var optionServer = underscore.extend(config.serverRestTemplate, {
    path: '/ProjectAdminMng/rest/project/' + req.params.id + '/getSummary',
    method: 'GET'
  });

  formatError.executeRequest(optionServer, req, result);
};

exports.getByBeneficiary = function(req, result) {
  var optionServer = underscore.extend(config.serverRestTemplate, {
    path: '/ProjectAdminMng/rest/project/getByBeneficiary/' + req.params.id,
    method: 'GET'
  });

  formatError.executeRequest(optionServer, req, result);
};

exports.add = function(req, result) {
  var optionServer = underscore.extend(config.serverRestTemplate, {
    path: '/ProjectAdminMng/rest/project/add',
    method: 'PUT'
  });

  formatError.executeRequest(optionServer, req, result);
};

exports.addBeneficiary = function(req, result) {
  var optionServer = underscore.extend(config.serverRestTemplate, {
    path: '/ProjectAdminMng/rest/project/' + req.params.id + '/addBeneficiary/' + req.params.idBeneficiary,
    method: 'PUT'
  });

  formatError.executeRequest(optionServer, req, result);
};

exports.removeBeneficiary = function(req, result) {
  var optionServer = underscore.extend(config.serverRestTemplate, {
    path: '/ProjectAdminMng/rest/project/' + req.params.id + '/removeBeneficiary/' + req.params.idBeneficiary,
    method: 'DELETE'
  });

  formatError.executeRequest(optionServer, req, result);
};

exports.update = function(req, result) {
  var optionServer = underscore.extend(config.serverRestTemplate, {
    path: '/ProjectAdminMng/rest/stateResponsible/update/' + req.params.id,
    method: 'POST'
  });

  formatError.executeRequest(optionServer, req, result);
};