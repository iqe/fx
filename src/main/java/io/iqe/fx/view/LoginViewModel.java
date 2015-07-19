package io.iqe.fx.view;

import javax.inject.Inject;

import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.utils.notifications.NotificationCenter;
import io.iqe.fx.biz.FxService;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LoginViewModel implements ViewModel {
	private StringProperty stationNumber;
	private BooleanProperty loginDisabled;
	
	private NotificationCenter bus;
	private	FxService service;
	
	@Inject
	public LoginViewModel(NotificationCenter bus, FxService service) {
		this.bus = bus;
		this.service = service;
		
		stationNumber = new SimpleStringProperty();
		loginDisabled = new SimpleBooleanProperty();

		loginDisabled.bind(stationNumber.isEmpty());
	}
	
	public StringProperty stationNumberProperty() {
		return stationNumber;
	}
	
	public BooleanProperty loginDisabledProperty() {
		return loginDisabled;
	}

	public void onLogin() {
		// FIXME service call should be done in background
		boolean success = service.login(stationNumber.get());
		if (success) {
			bus.publish(MainViewModel.EVENT_CHANGE_VIEW, MainViewModel.WORKLIST_VIEW);
		}
	}
}
