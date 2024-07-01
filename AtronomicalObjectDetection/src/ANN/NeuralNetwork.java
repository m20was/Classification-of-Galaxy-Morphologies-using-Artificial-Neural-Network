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
public class NeuralNetwork implements Serializable{

    public SingleLayer layers[];
    public int totalLayers;
    public double learningRate;
    public int neuronInEachLayers[];

    public NeuralNetwork(double learningRate, int totalLayers, int neuronInEachLayers[]) {
        this.totalLayers = totalLayers;
        this.neuronInEachLayers = neuronInEachLayers;
        this.learningRate = learningRate;

        layers = new SingleLayer[totalLayers];
        for (int i = 0; i < totalLayers; i++) {
            if (i != totalLayers - 1) {
                layers[i] = new SingleLayer(neuronInEachLayers[i], neuronInEachLayers[i + 1]);
            } else {
                layers[i] = new SingleLayer(neuronInEachLayers[i], 0);
            }
        }
    }

    public void setInputs(double inputs[]) {
        for (int i = 0; i < neuronInEachLayers[0]; i++) {
            layers[0].neurons[i].value = inputs[i];
        }
    }

    double limiter(double x) {
        return (1.0 / (1 + Math.exp(-x)));
    }

    public double[] runNetwork() {
        double outputs[];
        int i, j, k;
        outputs = new double[neuronInEachLayers[totalLayers - 1]]; // Temp ouput array

        for (i = 1; i < totalLayers; i++) {
            for (j = 0; j < neuronInEachLayers[i]; j++) {
                layers[i].neurons[j].value = 0;
                for (k = 0; k < neuronInEachLayers[i - 1]; k++) {
                    layers[i].neurons[j].value = layers[i].neurons[j].value + layers[i - 1].neurons[k].value * layers[i - 1].neurons[k].weights[j]; //Multiply and add all the inputs
                }
                layers[i].neurons[j].value = layers[i].neurons[j].value + layers[i].neurons[j].bias; //Add bias
                layers[i].neurons[j].value = limiter(layers[i].neurons[j].value);  // Squash that value
            }
        }
        for (i = 0; i < neuronInEachLayers[totalLayers - 1]; i++) {
            outputs[i] = layers[totalLayers - 1].neurons[i].value;
        }
        return outputs; //Return the outputs
    }

    double sigmaWeightDelta(int layer_no, int neuron_no) { //Calculate sum of weights * delta. Used in back prop. layer_no is layer number. Layer number and neuron number can be zero
        double result = 0;
        for (int i = 0; i < neuronInEachLayers[layer_no + 1]; i++) { //Go through all the neurons in the next layer
            result = result + layers[layer_no].neurons[neuron_no].weights[i] * layers[layer_no + 1].neurons[i].delta; //Comput the summation
        }
        return result;
    }

    public void train(double inputs[], double outputs[]) { //The standard Backprop Learning algorithm
        int i, j, k;
        double target, actual, delta;
        setInputs(inputs); //Set the inputs
        runNetwork(); //Update all the values

        for (i = totalLayers - 1; i > 0; i--) { //Go from last layer to first layer
            for (j = 0; j < neuronInEachLayers[i]; j++) {//Go thru every neuron
                if (i == totalLayers - 1) { //Output layer, Needs special atential
                    target = outputs[j]; //Target value
                    actual = layers[i].neurons[j].value; //Actual value
                    delta = (target - actual) * actual * (1 - actual); //Function to compute error
                    layers[i].neurons[j].delta = delta; //Compute the delta

                    for (k = 0; k < neuronInEachLayers[i - 1]; k++) {
                        layers[i - 1].neurons[k].weights[j] += delta * learningRate * layers[i - 1].neurons[k].value; //Calculate the new weights
                    }
                    layers[i].neurons[j].bias = layers[i].neurons[j].bias + delta * learningRate * 1; //n_value is always 1 for bias
                } else { // for all intermediate layers
                    //Target value
                    actual = layers[i].neurons[j].value; //Actual value
                    delta = actual * (1 - actual) * sigmaWeightDelta(i, j); //Function to compute error
                    for (k = 0; k < neuronInEachLayers[i - 1]; k++) {
                        layers[i - 1].neurons[k].weights[j] += delta * learningRate * layers[i - 1].neurons[k].value; //Calculate the new weights
                    }
                    layers[i].neurons[j].bias = layers[i].neurons[j].bias + delta * learningRate * 1; //n_value is always 1 for bias
                }
            }
        }
    }
}