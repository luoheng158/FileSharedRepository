package com.vdd.v1;

import android.app.Application;

import com.alipay.mobile.framework.LauncherApplicationAgent;
import com.alipay.mobile.framework.service.annotation.OperationType;
import com.alipay.mobile.framework.service.common.RpcService;

public class MockLauncherApplicationAgent  extends LauncherApplicationAgent {

    public MockLauncherApplicationAgent(Application context, Object bundleContext) {
        super(context, bundleContext);
    }

    @Override
    public void preInit() {
        super.preInit();
    }

    @Override
    public void postInit() {
        super.postInit();
        RpcService rpcService = getMicroApplicationContext().findServiceByInterface(RpcService.class.getName());
        rpcService.addRpcInterceptor(OperationType.class, new CommonInterceptor());

    }
}
