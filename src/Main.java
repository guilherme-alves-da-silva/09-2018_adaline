
public class Main {

    public static void main(String[] args) {
        Network n = new Network();

        Double[][] data = {
            {2.0, 1.0, 1.0, 1.0},
            {1.0, 1.0, 1.0, -1.0},
            {1.0, 0.0, 1.0, -1.0},
            {0.0, 1.0, 1.0, -1.0},
            {0.0, 0.0, 1.0, -1.0}
        };

        n.train(data);

        n.getOutput(data);
        n.getOutput(data);
        n.getOutput(data);
    }
}
