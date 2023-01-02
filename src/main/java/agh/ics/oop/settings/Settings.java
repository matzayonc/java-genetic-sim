package agh.ics.oop.settings;

import agh.ics.oop.Vector2d;

import java.io.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Settings {
    private Vector2d mapSize = new Vector2d(5, 4);
    private MapVariant mapVariant = MapVariant.EARTH;
    private int startPlantCount = 5;
    private int energyPerPlant = 1;
    private int plantsPerCycle = 1;
    private GrowthVariant growthVariant = GrowthVariant.EQUATOR;
    private int startAnimalCount = 5;
    private int startEnergy = 10;
    private int fertileEnergy = 5;
    private int reproductionEnergy = 5;
    private int minimumChildEnergy = 1;
    private int maximumChildEnergy = 3;
    private MutationVariant mutationVariant = MutationVariant.FULL_VARIABILITY;
    private int genomeLength = 5;
    private BehaviourVariant behaviourVariant = BehaviourVariant.PREDICTABLE;

    static public String path = "src/main/resources/settings";

    public Vector2d getMapSize() {
        return mapSize;
    }

    public MapVariant getMapVariant() {
        return mapVariant;
    }

    public int getEnergyPerPlant() {
        return energyPerPlant;
    }

    public int getPlantsPerCycle() {
        return plantsPerCycle;
    }

    public GrowthVariant getGrowthVariant() {
        return growthVariant;
    }

    public int getStartAnimalCount() {
        return startAnimalCount;
    }

    public int getFertileEnergy() {
        return fertileEnergy;
    }

    public int getReproductionEnergy() {
        return reproductionEnergy;
    }

    public int getMinimumMutations() {
        return minimumChildEnergy;
    }

    public int getMaximumMutations() {
        return maximumChildEnergy;
    }

    public MutationVariant getMutationVariant() {
        return mutationVariant;
    }

    public int getGenomeLength() {
        return genomeLength;
    }

    public BehaviourVariant getBehaviourVariant() {
        return behaviourVariant;
    }


    public String toString() {
        String[] strings = {
            "Map size: " + mapSize.toString(),
            "Map variant: " + mapVariant.toString(),
            "Start plant count: " + startPlantCount,
            "Energy per plant: " + energyPerPlant,
            "Plants per cycle: " + plantsPerCycle,
            "Growth variant: " + growthVariant.toString(),
            "Start animal count: " + startAnimalCount,
            "Start energy: " + startEnergy,
            "Fertile energy: " + fertileEnergy,
            "Reproduction energy: " + reproductionEnergy,
            "Minimum child energy: " + minimumChildEnergy,
            "Maximum child energy: " + maximumChildEnergy,
            "Mutation variant: " + mutationVariant.toString(),
            "Genome length: " + genomeLength,
            "Behaviour variant: " + behaviourVariant.toString()
        };

        return String.join("\n", strings);
    }

    public static Settings fromString(String input){
        String[] strings = input.split("\n");

        Settings settings = new Settings();

        for (String string : strings) {
            if(string.length() == 0) continue;
            String[] keyValue = string.split(": ");
            String key = keyValue[0];
            String value = keyValue[1];

            switch (key) {
                case "Map size" -> {
                    int[] cords = Arrays.stream(value.split("x")).mapToInt(Integer::parseInt).toArray();
                    settings.mapSize = new Vector2d(cords[0], cords[1]);
                }
                case "Map variant" -> settings.mapVariant = MapVariant.fromString(value);
                case "Start plant count" -> settings.startPlantCount = Integer.parseInt(value);
                case "Energy per plant" -> settings.energyPerPlant = Integer.parseInt(value);
                case "Plants per cycle" -> settings.plantsPerCycle = Integer.parseInt(value);
                case "Growth variant" -> settings.growthVariant = GrowthVariant.fromString(value);
                case "Start animal count" -> settings.startAnimalCount = Integer.parseInt(value);
                case "Start energy" -> settings.startEnergy = Integer.parseInt(value);
                case "Fertile energy" -> settings.fertileEnergy = Integer.parseInt(value);
                case "Reproduction energy" -> settings.reproductionEnergy = Integer.parseInt(value);
                case "Minimum child energy" -> settings.minimumChildEnergy = Integer.parseInt(value);
                case "Maximum child energy" -> settings.maximumChildEnergy = Integer.parseInt(value);
                case "Mutation variant" -> settings.mutationVariant = MutationVariant.fromString(value);
                case "Genome length" -> settings.genomeLength = Integer.parseInt(value);
                case "Behaviour variant" -> settings.behaviourVariant = BehaviourVariant.fromString(value);
                default -> throw new IllegalArgumentException("Unknown key");
            }
        }

        return settings;
    }

//    public void dump(String path) throws IOException {
//        PrintWriter writer = new PrintWriter(Settings.toPath(path), StandardCharsets.UTF_8);
//        writer.println(this.toString());
//        writer.close();
//    }

    public static Settings load(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(Settings.toPath(filename)));
        String read;
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            read = sb.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            br.close();
        }
        return Settings.fromString(read);
    }

    static public Set<String> list() {
        File file = new File(Settings.path);

        return Stream.of(Objects.requireNonNull(file.listFiles()))
                .map(File::getName)
                .filter(s -> s.length() > 4)
                .map(s -> s.substring(0, s.length()-4))
                .collect(Collectors.toSet());
    }

    static private String toPath(String filename) {
        return Settings.path + "/" + filename + ".txt";
    }
}
