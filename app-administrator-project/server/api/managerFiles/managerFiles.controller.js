'use strict';

var _ = require('lodash');
var http = require('http');
var module = 'managerFiles';
var config = require('./../../config/environment');
var formatError = require('./../../components/format');
var fileSystem = require('fs');

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

exports.getFile = function(req, res) {
  var filePath = path.join(__dirname, 'Pacificardbox.docx');
  log.info(filePath);
  var file = fileSystem.readFileSync(filePath, 'binary');

  res.setHeader('Content-Length', file.length);
  //s.setHeader('Content-Transfer-Encoding', 'binary');
  res.setHeader('Content-Disposition', 'attachment; filename=' + 'Pacificardbox.docx');
  res.contentType('application/vnd.openxmlformats-officedocument.wordprocessingml.document');

  res.download(filePath,'file_rename.docx', function(err){
    if (err) {
      log.info('Get file from server');
    } else {
      log.info('Get file from server');
    }
  });
};