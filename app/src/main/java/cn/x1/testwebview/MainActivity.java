package cn.x1.testwebview;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements JsBridge {

    private WebView mWebView;
    private TextView mTvResult;
    private Handler handler;
    private EditText mEditText;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWedgets(savedInstanceState);
        Log.d("activity_main","onCreate");
    }

    private void initWedgets(Bundle saveInstanceState){
        mWebView = findViewById(R.id.webview);
        mTvResult = findViewById(R.id.textview);
        mEditText = findViewById(R.id.edittext);
        mButton = findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String str = mEditText.getText().toString();
                String jsStr = "javascript:if(window.remote){remote('"+str+"')}";
                Log.d("onClick",jsStr);
                mWebView.loadUrl(jsStr);
            }
        });
        handler = new Handler();
        //允许webview加载js
        mWebView.getSettings().setJavaScriptEnabled(true);
        Log.d("setJavaScriptEnabled",String.valueOf(mWebView.getSettings().getJavaScriptEnabled()));
        //给webview添加js接口
        mWebView.addJavascriptInterface(new JsInterface(this),"mylauncher ");
        mWebView.loadUrl("file:///android_asset/index.html");
    }

    @Override
    public void setTextViewValue(final String str){
        handler.post(new Runnable() {
            @Override
            public void run() {
                mTvResult.setText(str);
            }
        });
    }
}
