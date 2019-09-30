package com.softcase.surc.bo.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question {
  private String id;
  private String category;
  private String text;
  private String type;
  private List<String> values;
}
