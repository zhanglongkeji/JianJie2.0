package com.zlkj.jianjie.http.builder;


import com.zlkj.jianjie.http.OkHttpUtils;
import com.zlkj.jianjie.http.request.OtherRequest;
import com.zlkj.jianjie.http.request.RequestCall;

/**
 * Created by zhy on 16/3/2.
 */
public class HeadBuilder extends GetBuilder
{
    @Override
    public RequestCall build()
    {
        return new OtherRequest(null, null, OkHttpUtils.METHOD.HEAD, url, tag, params, headers,id).build();
    }
}
