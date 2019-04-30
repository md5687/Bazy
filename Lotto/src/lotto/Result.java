/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lotto;

import java.util.LinkedList;

/**
 *
 * @author admin
 */
public class Result {
    
    private String id;
    private String llist;
    private String yes;
    
    public Result(int id, LinkedList<Integer> llist, int yes){
        this.id = Integer.toString(id);
        String temp = "";
        for(int i : llist){
            temp = temp + ", " + i;
        }
        this.llist = temp;
        this.yes = Integer.toString(yes);
    }
    
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public LinkedList<Integer> getLlist() {
//        return llist;
//    }
//
//    public void setLlist(LinkedList<Integer> llist) {
//        this.llist = llist;
//    }
//
//    public int getYes() {
//        return yes;
//    }
//
//    public void setYes(int yes) {
//        this.yes = yes;
//    }
    
    
    
}
