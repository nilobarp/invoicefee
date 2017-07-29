package io.tradeledger.invoice;

public class FeeRule2 extends InvoiceFeeCalculator implements FeeCalculator {
    private final float standardDiscountRate = 3.0F;
    private final int standardDiscountDays = 20;
    private final float additionalDiscountRate = 1.0F;

    public FeeRule2(float invoiceAmount, int days) {
        super(invoiceAmount, days);
    }

    @Override
    public InvoiceFeeCalculator serviceFee() {
        final double serviceFeeRate = 30.0;
        this.invoiceFee += this.getInvoiceFee() * (serviceFeeRate / 100);
        return this;
    }

    public float totalFee() throws RuleException {
        return this.standardDiscount(standardDiscountRate, standardDiscountDays)
                .additionalDiscount(additionalDiscountRate)
                .serviceFee()
                .invoiceFee;
    }
}
