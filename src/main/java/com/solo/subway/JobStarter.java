/**
 * @(#)JobStarter.java, Jun 17, 2015.
 * <p>
 * Copyright 2015 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.solo.subway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author licy03
 */
@SpringBootApplication
public class JobStarter {

    public static void main(String[] args) {
        System.setProperty("org.jboss.logging.provider", "slf4j");

        SpringApplication application = new SpringApplication(JobStarter.class);
        application.setWebApplicationType(WebApplicationType.NONE);
        application.run(args);
    }
}
