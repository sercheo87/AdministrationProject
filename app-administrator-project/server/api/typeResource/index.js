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
var typeResourceController = require('./typeResource.controller');

var router = express.Router();

router.get('/', typeResourceController.getAll);
router.put('/add', typeResourceController.addTypeResource);

module.exports = router;