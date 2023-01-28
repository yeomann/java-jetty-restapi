package com.mycompany.server;

import org.eclipse.jetty.server.HttpConfiguration;

import static org.eclipse.jetty.http.HttpScheme.HTTP;

public class ApiServer {
  public static void main(String... args) {
    System.out.println("Hello world!");

    HttpConfiguration httpsConfiguration = new HttpConfiguration();
    httpsConfiguration.setSecureScheme(HTTP.asString());
  }
}
