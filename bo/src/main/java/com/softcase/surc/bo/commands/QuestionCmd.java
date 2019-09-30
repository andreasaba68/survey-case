package com.softcase.surc.bo.commands;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionCmd {
  private String id;
  private String category;
  private String text;
  private String type;
  private List<String> values;
}
