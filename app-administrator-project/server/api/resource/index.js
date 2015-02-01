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
var resourcesController = require('./resources.controller');

var router = express.Router();

router.get('/', resourcesController.getAll);
router.put('/add/:idActivity', resourcesController.add);
router.delete('/delete/:idActivity/:idResource', resourcesController.remove);
module.exports = router;