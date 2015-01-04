'use strict';

var _ = require('lodash');
var http = require("http");

exports.getAll = function(req, result) {
  var http = require('http');
  var options = {
    host: '127.0.0.1',
    port: 1313,
    path: '/ProjectAdminMng/rest/typeResource/getAll',
    method: 'GET'
  };

  http.request(options, function(res) {
    console.log('STATUS: ' + res.statusCode);
    console.log('HEADERS: ' + JSON.stringify(res.headers));
    res.setEncoding('utf8');
    res.on('data', function(chunk) {
      console.log('BODY: ' + chunk);
      result.json(chunk);
    });
  }).end();
};

exports.addTypeResource = function(req, result) {
  var http = require('http');
  console.log('XXXXXXXXXX', req.body.data);
  var options = {
    host: '127.0.0.1',
    port: 1313,
    path: '/ProjectAdminMng/rest/typeResource/add',
    method: 'PUT',
    headers: {
      "Content-Type": "application/json"
    }
  };

  var x = http.request(options, function(res) {
    console.log('STATUS: ' + res.statusCode);
    console.log('HEADERS: ' + JSON.stringify(res.headers));
    res.setEncoding('utf8');
    res.on('data', function(chunk) {
      console.log('BODY: ' + chunk);
      result = chunk;
    });
  });
  x.write(JSON.stringify(req.body.data));
  x.end();
};