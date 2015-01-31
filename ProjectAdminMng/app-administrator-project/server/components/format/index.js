/**
 * Format responses
 */

'use strict';

var underscore = require('underscore');
var http = require('http');

//logger configuration
var logger = require('./../../config/logger.js')
var log = logger.logger;

module.exports = {
  executeRequest: function(optionServer, request, result) {
    log.debug('PARAMS:', request.params);
    log.debug('BODY:', JSON.stringify(request.body));
    log.debug('SERVER CONFIG:', optionServer);

    var response = http.request(optionServer, function(res) {
      res.setEncoding('utf8');
      res.on('data', function(data) {
        log.debug('ORIGINAL_RESPONSE:', data);
        result.status(res.statusCode).json(JSON.parse(data));
      }).on('end', function() {});
    });
    if (optionServer.method === 'DELETE') {
      //response.write(JSON.stringify(request.params));
    } else {
      if (JSON.stringify(request.body) !== '{}') {
        response.write(JSON.stringify(request.body.data));
      }
    }
    response.end();
  }
};