// 322624206 Daniel Talker

package listeners;

/**
 * The HitNotifier interface represents an object that can notify listeners
 * about hit events.
 */
public interface HitNotifier {

    /**
     * Adds a HitListener to the list of listeners.
     *
     * @param hl The HitListener to be added
     */
    void addHitListener(HitListener hl);

    /**
     * Removes a HitListener from the list of listeners.
     *
     * @param hl The HitListener to be removed
     */
    void removeHitListener(HitListener hl);

}
