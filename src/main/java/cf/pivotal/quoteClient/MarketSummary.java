/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cf.pivotal.quoteClient;

import java.math.BigDecimal;
import java.util.Date;

public class MarketSummary {

    private BigDecimal tradeStockIndexAverage;

    private BigDecimal tradeStockIndexVolume;

    private BigDecimal tradeStockIndexOpenAverage;

    private Date summaryDate;

    private BigDecimal change;

    private String percentGain;

    BigDecimal getChange() {
        return change;
    }

    void setChange(BigDecimal change) {
        this.change = change;
    }

    BigDecimal getTradeStockIndexAverage() {
        return tradeStockIndexAverage;
    }

    void setTradeStockIndexAverage(BigDecimal tradeStockIndexAverage) {
        this.tradeStockIndexAverage = tradeStockIndexAverage;
    }

    BigDecimal getTradeStockIndexVolume() {
        return tradeStockIndexVolume;
    }

    void setTradeStockIndexVolume(BigDecimal tradeStockIndexVolume) {
        this.tradeStockIndexVolume = tradeStockIndexVolume;
    }

    BigDecimal getTradeStockIndexOpenAverage() {
        return tradeStockIndexOpenAverage;
    }

    void setTradeStockIndexOpenAverage(
            BigDecimal tradeStockIndexOpenAverage) {
        this.tradeStockIndexOpenAverage = tradeStockIndexOpenAverage;
    }

    Date getSummaryDate() {
        return summaryDate;
    }

    void setSummaryDate(Date summaryDate) {
        this.summaryDate = summaryDate;
    }

    @Override
    public String toString() {
        return "MarketSummary [tradeStockIndexAverage="
                + tradeStockIndexAverage + ", tradeStockIndexVolume="
                + tradeStockIndexVolume + ", tradeStockIndexOpenAverage="
                + tradeStockIndexOpenAverage +
                ", summaryDate=" + summaryDate
                + ", percentGain=" + percentGain + "]";
    }

    void setPercentGain(String percentGain) {
        this.percentGain = percentGain;
    }

}