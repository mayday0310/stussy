package com.zk.google.guava.basicUtilities.others;

import com.google.common.net.InternetDomainName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by zk_chs on 5/8/17.
 *
 * 详细文档地址 -> https://github.com/google/guava/wiki/InternetDomainNameExplained
 */
public class DomainNameUsage {

    private static final Logger log = LogManager.getLogger();

    public static void main(String[] args) {
        InternetDomainName owner = InternetDomainName.from("mail.google.com");
        log.info(owner);
        log.info(owner.topPrivateDomain());
        log.info(owner.parent());
        log.info(owner.parts());
        log.info(owner.publicSuffix());
    }

}
