package io.tradeledger.invoice;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class FeeRule1Test {
    final float invoiceAmount = 80000.00F;
    final float exactMatchDelta = 0.000F;

    @Before
    public void prepare () {}

    @Test
    public void given_26_days_calculates_invoice_fee () throws RuleException {
        final int invoiceDurationDays = 26;
        final float expectedFee = 4800.0F;

        FeeRule1 feeRule1 = new FeeRule1(invoiceAmount, invoiceDurationDays);

        assertEquals(
                expectedFee,
                feeRule1.totalFee()
                , exactMatchDelta);
    }

    @Test
    public void given_45_days_calculates_invoice_fee () throws RuleException {
        final int invoiceDurationDays = 45;
        final float expectedFee = 18400.0F;

        FeeRule1 feeRule1 = new FeeRule1(invoiceAmount, invoiceDurationDays);

        assertEquals(
                expectedFee,
                feeRule1.totalFee()
                , exactMatchDelta);
    }

    @Test
    public void invoice_duration_less_than_standard_discount_days() throws RuleException {
        final int invoiceDurationDays = 15;
        final float expectedFee = 4800.0F;

        FeeRule1 feeRule1 = new FeeRule1(invoiceAmount, invoiceDurationDays);

        assertEquals(
                expectedFee,
                feeRule1.totalFee()
                , exactMatchDelta);
    }
}
