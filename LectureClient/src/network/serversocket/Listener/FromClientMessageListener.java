/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network.serversocket.Listener;

import network.shared.models.Message;

/**
 *
 * @author Amr
 */
public interface FromClientMessageListener {

    public void FromClientMessage(Message Message);
}
