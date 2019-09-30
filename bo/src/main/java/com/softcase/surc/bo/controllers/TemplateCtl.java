package com.softcase.surc.bo.controllers;

import com.softcase.surc.bo.commands.PropsBuilder;
import com.softcase.surc.bo.commands.TemplateCmd;
import com.softcase.surc.bo.svc.TemplateSvc;

import java.security.Principal;
import java.util.Properties;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest Controller to manage BackOffice Survey requests.
 */
@RestController
public class TemplateCtl {
  private static final String ROOT_URL = "/bo/svc/";
  public static final String TEMPLATE = ROOT_URL + "template";
  public static final String TEMPLATES = ROOT_URL + "templates";

  TemplateSvc templateSvc;

  public TemplateCtl(TemplateSvc templateSvc) {
    super();
    this.templateSvc = templateSvc;
  }

  /**
   * Inserts a new Survey Template in the storage.
   *
   * @param principal
   *          the logged user performing the save.
   *
   * @return the id of the survey if save goes all right
   */
  @PostMapping(path = TEMPLATE, consumes = "application/json", produces = "application/json")
  public Properties insertSurveyTemplate(Principal principal, @RequestBody TemplateCmd template) {
    return new PropsBuilder("id", templateSvc.insertSurveyTemplate(template).getId());
  }

  /**
   * Updates a previously loaded Survey Template in the storage.
   *
   * @param principal
   *          the logged user performing the save.
   *
   * @return the id of the survey if save goes all right
   */
  @PutMapping(path = TEMPLATE)
  public Properties updateSurveyTemplate(Principal principal, @RequestBody TemplateCmd template) {
    return new PropsBuilder("id", templateSvc.updateSurveyTemplate(template).getId());
  }

  /**
   * Loads the identified survey template.
   *
   * @param principal
   *          the logged user performing the save.
   *
   * @return the id of the survey if save goes all right
   */
  @GetMapping(path = TEMPLATE + "/{id}", produces = "application/json")
  public TemplateCmd loadSurveyTemplate(Principal principal, @PathVariable("id") String id) {
    return templateSvc.loadSurveyTemplate(id);
  }

}
