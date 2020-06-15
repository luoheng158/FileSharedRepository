package com.vdd.v1;

import android.os.Bundle;
import android.util.AndroidRuntimeException;
import android.util.Log;
import android.view.View;

import com.alipay.mobile.common.logging.api.rpc.RpcClient;
import com.alipay.mobile.common.rpc.RpcFactory;
import com.alipay.mobile.framework.app.ui.BaseAppCompatActivity;
import com.alipay.mobile.framework.service.common.RpcService;
import com.alipay.mobile.framework.service.common.impl.LiteMpaasRpcServiceImpl;
import com.alipay.mobile.liteprocess.rpc.RpcCall;
import com.alipay.mobile.liteprocess.rpc.RpcCallClientInvoker;
import com.mpaas.mas.adapter.api.MPLogger;
import com.vdd.v1.user.UserClient;
import com.vdd.v1.user.model.User;
import com.vdd.v1.user.request.UserAddPostReq;
import com.vdd.v1.user.request.UserQueryGetReq;

import alipay.yunpushcore.rpc.RPC;

public class MainActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_write_log).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread() {
                    @Override
                    public void run() {
                        LiteMpaasRpcServiceImpl liteMpaasRpcService = new LiteMpaasRpcServiceImpl(getApplicationContext());
                        UserClient userClient = liteMpaasRpcService.getBgRpcProxy(UserClient.class);
                        String logingRet = userClient.loginGet();
                        Log.e("tl-luoheng", "rpc-> logingRet: " + logingRet);


                        UserAddPostReq addPostReq = new UserAddPostReq();
                        User addUser = new User();
                        addUser.username = "username-" + System.currentTimeMillis();
                        addUser.password = "password-" + System.currentTimeMillis();
                        addPostReq._requestBody = addUser;
                        Log.e("tl-luoheng", "rpc-> userAddPost: " +userClient.userAddPost(addPostReq));


                        UserQueryGetReq queryGetReq = new UserQueryGetReq();
                        queryGetReq.userId = 3L;
                        User qUser = userClient.userQueryGet(queryGetReq);
                        Log.e("tl-luoheng", "rpc-> userQueryGet: " + qUser);



                    }
                }.start();

            }
        });

        findViewById(R.id.btn_crash).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                throw new AndroidRuntimeException("Crash!!!!");
            }
        });
    }

}
