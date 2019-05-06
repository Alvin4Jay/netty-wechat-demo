package the.flash.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * Netty Server
 *
 * @author xuanjian
 */
public class NettyServer {

    private static final int BEGIN_PORT = 8000;

    private static final AttributeKey<String> SERVER_NAME_KEY =  AttributeKey.newInstance("serverName");

    private static final AttributeKey<String> CLIENT_SIZE_KEY =  AttributeKey.newInstance("childSide");

    public static void main(String[] args) {

        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();

        serverBootstrap
                .group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        String clientValue = ch.attr(CLIENT_SIZE_KEY).get();
                        System.out.println("客户端Channel属性: " + clientValue);

                        ch.pipeline().addLast(new FirstServerHandler());
                    }
                })
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<NioServerSocketChannel>() {
                    @Override
                    protected void initChannel(NioServerSocketChannel ch) throws Exception {
                        System.out.println("服务端启动中...");
                        String serverName = ch.attr(SERVER_NAME_KEY).get();
                        System.out.println("服务名: " + serverName);
                    }
                })
                .attr(SERVER_NAME_KEY, "netty-server")
                .childAttr(CLIENT_SIZE_KEY, "clientValue");

        bind(serverBootstrap, BEGIN_PORT);

    }

    private static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if (future.isSuccess()) {
                    System.out.println("绑定端口" + port + "成功");
                } else {
                    System.err.println("绑定端口" + port + "失败");
                    bind(serverBootstrap, port + 1);
                }
            }
        });
    }

}
