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
var beneficiaryController = require('./beneficiary.controller');

var router = express.Router();

router.get('/', beneficiaryController.getAll);
router.post('/update/:id', beneficiaryController.update);

module.exports = router;