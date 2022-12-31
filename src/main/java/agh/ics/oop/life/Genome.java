package agh.ics.oop.life;

import agh.ics.oop.Direction;
import agh.ics.oop.settings.Settings;

import java.util.ArrayList;
import java.util.List;

public class Genome {
    private final int geneCount;
    private List<Direction> genes = new ArrayList<>();

    private int activeGene;

    public Genome(int geneCount) {
        this.geneCount = geneCount;
        for (int i = 0; i < geneCount; i++) {
            genes.add(Direction.random());
        }

        setToRandom();
    }

    public Genome(List<Direction> genes) {
        this.geneCount = genes.size();
        this.genes = genes;
        setToRandom();
    }

    public void setToRandom() {
        this.activeGene = (int) (Math.random() * geneCount);
    }

    public void nextGene() {
        activeGene = (activeGene + 1) % geneCount;
    }

    public Direction getActiveGene() {
        return genes.get(activeGene);
    }

    public Genome combine(Genome other, int proportion, Settings settings) {
        List<Direction> combination = genes.subList(0, proportion);
        combination.addAll(other.genes.subList(proportion, other.geneCount));

        System.out.println("combination: " + combination +
                "left: " + genes.subList(0, proportion) +
                "right: " + other.genes.subList(proportion, other.geneCount)+
                "proportion: " + proportion+
                "l:"+ genes.size()+ ", r: " + other.genes.size());


        int c = 0;
        while ((Math.random() < 0.6 || c++ < settings.getMinimumMutations()) && c < settings.getMaximumMutations()) {
            int index = (int) (Math.random() * geneCount);
            Direction value = switch (settings.getMutationVariant()) {
                case FULL_VARIABILITY -> Direction.random();
                case SLIGHT_CORRECTION -> genes.get(index).similar();
            };
            combination.set(index, value);
        }

        return new Genome(combination);
    }

    public String toString() {
        String result = "";
        for (Direction d : genes)
            result += Integer.toString(d.toIndex());
        return result;
    }
}
