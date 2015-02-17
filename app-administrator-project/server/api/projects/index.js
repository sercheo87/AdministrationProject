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
var projectsController = require('./projects.controller');

var router = express.Router();

router.get('/', projectsController.getAll);
router.get('/detail/:id', projectsController.getProjectById);
router.get('/:id/activities', projectsController.getActivities);
router.get('/:id/summary', projectsController.getSummary);
router.put('/:id/beneficiary/add/:idBeneficiary', projectsController.addBeneficiary);
router.delete('/:id/beneficiary/remove/:idBeneficiary', projectsController.removeBeneficiary);
router.get('/resume', projectsController.getResume);
router.get('/beneficiary/:id', projectsController.getByBeneficiary);
router.put('/add', projectsController.add);
router.post('/update/:id', projectsController.update);

module.exports = router;