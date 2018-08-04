package com.siyann.socket;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import java.util.Date;

import com.siyann.db.DBUtils;

@Sharable
public class HeartBeatClientHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("激活时间是：" + new Date());
		String host = ctx.channel().remoteAddress().toString();
		host = host.substring(1, host.indexOf(":"));
		DBUtils.updateStatus(1, host);
		System.out.println("HeartBeatClientHandler channelActive");
		ctx.fireChannelActive();
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("停止时间是：" + new Date());
		System.out.println("HeartBeatClientHandler channelInactive");
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		String message =(String)msg;
		System.out.println(message);
		if (message.contains("\"command_code\":1001")) {
			String host = ctx.channel().remoteAddress().toString();
			host = host.substring(1, host.indexOf(":"));
			DBUtils.insertAlarm(message, host, 0);
		}
		if (message.contains("\"command_code\":\"5000001\"")) {
			String host = ctx.channel().remoteAddress().toString();
			host = host.substring(1, host.indexOf(":"));
			DBUtils.insertAlarm(message, host, 1);
		}
		ReferenceCountUtil.release(msg);
	}
}