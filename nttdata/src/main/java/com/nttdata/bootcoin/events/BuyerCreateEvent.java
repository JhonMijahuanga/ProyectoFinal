package com.nttdata.bootcoin.events;

import com.nttdata.bootcoin.model.bean.Buyer;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BuyerCreateEvent extends Event<Buyer>{
}
