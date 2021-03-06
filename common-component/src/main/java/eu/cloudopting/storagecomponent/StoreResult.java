package eu.cloudopting.storagecomponent;

/**
 * Result object after a store is complete.
 *
 * @author Daniel P.
 */
public interface StoreResult {

    boolean isStored();

    void setStored(boolean stored);


}
