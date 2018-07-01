package cn.x1.testwebview;

import android.util.Log;
import android.webkit.JavascriptInterface;

public class JsInterface {

    private String TAG = "JsInterface";
    private JsBridge jsBridge;

    public JsInterface(JsBridge jsBridge){
        this.jsBridge = jsBridge;
        Log.d(TAG,"JsInterface inited" );
    }

    @JavascriptInterface
    public void setValue(String value){
        Log.d(TAG,"value= " + value);
        jsBridge.setTextViewValue(value);
    }
}
