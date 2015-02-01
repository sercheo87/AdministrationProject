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
var stateResponsibleController = require('./stateResponsible.controller');

var router = express.Router();

router.get('/', stateResponsibleController.getAll);
router.put('/add', stateResponsibleController.add);
router.post('/update/:id', stateResponsibleController.update);
router.delete('/delete/:id', stateResponsibleController.remove);

module.exports = router;