/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foreverhome;

/**
 *
 * @author yzape
 */
public class ForeverHomeController {
    private ForeverHomeModel model;
    private ForeverHomeView view;
    
    public ForeverHomeController(ForeverHomeModel mode, ForeverHomeView view){
        this.model = model;
        this.view = view;
    }
    
    public void start(){
        view.display();
//        view.showLoadingPanel();
//        simulateLoading();
//        view.showStartGamePanel();
        view.showLoginPanel();
        
    }
    
    private void simulateLoading(){
        try{
            Thread.sleep(22000);
        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
