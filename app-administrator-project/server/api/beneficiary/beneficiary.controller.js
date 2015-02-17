'use strict';

var _ = require('lodash');
var http = require('http');
var module = 'beneficiaryController';
var config = require('./../../config/environment');
var formatError = require('./../../components/format');

var underscore = require('underscore');
//logger configuration
var logger = require('./../../config/logger.js')
var log = logger.logger;

exports.getAll = function(req, result) {
  var optionServer = underscore.extend(config.serverRestTemplate, {
    path: '/ProjectAdminMng/rest/beneficiary/getAll',
    method: 'GET'
  });

  formatError.executeRequest(optionServer, req, result);
};

exports.update = function(req, result) {
  var optionServer = underscore.extend(config.serverRestTemplate, {
    path: '/ProjectAdminMng/rest/beneficiary/update/' + req.params.id,
    method: 'POST'
  });

  formatError.executeRequest(optionServer, req, result);
};