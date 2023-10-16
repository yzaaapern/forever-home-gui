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
//        view.display();
//        simulateLoading();
//        view.showStartGamePanel();
//        view.showLoginPanel();
        view.showFosterPanel();
        
    }
    
    private void simulateLoading(){
        try{
            Thread.sleep(14000);
        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
