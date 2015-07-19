package io.iqe.fx.view;

import java.net.URL;
import java.util.ResourceBundle;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginView implements FxmlView<LoginViewModel>, Initializable {
	@FXML
	private TextField tfStationNumber;
	
	@FXML
	private Button btLogin;
	
	@InjectViewModel
	private LoginViewModel viewModel;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btLogin.disableProperty().bind(viewModel.loginDisabledProperty());
		viewModel.stationNumberProperty().bind(tfStationNumber.textProperty());
		btLogin.setDefaultButton(true);
	}
	
	@FXML
	void onLogin(ActionEvent e) {
		viewModel.onLogin();
	}
}
