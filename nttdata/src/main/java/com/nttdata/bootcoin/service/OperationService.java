package com.nttdata.bootcoin.service;

import com.nttdata.bootcoin.model.bean.Operation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OperationService {

  Operation save(Operation operation);

  List<Operation> findOperationId(int id);

  List<Operation> findAll();

}
