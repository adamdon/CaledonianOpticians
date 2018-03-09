package caledonianopticians;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

public class Controller
{
    View view;

    public Controller() 
    {
       view = new View();
       view.btnSearch.setOnAction(e -> handleBtnSearch());
    }
    
    public void handleBtnSearch()
    {
        System.out.println("Test Print BtnSearch");
        updateStatusBar();
    }
    
    public void  updateStatusBar()
    {
        view.lblStatusBarText.setText("Test: " + view.txtSearchTextField.getText());
    }
    
    public Scene getSceneFromView()
    {       
        return view.getScene();
    }
    
}
