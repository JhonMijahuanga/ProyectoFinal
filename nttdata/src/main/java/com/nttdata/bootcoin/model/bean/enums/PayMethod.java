package com.nttdata.bootcoin.model.bean.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public enum PayMethod {

  YANKI("YANKI"), TRANSFER("TRANSFER");
  private String name;

}
