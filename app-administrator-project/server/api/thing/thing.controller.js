/**
 * Using Rails-like standard naming convention for endpoints.
 * GET     /things              ->  index
 * POST    /things              ->  create
 * GET     /things/:id          ->  show
 * PUT     /things/:id          ->  update
 * DELETE  /things/:id          ->  destroy
 */

'use strict';

var _ = require('lodash');
var express = require('express');
var fs = require('fs');

// Get list of things
exports.index = function(req, res) {
  res.json([{
    name: 'Development Tools',
    info: 'Integration with popular tools such as Bower, Grunt, Karma, Mocha, JSHint, Node Inspector, Livereload, Protractor, Jade, Stylus, Sass, CoffeeScript, and Less.'
  }, {
    name: 'Server and Client integration',
    info: 'Built with a powerful and fun stack: MongoDB, Express, AngularJS, and Node.'
  }, {
    name: 'Smart Build System',
    info: 'Build system ignores `spec` files, allowing you to keep tests alongside code. Automatic injection of scripts and styles into your index.html'
  }, {
    name: 'Modular Structure',
    info: 'Best practice client and server structures allow for more code reusability and maximum scalability'
  }, {
    name: 'Optimized Build',
    info: 'Build process packs up your templates as a single JavaScript payload, minifies your scripts/css/images, and rewrites asset names for caching.'
  }, {
    name: 'Deployment Ready',
    info: 'Easily deploy your app to Heroku or Openshift with the heroku and openshift subgenerators'
  }]);
};

exports.upload = function(req, res) {
  res.setHeader('Content-Type', 'text/html');
  console.log('>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>');
  console.log(req.FILES);
  console.log(req.uploader)
  var pictureUrl = '/path/to/default/pictures';
  var fileUploadMessage = '';

  // process file
  if (!req.files.file || req.files.file.size == 0) {
    fileUploadMessage = 'No file uploaded at ' + new Date().toString();
    res.send(fileUploadMessage);
  } else {
    var file = req.files.file;

    fs.unlink(file.path, function(err) {
      if (err)
        throw err;
      else {
        fileUploadMessage = '<b>"' + file.name + '"<b> uploaded to the server at ' + new Date().toString();
        pictureUrl = '/picture-uploads/' + file.name;

        var responseObj = {
          fullname: req.param('fullname'),
          gender: req.param('gender'),
          color: req.param('color'),
          pictureUrl: pictureUrl
        }
        res.send(JSON.stringify(responseObj));
      }
    });
  }
};