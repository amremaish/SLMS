/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network.serversocket;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import network.serversocket.Listener.FromClientMessageListener;
import network.shared.models.Message;
import network.shared.models.SlideNumber;
import network.shared.models.root;
import network.clientsocket.Models.FromServerQuiz;
import network.clientsocket.Models.FromServerSignIn;
import network.clientsocket.Models.FromServerSignUpStatus;
import network.serversocket.Listener.FromClientLoginListener;
import network.serversocket.Listener.FromClientQuizAnsListener;
import network.serversocket.Listener.FromClientSignUpListener;
import network.serversocket.Models.FromClientLogin;
import network.serversocket.Models.FromClientQuizAns;
import network.serversocket.Models.FromClientSignUp;

/**
 *
 * @author Amr
 */
public class ServerSocket {

    private Server server;
    private FromClientMessageListener FromClientMessageListener;
    private FromClientQuizAnsListener FromClientQuizAnsListener;
    private FromClientLoginListener FromClientLoginListener;
    private FromClientSignUpListener FromClientSignUpListener;

    private HashMap<String, Connection> clientsMap;

    public ServerSocket() {
        server = new Server();
        clientsMap = new HashMap<>();
        initializeRegisters();

    }

    private void initializeRegisters() {
        Kryo kryo = server.getKryo();
        kryo.register(ArrayList.class);
        kryo.register(Message.class);
        kryo.register(SlideNumber.class);
        kryo.register(FromServerQuiz.class);
        kryo.register(root.class);
        kryo.register(FromClientSignUp.class);
        kryo.register(FromClientLogin.class);
        kryo.register(FromServerSignUpStatus.class);
        kryo.register(FromServerSignIn.class);
        kryo.register(FromClientQuizAns.class);
    }

    public void Start() {
        try {
            server.start();
            server.bind(54555, 54777);
        } catch (IOException ex) {
            Logger.getLogger(ServerSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        StartReceiving();
        System.out.println("Server is started..");
    }

    private void StartReceiving() {
        server.addListener(new Listener() {
            @Override
            public void received(Connection connection, Object object) {
                if (object instanceof FromClientLogin) {
                    FromClientLogin info = (FromClientLogin) object;
                    clientsMap.put(info.getUsername(), connection);
                    int status = FromClientLoginListener.FromClientLogin(info);
                    FromServerSignIn signin = new FromServerSignIn();
                    signin.setStatus(status);
                    if (status != 1) {
                        clientsMap.remove(info.getUsername());
                    }
                    connection.sendTCP(signin);
                } else if (object instanceof FromClientSignUp) {
                    FromClientSignUp info = (FromClientSignUp) object;
                    FromClientSignUpListener.FromClientSignUp(info);
                    clientsMap.put(info.getUsername(), connection);
                } else if (object instanceof Message) {
                    Message info = (Message) object;
                    FromClientMessageListener.FromClientMessage(info);
                } else if (object instanceof FromClientQuizAns) {
                    FromClientQuizAns qa = (FromClientQuizAns) object;
                    FromClientQuizAnsListener.FromClientQuizAns(qa);
                }
            }
        });
    }

    public boolean SendTCP(root root, String username) {
        Connection con = clientsMap.get(username);
        if (disconnectUser(username)) {
            return false;
        }
        con.sendTCP(root);
        return true;
    }

    public void SendToAllTCP(root root) {
        server.sendToAllTCP(root);
    }

    public boolean disconnectUser(String username) {
        if (clientsMap.get(username) == null) {
            return true;
        }
        if (!clientsMap.get(username).isConnected()) {
            clientsMap.remove(username);
            return true;
        }
        return false;
    }

    public void sendSlideNumber(SlideNumber number) {
        server.sendToAllTCP(number);
    }

    public HashMap<String, Connection> getClientsMap() {
        return clientsMap;
    }

    public void setFromClientMessageListener(FromClientMessageListener FromClientMessageListener) {
        this.FromClientMessageListener = FromClientMessageListener;
    }

    public void setFromClientQuizAnsListener(FromClientQuizAnsListener FromClientQuizAnsListener) {
        this.FromClientQuizAnsListener = FromClientQuizAnsListener;
    }

    public void setFromClientLoginListener(FromClientLoginListener FromClientLoginListener) {
        this.FromClientLoginListener = FromClientLoginListener;
    }

    public void setFromClientSignUpListener(FromClientSignUpListener FromClientSignUpListener) {
        this.FromClientSignUpListener = FromClientSignUpListener;
    }

}
