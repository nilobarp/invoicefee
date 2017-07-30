## Build with maven
```Bash
$ mvn clean install
```
* Java 1.6+ is required

## Test
From project root run 
```bash
$ mvn test
```

## Manual test
Use the `jar` built from above step and pass in invoice amount, duration and rule number

```bash
$ java -jar target/invoice-fee-1.0-SNAPSHOT.jar 80000 28 1
Total invoice fee: 4800.0
$
```

## Design Consideration
Initially the problem at hand looked to be a good candidate for decorator pattern
but it quickly started getting ugly with the API where calls like
```Java
new InvoiceFee(new StandardDiscount(new AdditinalDiscount(new ServiceFee(80000, 26))))
```
had to be made. Moreover Service Fee calculation isn't same for every rule.

To make the rules fairly independent and self contained a combination of Command and
Composition pattern was chosen. With this paradigm it is trivial to create new rules as shown below.

```Java
public class FeeRule1 extends InvoiceFeeCalculator {
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
```

Every rule is a self contained entity that knows how to calculate the invoice fee,
the base class provides implementations for standard and additional fee calculation
but it is left to the extending class to provide logic for service fee.

The fluent API imposes only one restriction i.e. Standard Discount rate needs to be
calculated before calling Additional Discount rate.

For more complex rules the child classes can override methods from base class to provide
new logic.

## Use of rules
Rules have only one public method `totalFee()` and takes amount and duration of invoice
while instantiating.

```Java
FeeCalculator calculator = new FeeRule1(invoiceAmount, invoiceDurationDays);
float invoiceFee = calculator.totalFee();
System.out.println(invoiceFee);
```