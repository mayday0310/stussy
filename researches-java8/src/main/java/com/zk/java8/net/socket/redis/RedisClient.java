package com.zk.java8.net.socket.redis;


import redis.clients.jedis.BuilderFactory;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.io.IOException;
import java.net.Socket;

public class RedisClient {

    private byte[] bytes;
    private int count;
    private String ip;
    private int port;
    private Socket socket;
    private RedisInputStream input;
    private RedisOutputStream output;

    public RedisClient(String ip, int port){
        this.port = port;
        this.ip = ip;
        bytes = new byte[1024];
        count = 0;
    }

    public RedisClient(String ip, int port, int size){
        this.port = port;
        this.ip = ip;
        bytes = new byte[size];
        count = 0;
    }

    public RedisClient connect () {
        try {
            socket = new Socket(ip, port);
            input = new RedisInputStream(socket.getInputStream());
            output = new RedisOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            throw new RedisException("some exception throws while connect redis");
        }
        return this;
    }

    public void flush () throws IOException {
        if (output != null)
            output.flush();
    }

    public void close () throws IOException {
        if (input != null)
            input.close();
        if (output != null)
            output.close();
        if (socket != null)
            socket.close();
    }

    public void keys (String... args) throws IOException {

        byte[] command = Const.COMMAND.KEYS.name().getBytes();

        try {
            this.output.write(Const.ASTERISK_BYTE);
            this.output.writeIntCrLf(args.length + 1);
            this.output.write(Const.DOOLAR_BYTE);
            this.output.writeIntCrLf(command.length);
            this.output.write(command);
            this.output.writeCrLf();

            for (final String arg : args) {
                this.output.write(Const.DOOLAR_BYTE);
                this.output.writeIntCrLf(arg.getBytes("UTF-8").length);
                this.output.write(arg.getBytes("UTF-8"));
                this.output.writeCrLf();
            }
        } catch (IOException e) {
            throw new JedisConnectionException(e);
        }

        /** 写入缓冲数组并刷新缓冲区 */
        this.output.write(output.buf, 0, output.count);
        this.flush();
    }

    private void writeCRLF() throws IOException {
        this.bytes[count++] = '\r';
        this.bytes[count++] = '\n';
    }

    public RedisInputStream getInput() {
        return input;
    }

    public RedisOutputStream getOutput() {
        return output;
    }

    public static void main(String[] args) throws IOException {
        RedisClient client = new RedisClient("10.0.22.24", 6379).connect();
        client.keys("1227898377@qq.com");
        Object object = Protocol.read(client.getInput());
        System.out.println(BuilderFactory.STRING_SET.build(object));
        client.close();
    }

}