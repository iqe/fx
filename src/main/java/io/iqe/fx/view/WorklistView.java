package io.iqe.fx.view;

import java.net.URL;
import java.util.ResourceBundle;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class WorklistView implements FxmlView<WorklistViewModel>, Initializable {
	@FXML
	private TextField tfScan;
	
	@FXML
	private Button btScan;
	
	@FXML
	private Label lbScan;
	
	@InjectViewModel
	private WorklistViewModel viewModel;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btScan.disableProperty().bind(viewModel.scanAllowedProperty().not());
		lbScan.textProperty().bind(viewModel.messageProperty());
		tfScan.textProperty().bindBidirectional(viewModel.scannedValueProperty());
		
		btScan.setDefaultButton(true);
	}
	
	@FXML
	void onScan(ActionEvent e) {
		viewModel.onScan();
	}
}
