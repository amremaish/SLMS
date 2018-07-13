/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network.clientsocket;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import network.shared.models.Message;
import network.shared.models.SlideNumber;
import network.shared.models.root;
import network.clientsocket.Listener.FromServerMessageListener;
import network.clientsocket.Listener.FromServerQuizListener;
import network.clientsocket.Listener.FromServerSignInListener;
import network.clientsocket.Listener.FromServerSignUpStatusListener;
import network.clientsocket.Listener.FromServerSlideNumberListener;
import network.clientsocket.Models.FromServerQuiz;
import network.clientsocket.Models.FromServerSignIn;
import network.clientsocket.Models.FromServerSignUpStatus;
import network.serversocket.Models.FromClientLogin;
import network.serversocket.Models.FromClientQuizAns;
import network.serversocket.Models.FromClientSignUp;

/**
 *
 * @author Amr
 */
public class ClientSocket {

    private final String address = "192.168.1.60";
    private Client client;
    private FromServerMessageListener ServerMessageListener;
    private FromServerSlideNumberListener ServerSlideNumberListener;
    private FromServerSignUpStatusListener FromServerSignUpStatusListener;
    private FromServerSignInListener FromServerSignInListener;
    private FromServerQuizListener FromServerQuizListener;

    public ClientSocket() {
        client = new Client();
        initializeRegisters();
    }

    private void initializeRegisters() {
        Kryo kryo = client.getKryo();
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

    public void Start() throws IOException {

        client.start();
        client.connect(5000, address, 54555, 54777);
        StartReciving();
    }

    private void StartReciving() {

        client.addListener(new Listener() {
            @Override
            public void received(Connection connection, Object object) {

                if (object instanceof SlideNumber) {
                    SlideNumber slide = (SlideNumber) object;
                    ServerSlideNumberListener.FromServerSlideNumber(slide);
                } else if (object instanceof Message) {
                    Message msg = (Message) object;
                    ServerMessageListener.FromServerMessage(msg);
                } else if (object instanceof FromServerSignUpStatus) {
                    FromServerSignUpStatus Status = (FromServerSignUpStatus) object;
                    FromServerSignUpStatusListener.FromServerSignUpStatus(Status);
                } else if (object instanceof FromServerSignIn) {
                    FromServerSignIn Status = (FromServerSignIn) object;
                    FromServerSignInListener.FromServerSignInListener(Status);
                } else if (object instanceof FromServerQuiz) {
                    FromServerQuiz quiz = (FromServerQuiz) object;
                    FromServerQuizListener.FromServerQuiz(quiz);
                }
            }
        });
    }

    public boolean isConnected() {
        return client.isConnected();
    }

    public void SendTCP(root root) {
        client.sendTCP(root);
    }

    public void setServerMessageListener(FromServerMessageListener ServerMessageListener) {
        this.ServerMessageListener = ServerMessageListener;
    }

    public void setServerSlideNumberListener(FromServerSlideNumberListener ServerSlideNumberListener) {
        this.ServerSlideNumberListener = ServerSlideNumberListener;
    }

    public void setFromServerSignUpStatusListener(FromServerSignUpStatusListener FromServerSignUpStatusListener) {
        this.FromServerSignUpStatusListener = FromServerSignUpStatusListener;
    }

    public void setFromServerSignInListener(FromServerSignInListener FromServerSignInListener) {
        this.FromServerSignInListener = FromServerSignInListener;
    }

    public void setFromServerQuizListener(FromServerQuizListener FromServerQuizListener) {
        this.FromServerQuizListener = FromServerQuizListener;
    }

}
