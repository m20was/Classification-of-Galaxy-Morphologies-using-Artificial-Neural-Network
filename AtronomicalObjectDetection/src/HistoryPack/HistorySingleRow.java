/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package HistoryPack;

import java.io.Serializable;
import java.util.Vector;

/**
 *
 * @author mypc
 */
public class HistorySingleRow implements Serializable{
   public Vector<String> singleRow;

    public HistorySingleRow() {
      singleRow= new Vector<String>();
    }
    
}
