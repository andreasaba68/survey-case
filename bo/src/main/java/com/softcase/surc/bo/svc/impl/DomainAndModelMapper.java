package com.softcase.surc.bo.svc.impl;

import com.softcase.surc.bo.commands.TemplateCmd;
import com.softcase.surc.bo.domain.Template;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DomainAndModelMapper {

  // ----
  TemplateCmd templateToTemplateCmd(Template s);

  TemplateCmd templateToTemplateCmd(Template s, @MappingTarget TemplateCmd t);

  Template templateCmdToTemplate(TemplateCmd s);

  Template templateCmdToTemplate(TemplateCmd s, @MappingTarget Template t);

}
