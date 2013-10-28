package ee.ut.math.tvt.salessystem.domain.controller;

public interface ConfirmationStatusEvent {
	void SaleConfirmed(boolean success);
}