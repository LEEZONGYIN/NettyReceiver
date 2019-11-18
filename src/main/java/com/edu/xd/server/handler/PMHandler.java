package com.edu.xd.server.handler;


import com.edu.xd.Packet.PMPacket;
import com.edu.xd.decode.PMDecoder;
import com.edu.xd.util.DataUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class PMHandler extends SimpleChannelInboundHandler<PMPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PMPacket pmPacket) throws Exception {

        System.out.println("PMHandler");

        String data = pmPacket.getData();

        String channelId = pmPacket.getChannelId();

        byte[] bytes = DataUtil.deocde(data);

        PMDecoder.decode(bytes,channelId);
    }
}
