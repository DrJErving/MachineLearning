import java.util.Random;

public class NeuralNetworkNoMatrix {
    public static void main(String[] args) {
        double[][] arregloXor = {
                {0, 0, 0},
                {0, 1, 1},
                {1, 0, 1},
                {1, 1, 0}
        };
        //inputs
        double input1 = 0;
        double input2 = 0;

        //outputs
        double expectedOutput = 0;

        double learningRate = 0.05;

        //error
        double errorOutput = 0;

        double h1 = 0;
        double h2 = 0;
        double o1 = 0;

        //initialize the weights
        Random r = new Random();
        double input1H1 = r.nextDouble() * 2 - 1;
        double input2H1 = r.nextDouble() * 2 - 1;
        double input1H2 = r.nextDouble() * 2 - 1;
        double input2H2 = r.nextDouble() * 2 - 1;
        double H1O1 = r.nextDouble() * 2 - 1;
        double H2O1 = r.nextDouble() * 2 - 1;
        double biasNeuronH1 = r.nextDouble() * 2 - 1;
        double biasNeuronH2 = r.nextDouble() * 2 - 1;
        double biasOutput = r.nextDouble() * 2 - 1;

        int iteration = 1;
        for (int i = 0; i < 5000; i++) {
            //assign input and expected values
            input1 = arregloXor[i % 4][0];
            input2 = arregloXor[i % 4][1];
            expectedOutput = arregloXor[i % 4][2];

            //Forward Propagation
            //calculate values for hidden layer
            h1 = input1 * input1H1 + input2 * input2H1 + biasNeuronH1;
            h2 = input1 * input1H2 + input2 * input2H2 + biasNeuronH2;

            //apply sigmoid function for activation
            h1 = tanh(h1);
            h2 = tanh(h2);

            //calculate output
            o1 = h1 * H1O1 + h2 * H2O1 + biasOutput;

            //apply sigmoid function
            o1 = tanh(o1);

            //Back Propagation
            //Error of output neuron
            errorOutput = expectedOutput - o1;

            //Gradient for output neuron
            double gradient = tanhDerivative(o1); //derivative of activation function
            gradient = gradient * errorOutput; //times error calculated
            gradient = gradient * learningRate; //times learning rate

            //delta for weights incoming to output neuron
            double deltaNeuronH1ToOutput = h1 * gradient;
            double deltaNeuronH2ToOutput = h2 * gradient;

            //adjust weights
            H1O1 = H1O1 + deltaNeuronH1ToOutput;
            H2O1 = H2O1 + deltaNeuronH2ToOutput;
            //adjust bias for output neuron
            biasOutput = biasOutput + gradient;

            //target for neurons
            double targetH1 = H1O1 * errorOutput + h1;
            double targetH2 = H2O1 * errorOutput + h2;

            //Calculate error for hidden layer
            double errorH1 = targetH1 - h1;
            double errorH2 = targetH2 - h2;

            double gradientH1 = tanhDerivative(h1);
            double gradientH2 = tanhDerivative(h2);

            gradientH1 = gradientH1 * errorH1;
            gradientH2 = gradientH2 * errorH2;

            gradientH1 = gradientH1 * learningRate;
            gradientH2 = gradientH2 * learningRate;

            double deltaInput1H1 = gradientH1 * input1;
            double deltaInput2H1 = gradientH1 * input2;
            double deltaInput1H2 = gradientH2 * input1;
            double deltaInput2H2 = gradientH2 * input2;

            //Adjust weights for Neuron 1
            input1H1 = input1H1 + deltaInput1H1;
            input2H1 = input2H1 + deltaInput2H1;
            biasNeuronH1 = biasNeuronH1 + gradientH1;

            //Adjust weights for Neuron 2
            input1H2 = input1H2 + deltaInput1H2;
            input2H2 = input2H2 + deltaInput2H2;
            biasNeuronH2 = biasNeuronH2 + gradientH2;

            if (i % 4 == 0) {
                System.out.println("--------------SET: "+ iteration + " --------------------");
                iteration++;
            }
            System.out.println("Iteration: " + i);
            System.out.println(input1 + " XOR " + input2 + " = " + o1);
        }
    }

    public static double sigmoid(double value) {
        return 1 / (1 + Math.exp(-value));
    }

    public static double sigmoidDerivative(double value) {
        return value * (1 - value);
    }

    public static double tanh(double value) {
        return Math.tanh(value);
    }

    public static double tanhDerivative(double value) {
        return 1- value * value;
    }
}
