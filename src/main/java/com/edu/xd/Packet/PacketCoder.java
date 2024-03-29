package com.edu.xd.Packet;

import com.edu.xd.hbase.HbaseUtil;
import com.edu.xd.redis.RedisUtil;
import com.edu.xd.responsechain.MainDecoder;
import com.edu.xd.responsechain.PMDecoder;
import com.edu.xd.responsechain.THDecoder;
import com.edu.xd.util.DataUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;



public class PacketCoder {

    public Packet decode (ByteBuf byteBuf, ChannelHandlerContext ctx){

        //解析源数据
        int lenData = byteBuf.readableBytes();
        byte[] dataBytes = new byte[lenData];
        byteBuf.readBytes(dataBytes);
        String rawData = DataUtil.encode(dataBytes);

        //责任链
        MainDecoder thDecoder = new THDecoder();
        MainDecoder pmDecoder = new PMDecoder();

        thDecoder.setNext(pmDecoder);

        return  thDecoder.decode(rawData,ctx);

//        byteBuf.markReaderIndex();
//        int lenData = byteBuf.readableBytes();
//        byte[] dataBytes = new byte[lenData];
//        byteBuf.readBytes(dataBytes);
//        String rawData = DataUtil.encode(dataBytes);
//        byteBuf.resetReaderIndex();
//        System.out.println("源数据 ："+rawData);
//        System.out.println("len: "+rawData.length());
//
//            byteBuf.skipBytes(2);
//
//            //读取返回数据长度
//            byte[] lenBytes = new byte[1];
//            byteBuf.readBytes(lenBytes);
//            String lenStr = DataUtil.encode(lenBytes);
//            //得到数据个数
//            int len = Integer.parseInt(lenStr, 16) / 2;
//
//            byte[] bytes = new byte[byteBuf.readableBytes()];
//            byteBuf.readBytes(bytes);
//            String dataStr = DataUtil.encode(bytes);
//
//            if (len == 2) {
//                THPacket thPacket = new THPacket();
//                thPacket.setFlag("A");
//                thPacket.setData(dataStr);
//                thPacket.setChannelId(ctx.channel().id().asShortText());
//                return thPacket;
//            } else if (len == 7) {
//                PMPacket pmPacket = new PMPacket();
//                pmPacket.setFlag("B");
//                pmPacket.setData(dataStr);
//                pmPacket.setChannelId(ctx.channel().id().asShortText());
//                return pmPacket;
//            }

//            return null;

    }

    public void split (ByteBuf byteBuf, ChannelHandlerContext ctx) throws Exception{
        //定义数据库
        RedisUtil redisUtil = new RedisUtil();
        HbaseUtil hbaseUtil = new HbaseUtil();

        int lenData = byteBuf.readableBytes();
        byte[] dataBytes = new byte[lenData];
        byteBuf.readBytes(dataBytes);
        String gateData = DataUtil.encode(dataBytes);
        //将网关和channel id 存入redis
        if (gateData!=null) {
            System.out.println("网关");
            redisUtil.set(gateData, ctx.channel().id().asShortText());
            //网关存入mysql
            String[] colums = new String[]{"cf"};
            hbaseUtil.createTable(gateData,colums);
        }else {
            System.out.println("网关为空");
        }

    }


}
