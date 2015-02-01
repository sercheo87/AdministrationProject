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
var responsibleController = require('./responsible.controller');

var router = express.Router();

router.get('/', responsibleController.getAll);
router.put('/add/:idActivity', responsibleController.add);
router.post('/update/:idActivity', responsibleController.update);
router.delete('/delete/:idActivity/:id', responsibleController.remove);

module.exports = router;