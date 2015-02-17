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
var activityController = require('./activity.controller');

var router = express.Router();

router.get('/', activityController.getAll);
router.get('/:idActivity', activityController.getActivity);
router.put('/add/:idProject', activityController.add);
router.delete('/delete/:idActivity', activityController.remove);
module.exports = router;