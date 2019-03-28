package exercises

import kotlin.math.absoluteValue

// RatioQuantity - Analysis pattern by Martin Fowler

class RatioQuantity internal constructor(amount: Number, unit: Unit) : IntervalQuantity(amount, unit) {

    private val amount = amount.toDouble()
    private val unit = unit

    operator fun plus(other: RatioQuantity): RatioQuantity = RatioQuantity(this.amount + convertedAmount(other), this.unit)
    operator fun unaryPlus() = this
    operator fun minus(other: RatioQuantity) = this + - other
    operator fun unaryMinus() = RatioQuantity(- amount, unit)
}