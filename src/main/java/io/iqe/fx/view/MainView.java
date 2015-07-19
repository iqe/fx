package io.iqe.fx.view;

import java.net.URL;
import java.util.ResourceBundle;

import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class MainView implements FxmlView<MainViewModel>, Initializable {
	
	@FXML
	private BorderPane mainPane;
	
	@InjectViewModel
	private MainViewModel viewModel;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {		
		viewModel.visibleViewProperty().addListener((observable, oldValue, newValue) -> replaceView(newValue));
		viewModel.startup();
	}
	
	private void replaceView(String newView) {
		Parent view = loadView(newView);
		mainPane.setCenter(view);
	}

	@SuppressWarnings("rawtypes")
	private Parent loadView(String newView) {
		ViewTuple tuple;
		Parent view;
		switch (newView) {
		case MainViewModel.LOGIN_VIEW:
			tuple = FluentViewLoader.fxmlView(LoginView.class).load();
			view = tuple.getView();
			break;
		case MainViewModel.WORKLIST_VIEW:
			tuple = FluentViewLoader.fxmlView(WorklistView.class).load();
			view = tuple.getView();
			break;
		default:
			view = null;
			break;
		}
		
		return view;
	}
}
