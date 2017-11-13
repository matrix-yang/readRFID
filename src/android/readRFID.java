package readRFID;

import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.olc.uhf.UhfAdapter;
import com.olc.uhf.UhfManager;
import com.olc.uhf.tech.ISO1800_6C;
import com.olc.uhf.tech.IUhfCallback;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.List;

/**
 * This class echoes a string called from JavaScript.
 */
public class readRFID extends CordovaPlugin {

    public UhfManager mService;
    private ISO1800_6C uhf_6c;

    private CallbackContext callbackContext;


    public void init(){
        mService = UhfAdapter.getUhfManager(this.cordova.getActivity().getApplicationContext());
        boolean isopen=mService.open();

        uhf_6c=(ISO1800_6C) mService.getISO1800_6C();
    }

    IUhfCallback callback = new IUhfCallback.Stub() {
        @Override
        public void doInventory(List<String> str) throws RemoteException {
            Log.d("dqw", "count111=" + str.size());
            for (int i = 0; i < str.size(); i++) {
                String strepc = (String) str.get(i);
                Log.d("wyt", "RSSI=" + strepc.substring(0, 2));
                Log.d("wyt", "PC=" + strepc.substring(2, 6));
                Log.d("wyt", "EPC=" + strepc.substring(2, 6)+strepc.substring(6));
                String strEpc =strepc.substring(2, 6)+strepc.substring(6);
                System.out.println("inCallback--------------------->"+strEpc);

                callbackContext.success(strEpc);
                Message msg = new Message();
                msg.what = 1;
                msg.obj = strEpc;

            }

        }

        @Override
        public void doTIDAndEPC(List<String> str) throws RemoteException {
            for (Iterator it2 = str.iterator(); it2.hasNext();) {
                String strepc = (String) it2.next();
                int nlen = Integer.valueOf(strepc.substring(0, 2), 16);
            }
        }

    };

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("read")) {
            this.callbackContext=callbackContext;
            init();
            uhf_6c.inventory(callback);
            return true;
        }
        callbackContext.error("Expected one non-empty string argument.");
        return false;
    }
}
