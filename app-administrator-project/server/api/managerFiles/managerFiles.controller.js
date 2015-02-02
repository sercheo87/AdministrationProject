'use strict';

var _ = require('lodash');
var http = require('http');
var module = 'managerFiles';
var config = require('./../../config/environment');
var formatError = require('./../../components/format');

var underscore = require('underscore');
//logger configuration
var logger = require('./../../config/logger.js')
var log = logger.logger;
var path = require('path');

exports.upload = function(req, res) {

  log.info('File:', req.files.file.originalFilename);
  log.info('Path:', req.files.file.path);
  log.info('Type:', req.files.file.type);

  var response = {
    file: req.files.file.originalFilename,
    name: path.relative(__dirname + '/../../../client/upload', req.files.file.path)
  };

  log.debug('RESPONSE', response);

  res.writeHead(200, {
    'Content-Length': response.length,
    'Content-Type': 'application/json'
  });
  res.write(JSON.stringify(response));
  res.end();
};