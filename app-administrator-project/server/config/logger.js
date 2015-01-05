'use strict';

// Logger
var log4js = require('log4js');
var logger = log4js.getLogger('appProjectAdmin');

log4js.clearAppenders()
log4js.loadAppender('console');
//log4js.loadAppender('file');
log4js.addAppender(log4js.appenders.console());
//log4js.addAppender(log4js.appenders.file('logs/cheese.log'), 'cheese');

logger.setLevel('DEBUG');

var getLogger = function() {
  return logger;
};

exports.logger = getLogger('appProjectAdmin');