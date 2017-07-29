package io.tradeledger.invoice;

public abstract class InvoiceFeeCalculator implements FeeCalculator {
    protected float invoiceAmount = 0;
    protected float invoiceFee = 0;
    private int invoiceDurationDays = 0;
    private int standardDiscountDays = -1;

    InvoiceFeeCalculator (float invoiceAmount, int invoiceDurationDays) {
        this.invoiceAmount = invoiceAmount;
        this.invoiceDurationDays = invoiceDurationDays;
    }

    protected InvoiceFeeCalculator standardDiscount(float rate, int standardDiscountDays) {
        this.standardDiscountDays = standardDiscountDays > this.invoiceDurationDays ? this.invoiceDurationDays : standardDiscountDays;
        this.invoiceFee += this.invoiceAmount * (rate / 100);
        return this;
    }

    protected InvoiceFeeCalculator additionalDiscount(float rate) throws RuleException {
        if (this.standardDiscountDays == -1) {
            throw new RuleException("Standard rates must be calculated before additional discounts.");
        }
        if (this.standardDiscountDays <= this.invoiceDurationDays) {
            int additionalDiscountDays = this.invoiceDurationDays - this.standardDiscountDays;
            this.invoiceFee += this.invoiceAmount * additionalDiscountDays * (rate / 100);
        }
        return this;
    }

    protected float getInvoiceFee () {
        return this.invoiceFee;
    }

    protected InvoiceFeeCalculator serviceFee() {
        return this;
    }
}
