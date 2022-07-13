package com.nttdata.bootcoin.events;

import com.nttdata.bootcoin.model.bean.Operation;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class OperationEvent extends Event<Operation> {
}
