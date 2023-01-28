package com.mycompany.server;

import org.eclipse.jetty.server.HttpConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.eclipse.jetty.http.HttpScheme.HTTP;

public class ApiServer {
  private static final Logger LOGGER = LoggerFactory.getLogger(ApiServer.class);
  public static void main(String... args) {
    LOGGER.info("Hello world!");

    HttpConfiguration httpsConfiguration = new HttpConfiguration();
    httpsConfiguration.setSecureScheme(HTTP.asString());
  }
}
