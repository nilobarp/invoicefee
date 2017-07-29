package io.tradeledger.invoice;

import org.junit.Test;

import static org.junit.Assert.*;

public class FeeRule2Test {
    final float invoiceAmount = 80000.00F;
    final float exactMatchDelta = 0.000F;

    @Test
    public void given_10_days_calculates_invoice_fee () throws RuleException {
        final int invoiceDurationDays = 10;
        final float expectedFee = 3120.0F;

        FeeRule2 feeRule2 = new FeeRule2(invoiceAmount, invoiceDurationDays);

        assertEquals(
                expectedFee,
                feeRule2.totalFee()
                , exactMatchDelta);
    }

    @Test
    public void given_35_days_calculates_invoice_fee () throws RuleException {
        final int invoiceDurationDays = 35;
        final float expectedFee = 18720.0F;

        FeeRule2 feeRule2 = new FeeRule2(invoiceAmount, invoiceDurationDays);

        assertEquals(
                expectedFee,
                feeRule2.totalFee()
                , exactMatchDelta);
    }
}
