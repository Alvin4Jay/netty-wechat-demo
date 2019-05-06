package the.flash.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * ByteBuf Test
 *
 * @author xuanjian.xuwj
 */
public class ByteBufTest2 {
    public static void main(String[] args) throws InterruptedException {

        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer(6, 10);

        byteBuf.writeBytes(new byte[]{1, 2, 3, 4});

        print("byteBuf", byteBuf);

//        ByteBuf byteBuf1 = byteBuf.slice();
//        print("byteBuf1", byteBuf1);
//        byteBuf1.setByte(0, 10);
//        byteBuf1.readByte();
//
//        print("a", byteBuf);
//        print("b", byteBuf1);

//         byteBuf1.writeByte(1);

//        ByteBuf byteBuf2 = byteBuf.duplicate();
//        print("byteBuf2", byteBuf2);
//
//        byteBuf2.writeByte(5);
//        byteBuf2.setByte(4, 8);
//        print("byteBuf", byteBuf);
//        print("byteBuf2", byteBuf2);
//        System.out.println(byteBuf.getByte(4));
//        System.out.println(byteBuf1.getByte(0));
//        System.out.println(byteBuf.getByte(0));
//        System.out.println(byteBuf2.getByte(4));

        ByteBuf byteBuf3 = byteBuf.copy();
        print("byteBuf3", byteBuf3);
//
//        byteBuf.release();
//
//        byteBuf3.writeByte(5);
//        System.out.println(byteBuf.getByte(4));
//        System.out.println(byteBuf3.getByte(4));
//        print("byteBuf", byteBuf);
//        print("byteBuf3", byteBuf3);

//        byteBuf.release();
//        byteBuf.release();
//        byteBuf.retain();
    }

    private static void print(String action, ByteBuf buffer) {
        System.out.println("after =========" + action +"==============");
        System.out.println("capacity(): " + buffer.capacity());
        System.out.println("maxCapacity(): " + buffer.maxCapacity());
        System.out.println("readerIndex(): " + buffer.readerIndex());
        System.out.println("readableBytes(): " + buffer.readableBytes());
        System.out.println("isReadable(): " + buffer.isReadable());
        System.out.println("writerIndex(): " + buffer.writerIndex());
        System.out.println("writableBytes(): " + buffer.writableBytes());
        System.out.println("isWritable(): " + buffer.isWritable());
        System.out.println("maxWritableBytes(): " + buffer.maxWritableBytes());
        System.out.println("refCnt(): " + buffer.refCnt());
        System.out.println();
    }
}
