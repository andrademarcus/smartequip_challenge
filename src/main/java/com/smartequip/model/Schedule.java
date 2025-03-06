package com.smartequip.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class Schedule {
    private Map<String, YearData> years;
    private double defaultMarketRatio;
    private double defaultAuctionRatio;
}
