package travelator.chap1;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;


public class Money_java {
    private final BigDecimal amount;
    private final Currency currency;

    // 비공개 생성자 -> 정적 메서드 of를 통해 Money 값을 얻음 (new와 of를 통해 객체와 값을 구분하는 현대 자바 관습)
    private Money_java(BigDecimal amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public static Money_java of(BigDecimal amount, Currency currency) {
        return new Money_java(
                amount.setScale(currency.getDefaultFractionDigits()),
                currency
        );
    }

    public Money_java add(Money_java that) {
        if (!this.currency.equals(that.currency)) {
            throw new IllegalArgumentException("cannot add Money values of different currencies");
        }

        return new Money_java(this.amount.add(that.amount), this.currency);
    }

    public BigDecimal getAmount() { return amount; }
    public Currency getCurrency() { return currency; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money_java money = (Money_java) o;
        return amount.equals(money.amount) && currency.equals(money.currency);
    }

    @Override
    public int hashCode() { return Objects.hash(amount, currency); }

    @Override
    public String toString() { return amount.toString() + " " + currency.toString(); }
}
