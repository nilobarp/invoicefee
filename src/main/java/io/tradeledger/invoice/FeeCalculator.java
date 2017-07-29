package io.tradeledger.invoice;

public interface FeeCalculator {
    float totalFee() throws RuleException;
}
