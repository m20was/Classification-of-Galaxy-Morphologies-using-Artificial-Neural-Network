/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ANN;

import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author Administrator
 */
public class SingleNeuron implements Serializable {

    double value;
    double bias;
    double delta; // required for back propogation.
    double weights[];

    public SingleNeuron(int totalOutgoingDendrites) {
        value = 0;
        bias = getRand();
        delta = 0;
        weights = new double[totalOutgoingDendrites];
        for (int i = 0; i < totalOutgoingDendrites; i++) {
            weights[i] = getRand();
        }
    }

    public double getRand() {
        Random r = new Random();
        return (1 - (r.nextDouble() * 2));
    }
}
