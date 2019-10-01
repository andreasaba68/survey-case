package com.softcase.surc.bo.svc.impl;

import com.softcase.surc.bo.commands.TemplateCmd;
import com.softcase.surc.bo.domain.Template;
import com.softcase.surc.bo.repos.TemplateRepo;
import com.softcase.surc.bo.svc.TemplateSvc;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TemplateSvcImpl implements TemplateSvc {

  private DomainAndModelMapper mapper;
  private TemplateRepo templateRepo;

  /**
   * Inserts the survey template in the database.
   * 
   * @param cmd
   *          the template
   * @return
   */
  @Override
  public TemplateCmd insertSurveyTemplate(TemplateCmd cmd) {
    Template entity = mapper.templateCmdToTemplate(cmd);
    templateRepo.insert(entity);
    return mapper.templateToTemplateCmd(templateRepo.insert(entity));
  }

  @Override
  public TemplateCmd loadSurveyTemplate(String id) {
    return mapper.templateToTemplateCmd(templateRepo.findById(id).orElse(null));
  }

  @Override
  public TemplateCmd updateSurveyTemplate(TemplateCmd cmd) {
    Template entity = mapper.templateCmdToTemplate(cmd);
    return mapper.templateToTemplateCmd(templateRepo.save(entity));
  }

  @Override
  public List<TemplateCmd> getAllSurveyTemplates() {
    return templateRepo.findAll().stream()
        .map(t -> TemplateCmd.builder().id(t.getId()).title(t.getTitle()).build())
        .collect(Collectors.toList());
  }

}
