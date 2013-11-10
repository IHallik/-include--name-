package ee.ut.math.tvt.salessystem.domain.controller;

public interface DataChangedEvent {
	public void DataChanged(String type) throws IllegalArgumentException;
}
