/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foreverhome;

/**
 *
 * @author yzape
 */
public class ForeverHomeMain {

    public static void main(String[] args) {
        ForeverHomeModel model = new ForeverHomeModel();
        ForeverHomeView view = new ForeverHomeView();
        ForeverHomeController controller = new ForeverHomeController(model, view);
        
        controller.start();
    }

}
