/**
 * Main application routes
 */

'use strict';

var errors = require('./components/errors');

module.exports = function(app) {

  // Insert routes below
  app.use('/api/things', require('./api/thing'));
  app.use('/api/resources', require('./api/resource'));
  app.use('/api/typeResource', require('./api/typeResource'));
  app.use('/api/stateActivity', require('./api/stateActivity'));
  app.use('/api/activity', require('./api/activity'));
  app.use('/api/stateResponsible', require('./api/stateResponsible'));
  app.use('/api/responsible', require('./api/responsible'));
  app.use('/api/jobs', require('./api/jobs'));
  app.use('/api/files', require('./api/managerFiles'));
  app.use('/api/projects', require('./api/projects'));
  app.use('/api/beneficiary', require('./api/beneficiary'));

  // All undefined asset or api routes should return a 404
  app.route('/:url(api|auth|components|app|bower_components|assets)/*')
    .get(errors[404]);

  // All other routes should redirect to the index.html
  app.route('/*')
    .get(function(req, res) {
      res.sendfile(app.get('appPath') + '/index.html');
    });
};