package com.smartequip.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Equipment {
    private Schedule schedule;
    private SaleDetails saleDetails;
    private Classification classification;
}
