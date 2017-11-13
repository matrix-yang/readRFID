package readRFID;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class echoes a string called from JavaScript.
 */
public class readRFID extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("read")) {
            String message = args.getString(0);
            callbackContext.success(message);
            return true;
        }
        callbackContext.error("Expected one non-empty string argument.");
        return false;
    }
}
