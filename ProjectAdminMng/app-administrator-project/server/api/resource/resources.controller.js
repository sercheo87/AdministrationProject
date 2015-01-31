'use strict';

var _ = require('lodash');
var http = require("http");

// Get list of things
exports.getAll = function(req, result) {
  var http = require('http');
  var options = {
    host: '127.0.0.1',
    port: 1313,
    path: '/ProjectAdminMng/rest/resources/getAll',
    method: 'GET'
  };

  http.request(options, function(res) {
    console.log('STATUS: ' + res.statusCode);
    console.log('HEADERS: ' + JSON.stringify(res.headers));
    res.setEncoding('utf8');
    res.on('data', function(chunk) {
      console.log('BODY: ' + chunk);
      result.json(chunk);

      result.json = [{
        "id": 1,
        "quantity": 5,
        "typeResource": {
          "description": "HUMANO",
          "id": 1,
          "name": "RES_HUM"
        }
      }, {
        "id": 2,
        "quantity": 2,
        "typeResource": {
          "description": "HUMANO",
          "id": 1,
          "name": "RES_HUM"
        }
      }];
    });
  }).end();
};