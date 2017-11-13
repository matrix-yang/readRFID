var exec = require('cordova/exec');

exports.readRFID = function(arg0, success, error) {
    exec(success, error, "readRFID", "read", [arg0]);
};
