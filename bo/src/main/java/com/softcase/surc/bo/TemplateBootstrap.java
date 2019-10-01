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
    String r = "Please rate us about ...";
    String[] rates = new String[] { "Good", "Average", "Poor" };

    Template t0 = Template.builder().title("Hotel hAll-White - Guest Comments").build()
        .addQuestion("0", r, "How did we do with Welcoming you?", "singleChoice", rates)
        .addQuestion("1", r, "What about cleanliness?", "singleChoice", rates)
        .addQuestion("2", r, "Where services satisfactory?", "yn", "")
        .addQuestion("3", "General", "Why did you choose to stay with us ?", "freeText", "", "", "")
        .addQuestion("4", "Suggestions?", "What would you improve ?", "freeText", "", "", "")
        .addQuestion("5", "Contacts", "Feel free to leave your contacts", "freeText", "", "", "")
        .addQuestion("6", "Contacts", "Fancy a contact by us?", "yn", "");

    Template t1 = Template.builder().title("Restaurant hAll-Good Food!").build()
        .addQuestion("0", "Rate us", "How much would you recomend us?", "singleChoice", 
            "0 - I wouldn't", "1 - Only to nearby people", "2 - Anyone in the city", "3 - Let me tell the world!")
        .addQuestion("1", r, "How often do you dine with us ?", "singleChoice", "monthly ...", "weekly ;-)",
            "everyday!")
        .addQuestion("2", "The Food", "What did you like the most ?", "multipleChoice", "first courses",
            "second courses", "desserts", "fresh fruit")
        .addQuestion("3", "The Server", "Was s/he attentive when you needed ?", "singleChoice", "completely",
            "most often", "kinda ...", "absolutely not :(")
        .addQuestion("4", r, "What would you improve ?", "freeText", "", "", "");

    List<Template> res = new ArrayList<>();
    res.add(t0);
    res.add(t1);
    return res;
  }

}
