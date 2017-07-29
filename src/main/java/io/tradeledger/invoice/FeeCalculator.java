package io.tradeledger.invoice;

public interface FeeCalculator {
    /*FeeCalculator standardDiscount(float rate, int days);
    FeeCalculator additionalDiscount(float rate) throws RuleException;
    FeeCalculator serviceFee();*/
    float totalFee() throws RuleException;
}
