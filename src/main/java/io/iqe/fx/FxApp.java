package io.iqe.fx;

import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import de.saxsys.mvvmfx.cdi.MvvmfxCdiApplication;
import io.iqe.fx.view.MainView;
import io.iqe.fx.view.MainViewModel;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FxApp extends MvvmfxCdiApplication {
	public static void main(String[] args) {
		FxApp.launch(args);
	}
	
	@Override
	public void startMvvmfx(Stage stage) throws Exception {
		stage.setTitle("FX");
		
		ViewTuple<MainView, MainViewModel> mainViewTuple = FluentViewLoader.fxmlView(MainView.class).load();
		
	    Parent root = mainViewTuple.getView();
	    stage.setScene(new Scene(root));
	    
		stage.show();
	}
}
