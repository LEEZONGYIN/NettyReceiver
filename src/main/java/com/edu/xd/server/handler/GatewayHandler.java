package com.edu.xd.server.handler;


import com.edu.xd.Packet.GatewayPacket;
import com.edu.xd.decode.GatewayDecoder;
import com.edu.xd.util.DataUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class GatewayHandler extends SimpleChannelInboundHandler<GatewayPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext cxt, GatewayPacket gatewayPacket) throws Exception {
        String getWay = gatewayPacket.getData();
        byte[] bytes = DataUtil.deocde(getWay);
        GatewayDecoder.decode(bytes);
    }
}
