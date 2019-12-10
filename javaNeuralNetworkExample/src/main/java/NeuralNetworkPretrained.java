import java.util.Random;
import java.util.Scanner;

public class NeuralNetworkPretrained {
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

        //Read the weights from the console
        System.out.println("Enter the values for the weights:");
        Scanner r = new Scanner(System.in);

        double input1H1 = r.nextDouble();
        double input2H1 = r.nextDouble();
        double input1H2 = r.nextDouble();
        double input2H2 = r.nextDouble();
        double H1O1 = r.nextDouble();
        double H2O1 = r.nextDouble();
        double biasNeuronH1 = r.nextDouble();
        double biasNeuronH2 = r.nextDouble();
        double biasOutput = r.nextDouble();

        int iteration = 1;
        int maxLoops = 4;
        for (int i = 0; i < maxLoops; i++) {
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
