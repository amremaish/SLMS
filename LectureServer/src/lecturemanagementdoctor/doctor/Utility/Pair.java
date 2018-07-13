/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lecturemanagementdoctor.doctor.Utility;

/**
 *
 * @author Amr
 */
public class Pair<E, Y> {

    private E first;

    private Y second;

    public Pair(E first, Y second) {
        this.first = first;
        this.second = second;
    }
    
    

    public E getFirst() {
        return first;
    }

    public void setFirst(E first) {
        this.first = first;
    }

    public Y getSecond() {
        return second;
    }

    public void setSecond(Y second) {
        this.second = second;
    }

}
