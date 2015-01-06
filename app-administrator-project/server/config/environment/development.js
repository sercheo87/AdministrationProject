'use strict';

// Development specific configuration
// ==================================
module.exports = {
  // MongoDB connection options
  mongo: {
    uri: 'mongodb://localhost/appadministratorproject-dev'
  },

  seedDB: true,

  serverRestTemplate: {
    host: '127.0.0.1',
    port: 1313,
    headers: {
      'Content-Type': 'application/json'
    }
  }
};