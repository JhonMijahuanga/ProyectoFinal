package com.nttdata.bootcoin.service;

import com.nttdata.bootcoin.model.bean.Buyer;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface BuyerService {

  Buyer save(Buyer buyer);

  List<Buyer> findAll();

  Buyer findBuyerId(int id);

  String deleteBuyer(int id);

}
