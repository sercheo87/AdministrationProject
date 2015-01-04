'use strict';

var _ = require('lodash');
var http = require("http");

exports.getAll = function(req, result) {
  var options = {
    host: '127.0.0.1',
    port: 1313,
    path: '/ProjectAdminMng/rest/typeResource/getAll',
    method: 'GET'
  };

  http.request(options, function(res) {
    res.setEncoding('utf8');
    res.on('data', function(chunk) {
      result.json(chunk);
    });
  }).end();
};

exports.addTypeResource = function(req, result) {
  var options = {
    host: '127.0.0.1',
    port: 1313,
    path: '/ProjectAdminMng/rest/typeResource/add',
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json'
    }
  };

  var res = http.request(options, function(res) {
    res.setEncoding('utf8');
    res.on('data', function(chunk) {
      result = chunk;
    });
  });
  res.write(JSON.stringify(req.body.data));
  res.end();
};

exports.removeTypeResource = function(req, result) {
  var options = {
    host: '127.0.0.1',
    port: 1313,
    path: '/ProjectAdminMng/rest/typeResource/delete',
    method: 'DELETE',
    headers: {
      'Content-Type': 'application/json'
    }
  };

  var res = http.request(options, function(res) {
    res.setEncoding('utf8');
    res.on('data', function(chunk) {
      result = chunk;
    });
  });
  res.write(JSON.stringify(req.body.data));
  res.end();
};