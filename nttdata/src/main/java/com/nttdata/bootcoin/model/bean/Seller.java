package com.nttdata.bootcoin.model.bean;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Builder
@Data
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Seller implements Serializable {

  @Id
  private String id;
  private String nYanki;
  private double balance;
}
