package jss.bugtorch.mixins.early.minecraft.logcleanup;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SocketChannel;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOutboundBuffer;
import io.netty.channel.nio.AbstractNioByteChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

@Mixin(value = NioSocketChannel.class, remap = false)
public abstract class MixinNioSocketChannel extends AbstractNioByteChannel {

    MixinNioSocketChannel(Channel parent, SelectableChannel ch) {
		super(parent, ch);
	}

	/**
	 * @author C0bra5
	 * @reason Fixes Netty causing excessive log spam and a resource leak when a connection is improperly terminated.
	 */
    @Overwrite
    protected void doWrite(ChannelOutboundBuffer in) throws Exception {
        for (;;) {
            // Do non-gathering write for a single buffer case.
            final int msgCount = in.size();
            if (msgCount <= 1) {
                super.doWrite(in);
                return;
            }

            // Ensure the pending writes are made of ByteBufs only.
            ByteBuffer[] nioBuffers = in.nioBuffers();
            if (nioBuffers == null) {
                super.doWrite(in);
                return;
            }

            int nioBufferCnt = in.nioBufferCount();
            long expectedWrittenBytes = in.nioBufferSize();

            final SocketChannel ch = (SocketChannel)javaChannel();
            long writtenBytes = 0;
            boolean done = false;
            for (int i = config().getWriteSpinCount() - 1; i >= 0; i --) {
            	long localWrittenBytes = 0l;
            	try {
            		localWrittenBytes = ch.write(nioBuffers, 0, nioBufferCnt);
            	} catch(IOException ex) {
            		close();
            	}

                if (localWrittenBytes == 0) {
                    break;
                }
                expectedWrittenBytes -= localWrittenBytes;
                writtenBytes += localWrittenBytes;
                if (expectedWrittenBytes == 0) {
                    done = true;
                    break;
                }
            }

            if (done) {
                // Release all buffers
                for (int i = msgCount; i > 0; i --) {
                    in.remove();
                }

                // Finish the write loop if no new messages were flushed by in.remove().
                if (in.isEmpty()) {
                    clearOpWrite();
                    break;
                }
            } else {
                // Did not write all buffers completely.
                // Release the fully written buffers and update the indexes of the partially written buffer.

                for (int i = msgCount; i > 0; i --) {
                    final ByteBuf buf = (ByteBuf) in.current();
                    final int readerIndex = buf.readerIndex();
                    final int readableBytes = buf.writerIndex() - readerIndex;

                    if (readableBytes < writtenBytes) {
                        in.progress(readableBytes);
                        in.remove();
                        writtenBytes -= readableBytes;
                    } else if (readableBytes > writtenBytes) {
                        buf.readerIndex(readerIndex + (int) writtenBytes);
                        in.progress(writtenBytes);
                        break;
                    } else { // readableBytes == writtenBytes
                        in.progress(readableBytes);
                        in.remove();
                        break;
                    }
                }

                setOpWrite();
                break;
            }
        }
    }

}
