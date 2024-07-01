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
public class HistoryDB implements Serializable{
    public Vector<HistorySingleRow> rows;

    public HistoryDB() {
      rows = new Vector<HistorySingleRow>();
    }
    
}
