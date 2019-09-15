
import java.util.Random;

public class Neuron {

    private Random rand;
    public Double[] weight;
    public Double theta;

    public Neuron() {
        rand = new Random();
        theta = -1.0;
    }

    private Double activationFunction(Double u) {
        return u < 0 ? -1.0 : 1.0;
    }

    private Double inputFunction(Double[] input) {
        Double sum = 0.0;

        for (int i = 0; i < input.length - 1; i++) {
            sum += input[i] * weight[i];
        }

        return sum - theta;
    }

    public Double train(Double[] input) {
        //if it's a new neuron, get random weights
        if (weight == null) {
            weight = new Double[input.length - 1];

            for (int i = 0; i < weight.length; i++) {
                weight[i] = rand.nextDouble();
            }
        }

        Double u = inputFunction(input);

        //just printing some info
        System.out.println("    d: " + input[input.length - 1] + " ; u: " + u);
        System.out.print("    theta: " + theta + "\n    w: ");
        for (int i = 0; i < weight.length; i++) {
            System.out.print(weight[i] + "  ");
        }
        System.out.println();

        return u;
    }

    /**
     * only run this after training.
     *
     * @param input
     * @return "y"
     */
    public Double getOutput(Double[] input) {
        return activationFunction(inputFunction(input));
    }
}
