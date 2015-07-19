package io.iqe.fx.view;

import javax.inject.Inject;

import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.utils.notifications.NotificationCenter;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MainViewModel implements ViewModel {
	public static final String EVENT_CHANGE_VIEW = "ChangeView";
	public static final String LOGIN_VIEW = "LoginView";
	public static final String WORKLIST_VIEW = "WorklistView";

	@Inject
	NotificationCenter bus;
	
	private SimpleStringProperty visibleView;

	public MainViewModel() {
		visibleView = new SimpleStringProperty();
	}
	
	public StringProperty visibleViewProperty() {
		return visibleView;
	}

	public void startup() {
		bus.subscribe(EVENT_CHANGE_VIEW, (key, payload) -> switchView(payload));
		visibleView.set(LOGIN_VIEW);
	}
	
	private void switchView(Object[] payload) {
		if (payload == null || payload.length != 1) {
			System.out.println("Received unexpected payload " + payload);
			return;
		}
		
		String view = payload[0].toString();
		visibleView.set(view);
	}
}
