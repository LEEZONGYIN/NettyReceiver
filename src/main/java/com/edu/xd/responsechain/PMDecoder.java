package com.edu.xd.responsechain;


import com.edu.xd.Packet.PMPacket;
import com.edu.xd.Packet.Packet;
import io.netty.channel.ChannelHandlerContext;

public class PMDecoder extends MainDecoder {
    @Override
    public Packet decode(String data, ChannelHandlerContext ctx) {

        if (data.length()==38){
            PMPacket pmPacket = new PMPacket();
                pmPacket.setFlag("B");
                pmPacket.setData(data);
                pmPacket.setChannelId(ctx.channel().id().asShortText());
                return pmPacket;
        }else {
                System.out.println("pmpacket为空");
                return null;
            }
    }
}
