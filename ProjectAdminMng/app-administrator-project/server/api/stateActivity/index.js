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
var stateActivityController = require('./stateActivity.controller');

var router = express.Router();

router.get('/', stateActivityController.getAll);
router.put('/add', stateActivityController.add);
router.post('/update/:id', stateActivityController.update);
router.delete('/delete/:id', stateActivityController.remove);

module.exports = router;