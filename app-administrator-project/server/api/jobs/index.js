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
var jobsController = require('./jobs.controller');

var router = express.Router();

router.get('/', jobsController.getAll);
router.put('/add', jobsController.add);
router.post('/update', jobsController.update);
router.delete('/delete/:id', jobsController.remove);

module.exports = router;