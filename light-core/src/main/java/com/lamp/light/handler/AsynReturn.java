/*
 *Copyright (c) [Year] [name of copyright holder]
 *[Software Name] is licensed under Mulan PSL v2.
 *You can use this software according to the terms and conditions of the Mulan PSL v2.
 *You may obtain a copy of Mulan PSL v2 at:
 *         http://license.coscl.org.cn/MulanPSL2
 *THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND,
 *EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT,
 *MERCHANTABILITY OR FIT FOR A PARTICULAR PURPOSE.
 *See the Mulan PSL v2 for more details.
 */
package com.lamp.light.handler;

import com.lamp.light.Call;
import com.lamp.light.handler.HandleProxy.HandleMethod;
import com.lamp.light.response.ReturnMode;
import com.lamp.light.serialize.Serialize;

import io.netty.channel.Channel;
import io.netty.handler.codec.http.HttpRequest;

public class AsynReturn {

    private Channel channel;

    private Object[] args;

    private Object object;

    private Serialize serialize;

    private HttpRequest fullHttpRequest;

    private Integer requestTimes = 30000;

    private HandleMethod handleMethod;

    private Call<Object> call;

    private ReturnMode returnMode;

    public AsynReturn() {

    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public Serialize getSerialize() {
        return serialize;
    }

    public void setSerialize(Serialize serialize) {
        this.serialize = serialize;
    }

    public HttpRequest getFullHttpRequest() {
        return fullHttpRequest;
    }

    public void setFullHttpRequest(HttpRequest fullHttpRequest) {
        this.fullHttpRequest = fullHttpRequest;
    }

    public Integer getRequestTimes() {
        return requestTimes;
    }

    public void setRequestTimes(Integer requestTimes) {
        this.requestTimes = requestTimes;
    }

    public HandleMethod getHandleMethod() {
        return handleMethod;
    }

    public void setHandleMethod(HandleMethod handleMethod) {
        this.handleMethod = handleMethod;
    }

    public Call<Object> getCall() {
        return call;
    }

    public void setCall(Call<Object> call) {
        this.call = call;
    }

    public ReturnMode getReturnMode() {
        return returnMode;
    }

    public void setReturnMode(ReturnMode returnMode) {
        this.returnMode = returnMode;
    }

    public synchronized void setObject(Object object) {
        this.object = object;
        this.notify();
    }

    public synchronized Object getObject() throws InterruptedException {
        this.wait(requestTimes + 100);
        if(object instanceof Throwable) {
            
        }
        return object;
    }
}
