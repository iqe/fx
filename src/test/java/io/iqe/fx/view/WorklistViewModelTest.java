package io.iqe.fx.view;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Before;
import org.junit.Test;

import de.saxsys.mvvmfx.utils.notifications.NotificationCenter;

public class WorklistViewModelTest {
	private NotificationCenter bus;
	private WorklistViewModel model;
	
	@Before
	public void setUp() throws Exception {
		bus = mock(NotificationCenter.class);
		model = new WorklistViewModel(bus);
	}
	
	@Test
	public void shouldDisableButtonAtStartup() throws Exception {
		assertThat(model.scanAllowedProperty().get(), is(false));
	}
	
	@Test
	public void shouldEnableButtonAtFirstLetter() throws Exception {
		model.scannedValueProperty().set("1");
		assertThat(model.scanAllowedProperty().get(), is(true));
	}
	
	@Test
	public void shouldReturnToLoginOn1234() throws Exception {
		// given
		model.scannedValueProperty().set("1234");
		
		// when
		model.onScan();
		
		// then
		verify(bus).publish(MainViewModel.EVENT_CHANGE_VIEW, MainViewModel.LOGIN_VIEW);
	}
	
	@Test
	public void shouldStayInViewForAnyOtherValue() throws Exception {
		// given
		model.scannedValueProperty().set("abc");
		
		// when
		model.onScan();
		
		// then
		verifyZeroInteractions(bus);
	}
}
