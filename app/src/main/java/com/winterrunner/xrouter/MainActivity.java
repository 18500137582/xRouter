package com.winterrunner.xrouter;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.winterrunner.basecommon.base.BaseActivity;
import com.winterrunner.router.bean.RouterRequestBean;
import com.winterrunner.router.bean.RouterResponseBean;
import com.winterrunner.router.interfaces.OnResponseListener;
import com.winterrunner.router.request.Router;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        TextView tv_show = (TextView) findViewById(R.id.tv_show);
    }

    public void start1(View view) {
        //同步返回结果
        RouterResponseBean responseBean = Router.getDefault().request(this, new RouterRequestBean()
                .provider("com.winterrunner.ordermanage.provider.OrderProvider")
                .action("com.winterrunner.ordermanage.action.OpenOrderPageAction")
                .from("main")
                .put("content", "我是首页跳转而来,同步开启的"));

        Toast.makeText(this, "同步返回结果为："+responseBean.getStringValue("result"), Toast.LENGTH_SHORT).show();
    }

    public void start11(View view) {

        Router.getDefault().request(this, new RouterRequestBean()
                .provider("com.winterrunner.ordermanage.provider.OrderProvider")
                .action("com.winterrunner.ordermanage.action.OpenOrderPageAction")
                .from("main")
                .put("content", "我是首页跳转而来，异步开启的，可以异步返回结果给主页"), new OnResponseListener() {
            @Override
            public void onSuccess(RouterResponseBean routerResponseBean) {
                //异步返回结果
                Toast.makeText(MainActivity.this, "主页收到异步返回的结果："+routerResponseBean.getStringValue("result"), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError() {

            }
        });
    }

    public void start2(View view) {

    }
}
