package io.iqe.fx.view;

import javax.inject.Inject;

import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.utils.notifications.NotificationCenter;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class WorklistViewModel implements ViewModel {
	private StringProperty scannedValue;
	private BooleanProperty scanAllowed;
	private StringProperty message;
	
	private NotificationCenter bus;
	
	@Inject
	public WorklistViewModel(NotificationCenter bus) {
		this.bus = bus;
		scannedValue = new SimpleStringProperty();
		scanAllowed = new SimpleBooleanProperty();
		message = new SimpleStringProperty();
		
		scanAllowed.bind(scannedValue.isEmpty().not());
	}
	
	public StringProperty scannedValueProperty() {
		return scannedValue;
	}
	
	public BooleanProperty scanAllowedProperty() {
		return scanAllowed;
	}
	
	public StringProperty messageProperty() {
		return message;
	}

	public void onScan() {
		if ("1234".equals(scannedValue.get())) {
			bus.publish(MainViewModel.EVENT_CHANGE_VIEW, MainViewModel.LOGIN_VIEW);
		} else {
			message.set("Scanned: " + scannedValue.get());
		}
	}
}
