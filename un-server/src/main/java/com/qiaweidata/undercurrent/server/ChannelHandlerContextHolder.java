package com.qiaweidata.undercurrent.server;

import io.netty.channel.ChannelHandlerContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ReWinD00
 * @date 2021/12/17 15:30
 */
public class ChannelHandlerContextHolder {

    private static final Map<Integer, ChannelHandlerContext> map = new ConcurrentHashMap<>();


    public static ChannelHandlerContext getChannelCtx(Integer id) {
        return map.getOrDefault(id, null);
    }

    public static void updateMap(Integer id, ChannelHandlerContext ctx) {
        map.put(id, ctx);
    }

    public static void removeByKey(Integer id) {
        ChannelHandlerContext channelHandlerContext = map.get(id);
        map.remove(id);
        if (channelHandlerContext != null) {
            channelHandlerContext.close();
        }
    }

    public static void removeByCtx(ChannelHandlerContext ctx) {
        for (Map.Entry<Integer, ChannelHandlerContext> entry : map.entrySet()) {
            if (entry.getValue().equals(ctx)) {
                map.remove(entry.getKey());
                break;
            }
        }
    }

    public static int getChannelLength() {
        return map.size();
    }
}
