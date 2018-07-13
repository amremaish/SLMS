/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network.clientsocket.Models;

/**
 *
 * @author Amr
 */
public class FromServerSignIn {

    public static final int ACCEPT = 1, REJECT = 2, ALREADY_LOGGOED = 3;
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    
    

}
