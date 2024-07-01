/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ANN;

import java.io.Serializable;

/**
 *
 * @author Administrator
 */
public class SingleLayer implements Serializable {

    public SingleNeuron neurons[];

    public SingleLayer(int neuronsInCurrentLayer, int neuronsInNextLayer) {
        neurons = new SingleNeuron[neuronsInCurrentLayer];
        for (int i = 0; i < neuronsInCurrentLayer; i++) {
            neurons[i] = new SingleNeuron(neuronsInNextLayer);
        }
    }
}
