
public class Network {

    public static final Double BIAS = -1.0;
    public static final Double LEARNING_RATE = 0.01;
    public static final Double E = 0.0001;
    private Neuron neuron_1_1; //_eachNeuron_layer
    public static int epochs;

    public Network() {
        neuron_1_1 = new Neuron();
        epochs = 0;
    }

    private Double conditionToStop(Double[][] input, Double[] u) {
        Double sum = 0.0;

        for (int i = 0; i < u.length; i++) {
            sum += Math.pow(input[i][input[0].length - 1] - u[i], 2);
        }

        Double result = sum / u.length;
        System.out.println("Eqm: " + result);

        return result;
    }

    private Double[] trainingLoop(Double[][] input) {
        Double[] u = new Double[input.length];

        for (int i = 0; i < input.length; i++) {
            System.out.println("\nepoch: " + epochs + " ; input[" + i + "]");
            u[i] = neuron_1_1.train(input[i]); //this should be put after the changes of the weights and theta, here just 6 lines down. because after you've trained it to get a certain result, if you change the weights and theta again you may not get the same result again.

            for (int j = 0; j < neuron_1_1.weight.length; j++) {
                neuron_1_1.weight[j] += LEARNING_RATE * (input[i][input[0].length - 1] - u[i]) * input[i][j];
            }
            neuron_1_1.theta += LEARNING_RATE * (input[i][input[0].length - 1] - u[i]) * BIAS;
        }
        epochs++;

        return u;
    }

    public void train(Double[][] input) {
        Double previousConditionIndex = conditionToStop(input, trainingLoop(input));
        Double currentConditionIndex = conditionToStop(input, trainingLoop(input));

        while (previousConditionIndex - currentConditionIndex > E) {
            previousConditionIndex = currentConditionIndex;
            currentConditionIndex = conditionToStop(input, trainingLoop(input));
            System.out.println("current Eqm - previous Eqm: " + (previousConditionIndex - currentConditionIndex) + "\n");
        }
    }

    public Double[] getOutput(Double[][] input) {
        Double[] y = new Double[input.length];

        for (int i = 0; i < input.length; i++) {
            y[i] = neuron_1_1.getOutput(input[i]);
        }

        //just printing the ys
        System.out.println("\nresults:");
        for (int i = 0; i < input.length; i++) {
            System.out.println("input[" + i + "]");
            for (int j = 0; j < input[0].length; j++) {
                System.out.print(input[i][j] + "  ");
            }
            System.out.println("; y: " + y[i] + "\n");
        }

        return y;
    }
}
