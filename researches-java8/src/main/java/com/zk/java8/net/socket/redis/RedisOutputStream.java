package com.zk.java8.net.socket.redis;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;


/**
 * 功能描述：类似于BufferedOutputStream,不过将同步的写操作去除掉了<br/>
 * 创建时间：2016-6-30<br/>
 */
public class RedisOutputStream extends FilterOutputStream {

    /**
     * 输出时的缓冲数组
     */
    protected final byte[] buf;

    /**
     * 记录缓冲数组中字节数,如果接下来的空间不够,则刷新一次缓存
     */
    protected int count;

    private final static int[] sizeTable = { 9, 99, 999, 9999, 99999, 999999, 9999999, 99999999,
            999999999, Integer.MAX_VALUE };

    private final static byte[] DigitTens = { '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1',
            '1', '1', '1', '1', '1', '1', '1', '1', '1', '2', '2', '2', '2', '2', '2', '2', '2', '2',
            '2', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '4', '4', '4', '4', '4', '4', '4',
            '4', '4', '4', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '6', '6', '6', '6', '6',
            '6', '6', '6', '6', '6', '7', '7', '7', '7', '7', '7', '7', '7', '7', '7', '8', '8', '8',
            '8', '8', '8', '8', '8', '8', '8', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', };

    private final static byte[] DigitOnes = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0',
            '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8',
            '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4',
            '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2',
            '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', };

    private final static byte[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a',
            'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
            't', 'u', 'v', 'w', 'x', 'y', 'z' };

    public RedisOutputStream(final OutputStream out) {
        this(out, 8192);
    }

    public RedisOutputStream(final OutputStream out, final int size) {
        super(out);
        if (size <= 0) {
            throw new IllegalArgumentException("Buffer size <= 0");
        }
        buf = new byte[size];
    }

    /** 如果超过了缓冲数组的大小,就先写一次,并且将缓冲数组当前位置置为0 */
    private void flushBuffer() throws IOException {
        if (count > 0) {
            out.write(buf, 0, count);
            count = 0;
        }
    }

    /** 写单个字节 */
    public void write(final byte b) throws IOException {
        if (count == buf.length) {
            flushBuffer();
        }
        buf[count++] = b;
    }

    @Override
    public void write(final byte[] b) throws IOException {
        write(b, 0, b.length);
    }

    @Override
    public void write(final byte[] b, final int off, final int len) throws IOException {
        if (len >= buf.length) {
            flushBuffer();
            out.write(b, off, len);
        } else {
            if (len >= buf.length - count) {
                flushBuffer();
            }

            System.arraycopy(b, off, buf, count, len);
            count += len;
        }
    }

    public void writeAsciiCrLf(final String in) throws IOException {
        final int size = in.length();

        for (int i = 0; i != size; ++i) {
            if (count == buf.length) {
                flushBuffer();
            }
            buf[count++] = (byte) in.charAt(i);
        }

        writeCrLf();
    }

    /**
     * 有效的 Unicode代码点范围是 U+0000 至 U+10FFFF
     * Java的char类型是固定16bits的。代码点在U+0000 — U+FFFF之内到是可以用一个char完整的表示出一个字符
     * 但代码点在U+FFFF之外的，一个char无论如何无法表示一个完整字符
     * 增补字符就是代码点在 U+10000 至 U+10FFFF 范围之间的字符
     * @param ch
     * @return
     */
    public static boolean isSurrogate(final char ch) {
        boolean b1 = ch >= Character.MIN_SURROGATE;
        boolean b2 = ch <= Character.MAX_SURROGATE;
        return ch >= Character.MIN_SURROGATE && ch <= Character.MAX_SURROGATE;
    }

    /**
     * utf-8使用一至四个字节的序列对编码 Unicode 代码点进行编码
     * U+0000 至 U+007F 使用一个字节编码,对应 ASCII 字符集
     * U+0080 至 U+07FF 使用两个字节
     * U+0800 至 U+FFFF 使用三个字节
     * U+10000 至 U+10FFFF 使用四个字节
     * @param str
     * @return
     */
    public static int utf8Length(final String str) {
        int strLen = str.length(), utfLen = 0;
        for (int i = 0; i != strLen; ++i) {
            char c = str.charAt(i);
            if (c < 0x80) {
                utfLen++;
            } else if (c < 0x800) {
                utfLen += 2;
            } else if (isSurrogate(c)) { // 0xD800 < c < 0xDFFF
                i++;
                utfLen += 4;
            } else {
                utfLen += 3;
            }
        }
        return utfLen;
    }

    public void writeCrLf() throws IOException {
        if (2 >= buf.length - count) {
            flushBuffer();
        }

        buf[count++] = '\r';
        buf[count++] = '\n';
    }

    public void writeUtf8CrLf(final String str) throws IOException {
        int strLen = str.length();

        int i;
        for (i = 0; i < strLen; i++) {
            char c = str.charAt(i);
            if (!(c < 0x80)) break;
            if (count == buf.length) {
                flushBuffer();
            }
            buf[count++] = (byte) c;
        }

        for (; i < strLen; i++) {
            char c = str.charAt(i);
            if (c < 0x80) {
                if (count == buf.length) {
                    flushBuffer();
                }
                buf[count++] = (byte) c;
            } else if (c < 0x800) {
                if (2 >= buf.length - count) {
                    flushBuffer();
                }
                buf[count++] = (byte) (0xc0 | (c >> 6));
                buf[count++] = (byte) (0x80 | (c & 0x3f));
            } else if (isSurrogate(c)) {
                if (4 >= buf.length - count) {
                    flushBuffer();
                }
                int uc = Character.toCodePoint(c, str.charAt(i++));
                buf[count++] = ((byte) (0xf0 | ((uc >> 18))));
                buf[count++] = ((byte) (0x80 | ((uc >> 12) & 0x3f)));
                buf[count++] = ((byte) (0x80 | ((uc >> 6) & 0x3f)));
                buf[count++] = ((byte) (0x80 | (uc & 0x3f)));
            } else {
                if (3 >= buf.length - count) {
                    flushBuffer();
                }
                buf[count++] = ((byte) (0xe0 | ((c >> 12))));
                buf[count++] = ((byte) (0x80 | ((c >> 6) & 0x3f)));
                buf[count++] = ((byte) (0x80 | (c & 0x3f)));
            }
        }

        writeCrLf();
    }

    public void writeIntCrLf(int value) throws IOException {
        if (value < 0) {
            write((byte) '-');
            value = -value;
        }

        int size = 0;
        while (value > sizeTable[size])
            size++;

        size++;
        if (size >= buf.length - count) {
            flushBuffer();
        }

        int q, r;
        int charPos = count + size;

        while (value >= 65536) {
            q = value / 100;
            r = value - ((q << 6) + (q << 5) + (q << 2));
            value = q;
            buf[--charPos] = DigitOnes[r];
            buf[--charPos] = DigitTens[r];
        }

        for (;;) {
            q = (value * 52429) >>> (16 + 3);
            r = value - ((q << 3) + (q << 1));
            buf[--charPos] = digits[r];
            value = q;
            if (value == 0) break;
        }
        count += size;

        writeCrLf();
    }

    @Override
    public void flush() throws IOException {
        flushBuffer();
        out.flush();
    }

}
