/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network.serversocket.Listener;

import network.serversocket.Models.FromClientSignUp;



/**
 *
 * @author Amr
 */
public interface FromClientSignUpListener {

    /**
     *
     * @param signup
     * @return if we return 1 mean he is accept if we return 2 mean he is reject
     * if we return 3 mean his username is duplicate
     */
    public void FromClientSignUp(FromClientSignUp signup);
}
