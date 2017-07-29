import io.tradeledger.invoice.FeeRule1;
import io.tradeledger.invoice.FeeRule2;

public class InvoiceFee {
    public static void main (String args[]) {
        if (args.length < 3) {
            System.out.println();
            System.out.println("Missing arguments");
            System.out.println("Usage: InvoiceFee [amount] [duration] [rule number]");
            System.out.println("Example: InvoiceFee 80000.00 50 1");
            return;
        }
        float invoiceAmount = 0;
        int invoiceDurationDays = 0;
        int ruleNumber = 0;
        try {
            invoiceAmount = Float.parseFloat(args[0]);
            invoiceDurationDays = Integer.parseInt(args[1]);
            ruleNumber = Integer.parseInt(args[2]);

            float invoiceFee = 0;

            switch (ruleNumber) {
                case 1:
                    FeeRule1 feeRule1 = new FeeRule1(invoiceAmount, invoiceDurationDays);
                    invoiceFee = feeRule1.totalFee();
                    break;
                case 2:
                    FeeRule2 feeRule2 = new FeeRule2(invoiceAmount, invoiceDurationDays);
                    invoiceFee = feeRule2.totalFee();
            }

            System.out.println("Total invoice fee: " + invoiceFee);

        } catch (NumberFormatException nfe) {
            System.out.println("Invalid argument: number expected.");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
