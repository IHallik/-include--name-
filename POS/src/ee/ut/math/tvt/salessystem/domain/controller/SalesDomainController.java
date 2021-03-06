package ee.ut.math.tvt.salessystem.domain.controller;

import java.util.List;

import ee.ut.math.tvt.salessystem.domain.data.HistoryItem;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.domain.exception.VerificationFailedException;

/**
 * Sales domain controller is responsible for the domain specific business
 * processes.
 */
public interface SalesDomainController {
	
	/**
     * Add a confirmation status listener to the SalesComainController
     */
	public void addConfirmationStatusListener(ConfirmationStatusEvent listener);
	
	public void addDataChangedListener(DataChangedEvent listener);

    /**
     * Load the current state of the warehouse.
     * 
     * @return List of ${link
     *         ee.ut.math.tvt.salessystem.domain.data.StockItem}s.
     */
    public List<StockItem> loadWarehouseState();

    // business processes
    /**
     * Initiate new business transaction - purchase of the goods.
     * 
     * @throws VerificationFailedException
     */
    public void startNewPurchase() throws VerificationFailedException;

    /**
     * Rollback business transaction - purchase of goods.
     * 
     * @throws VerificationFailedException
     */
    public void cancelCurrentPurchase() throws VerificationFailedException;

    /**
     * Commit business transaction - purchase of goods.
     * 
     * @param goods
     *            Goods that the buyer has chosen to buy.
     * @throws VerificationFailedException
     */
    public void submitCurrentPurchase(List<SoldItem> goods)
            throws VerificationFailedException;

    /**
     *  Closes the connection to the database
     */
    public void endSession();
	
    /**
     * Load the list of purchases.
     * 
     * @return List of ${link
     *         ee.ut.math.tvt.salessystem.domain.data.HistoryItem}s.
     */
	public List<HistoryItem> getSales();
	
	/**
     * Load the list of sold items.
     * 
     * @return List of ${link
     *         ee.ut.math.tvt.salessystem.domain.data.HistoryItem}s.
     */
	public List<SoldItem> getSoldItems();

	/**
     * Add or update the corresponding StockItem
     */
	public void addStock(StockItem Product);
}
