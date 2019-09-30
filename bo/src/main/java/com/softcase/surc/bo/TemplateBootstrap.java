package com.softcase.surc.bo;

import com.softcase.surc.bo.domain.Template;
import com.softcase.surc.bo.repos.TemplateRepo;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * An init function for basic testing of mongo embedded / providing some init data.
 */
@Slf4j
@AllArgsConstructor
@Component
public class TemplateBootstrap implements ApplicationListener<ContextRefreshedEvent> {

  private final TemplateRepo templateRepo;

  @Override
  @Transactional
  public void onApplicationEvent(ContextRefreshedEvent event) {
    templateRepo.saveAll(getTemplates());
    log.debug("Loading Bootstrap Data");
  }

  private List<Template> getTemplates() {
    String k = "General suggestions";

    Template t = Template.builder().description("No description provided yet").build()
        .addQuestion("005", k, "What's the case ?", "singleChoice", "case this", "case that",
            "take that!")
        .addQuestion("004", k, "What did you like the most ?", "multipleChoice", "the taste",
            "the scent", "the colours")
        .addQuestion("id", k, "What would you improve ?", "freeText", "", "", "")
        .addQuestion("id", "Food & drinks", "Did you like the food?", "yn", "", "", "");

    List<Template> res = new ArrayList<>();
    res.add(t);
    return res;
  }

}
