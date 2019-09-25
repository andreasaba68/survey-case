package com.softcase.surc.bo.controllers;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.Map;
import java.util.Properties;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by asaba on 20190924.
 */
@RestController
public class OpsServices {

  // TODO these must be active only under a non production profile

  /**
   * Returns a version indication.
   *
   * @return the Properties to be translated in JSon
   * @throws IOException if it can't read the manifest
   */
  @GetMapping("/ops/version")
  public Properties version() throws IOException {
    Properties res = new Properties();
    File f= new File(".\\target\\classes\\application.properties");
    res.put("backEndVersion", new Timestamp(f.lastModified()).toString());
    return res;
  }

  @GetMapping("/ops/properties")
  public Properties props() {
    return System.getProperties();
  }

  @GetMapping("/ops/env")
  public Map<String, String> env() {
    return System.getenv();
  }

  @GetMapping("/ops/user")
  public Principal user(Principal principal) {
    return principal;
  }

}
