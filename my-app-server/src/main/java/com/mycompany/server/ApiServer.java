package com.mycompany.server;

import org.eclipse.jetty.http.HttpVersion;
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.eclipse.jetty.http.HttpScheme.HTTP;

public class ApiServer {
  private static final Logger LOGGER = LoggerFactory.getLogger(ApiServer.class);
  public static void main(String... args) throws Exception {
    LOGGER.info("Hello world!");

    // http config
    HttpConfiguration httpsConfiguration = new HttpConfiguration();
    httpsConfiguration.setSecureScheme(HTTP.asString());
    httpsConfiguration.setSecurePort(8443);
    httpsConfiguration.addCustomizer(new SecureRequestCustomizer()); // does ssl tls
    httpsConfiguration.setSendXPoweredBy(false);
    HttpConnectionFactory httpsConnectionFactory = new HttpConnectionFactory(httpsConfiguration);

    //ssl stuff
    SslContextFactory.Server sslContextFactory = new SslContextFactory.Server();
    sslContextFactory.setKeyStorePath("my-app-server/src/main/resources/certs/mycompany.p12");
    sslContextFactory.setKeyStorePassword("changeit");
    sslContextFactory.setKeyManagerPassword("changeit");
    sslContextFactory.setKeyStoreType("PKCS12");
    sslContextFactory.setTrustAll(true);
    SslConnectionFactory sslConnectionFactory = new SslConnectionFactory(sslContextFactory, HttpVersion.HTTP_1_1.asString());

    // jetty server instance
    Server server = new Server();
    ServerConnector httpsServerConnector = new ServerConnector(server, sslConnectionFactory, httpsConnectionFactory);
    httpsServerConnector.setPort(httpsConfiguration.getSecurePort());
    server.addConnector(httpsServerConnector);
    server.start();
    server.join();
  }
}
