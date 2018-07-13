/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network.clientsocket.Models;

import network.shared.models.root;

/**
 *
 * @author Amr
 */
public class FromServerSignUpStatus extends root {

    public static final int REJECT = 2, ACCPET = 1;
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
