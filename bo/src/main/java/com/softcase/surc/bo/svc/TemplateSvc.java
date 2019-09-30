package com.softcase.surc.bo.svc;

import com.softcase.surc.bo.commands.TemplateCmd;

public interface TemplateSvc {

  public TemplateCmd insertSurveyTemplate(TemplateCmd templateCmd);

  public TemplateCmd loadSurveyTemplate(String id);

  public TemplateCmd updateSurveyTemplate(TemplateCmd template);

}
