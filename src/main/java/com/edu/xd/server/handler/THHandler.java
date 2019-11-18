package com.edu.xd.server.handler;


import com.edu.xd.Packet.THPacket;
import com.edu.xd.decode.THDecoder;
import com.edu.xd.util.DataUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class THHandler extends SimpleChannelInboundHandler<THPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, THPacket thPacket) throws Exception {
        System.out.println("THHandler");

        String data = thPacket.getData();
        //温湿度
        String channelId = thPacket.getChannelId();

        byte[] thByteByte = DataUtil.deocde(data);

        THDecoder.decode(thByteByte,channelId);

    }
}
