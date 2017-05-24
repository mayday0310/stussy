package com.zk.java8.system;

/**
 * Created by zk_chs on 16/7/18.
 */
public class SystemConfigs {

    public static void main(String[] args) {
        System.out.println(System.getProperty("java.vm.name")); // 获取JVM名字和类型
        System.out.println(System.getProperty("java.vm.info")); // 获取JVM的工作模式
        System.out.println(System.getProperty("os.name")); // 操作系统的名称
        System.out.println(System.getProperty("os.arch")); // 操作系统的架构
        System.out.println(System.getProperty("os.version")); // 操作系统的版本
        System.out.println(System.getProperty("java.version")); // Java虚拟机实现版本
        System.out.println(System.getProperty("java.vendor")); // Java运行时环境供应商
        System.out.println(System.getProperty("java.vendor.url")); // Java 供应商的URL
        System.out.println(System.getProperty("java.home")); // Java 安装目录
        System.out.println(System.getProperty("java.class.version")); // Java类格式版本号
        System.out.println(System.getProperty("java.class.path")); // Java类路径
        System.out.println(System.getProperty("java.library.path")); // 加载库时搜索的路径列表
        System.out.println(System.getProperty("java.io.tmpdir")); // 默认的临时文件路径
        System.out.println(System.getProperty("java.compiler")); // 要使用的 JIT 编译器的名称
        System.out.println(System.getProperty("file.separator")); // 文件分隔符（在 UNIX 系统中是“/”）
        System.out.println(System.getProperty("path.separator")); // 路径分隔符（在 UNIX 系统中是“:”）
        System.out.println(System.getProperty("line.separator")); // 行分隔符（在 UNIX 系统中是“/n”）
        System.out.println(System.getProperty("user.name")); // 用户的账户名称
        System.out.println(System.getProperty("user.home")); // 用户的主目录
        System.out.println(System.getProperty("user.dir")); // 用户的当前工作目录
    }

}
