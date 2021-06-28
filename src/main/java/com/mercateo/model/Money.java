package com.mercateo.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Locale;
import java.util.Objects;

import static com.mercateo.config.Config.*;

/**
 *
 *
 * <h1>Money</h1>
 *
 * <p>Money static factory class that represents money
 */
public class Money {

  private BigDecimal amount;

  private Currency currency;

  public static Money money(BigDecimal amount, Currency currency) {
    return new Money(amount, currency);
  }

  private Money(BigDecimal amount, Currency currency) {
    this(amount, currency, DEFAULT_ROUNDING);
  }

  private Money(BigDecimal amount, Currency currency, RoundingMode rounding) {
    this.currency = currency;
    this.amount = amount.setScale(currency.getDefaultFractionDigits(), rounding);
  }

  private Money() {}

  public BigDecimal getAmount() {
    return amount;
  }

  public Currency getCurrency() {
    return currency;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Money money = (Money) o;
    return Objects.equals(amount, money.amount) && Objects.equals(currency, money.currency);
  }

  @Override
  public int hashCode() {
    return Objects.hash(amount, currency);
  }

  @Override
  public String toString() {
    return getCurrency().getSymbol() + " " + getAmount();
  }

  public String toString(Locale locale) {
    return getCurrency().getSymbol(locale) + " " + getAmount();
  }
}
