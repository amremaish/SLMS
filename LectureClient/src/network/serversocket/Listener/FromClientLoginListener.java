package network.serversocket.Listener;

import network.serversocket.Models.FromClientLogin;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Amr
 */
public interface FromClientLoginListener {
        /**
     *
     * @param login
     * @return if we return 1 mean he will login if we return 2 mean the
     * password or username is incorrect
     */
    public int FromClientLogin(FromClientLogin login);
}
