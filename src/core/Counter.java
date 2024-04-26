// 322624206 Daniel Talker

package core;

/**
 * The type Counter.
 */
public class Counter {
    private int value;

    /**
     * Instantiates a new Counter.
     *
     * @param value the value
     */
    public Counter(int value) {
        this.value = value;
    }

    /**
     * Get current count.
     *
     * @return the value
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Increase.
     * add number to current count.
     *
     * @param number the number
     */
    public void increase(int number) {
        value += number;
    }

    /**
     * Decrease.
     * subtract number from current count.
     *
     * @param number the number
     */
    public void decrease(int number) {
        value -= number;
    }
}
