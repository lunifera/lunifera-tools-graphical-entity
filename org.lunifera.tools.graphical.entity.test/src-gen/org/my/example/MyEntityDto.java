package org.my.example;

import java.io.Serializable;
import org.my.example.MyParentDto;

@SuppressWarnings("all")
public class MyEntityDto extends MyParentDto implements Serializable {
  private String value;
}
