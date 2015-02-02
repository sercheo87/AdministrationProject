'use strict';

/**
 * Using Rails-like standard naming convention for endpoints.
 * GET     /things              ->  index
 * POST    /things              ->  create
 * GET     /things/:id          ->  show
 * PUT     /things/:id          ->  update
 * DELETE  /things/:id          ->  destroy
 */

var express = require('express');
var managerFiles = require('./managerFiles.controller');

var router = express.Router();

var multipart = require('connect-multiparty');
var multipartMiddleware = multipart({
  autoFields: true,
  autoFiles: true,
  uploadDir: __dirname + '/../../../client/upload'
});

router.post('/upload', multipartMiddleware, managerFiles.upload);

module.exports = router;