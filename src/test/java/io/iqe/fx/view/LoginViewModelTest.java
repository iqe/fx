package io.iqe.fx.view;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;

import de.saxsys.mvvmfx.utils.notifications.NotificationCenter;
import io.iqe.fx.biz.FxService;

public class LoginViewModelTest {
	private NotificationCenter bus;
	private FxService service;
	private LoginViewModel model;
	
	@Before
	public void setUp() {
		bus = mock(NotificationCenter.class);
		service = mock(FxService.class);
		model = new LoginViewModel(bus, service);
	}
	
	@Test
	public void shouldDisableLoginAtStartup() throws Exception {
		assertThat(model.loginDisabledProperty().get(), is(true));
	}
	
	@Test
	public void shouldEnableLoginAsSoonAsAStationNumberIsPresent() throws Exception {
		model.stationNumberProperty().set("a");
		assertThat(model.loginDisabledProperty().get(), is(false));
	}
	
	@Test
	public void shouldCallServiceOnLogin() throws Exception {
		// given
		model.stationNumberProperty().set("abc");
		
		// when
		model.onLogin();
		
		// then
		verify(service).login("abc");
	}
	
	@Test
	public void shouldSendEventOnSuccessfulLogin() throws Exception {
		// given
		when(service.login("abc")).thenReturn(true);
		model.stationNumberProperty().set("abc");
		
		// when
		model.onLogin();
		
		// then
		verify(bus).publish(MainViewModel.EVENT_CHANGE_VIEW, MainViewModel.WORKLIST_VIEW);
	}
	
	@Test
	public void shouldNotSendEventOnFailedLogin() throws Exception {
		// given
		when(service.login("abc")).thenReturn(false);
		model.stationNumberProperty().set("abc");
		
		// when
		model.onLogin();
		
		// then
		verifyZeroInteractions(bus);
	}
}
