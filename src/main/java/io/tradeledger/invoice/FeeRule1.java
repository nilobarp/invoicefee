package io.tradeledger.invoice;

public class FeeRule1 extends InvoiceFeeCalculator implements FeeCalculator {
    private final float standardDiscountRate = 4.0F;
    private final int standardDiscountDays = 28;
    private final float additionalDiscountRate = 1.0F;

    public FeeRule1(float invoiceAmount, int invoiceDurationDays) {
        super(invoiceAmount, invoiceDurationDays);
    }

    @Override
    protected InvoiceFeeCalculator serviceFee() {
        final double serviceFeeRate = 2.0;
        this.invoiceFee += this.invoiceAmount * (serviceFeeRate / 100);
        return this;
    }

    public float totalFee () throws RuleException {
        return this.standardDiscount(standardDiscountRate, standardDiscountDays)
                .additionalDiscount(additionalDiscountRate)
                .serviceFee()
                .invoiceFee;
    }
}
