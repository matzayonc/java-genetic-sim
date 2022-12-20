package agh.ics.oop.life;

import agh.ics.oop.Direction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Genome {
    private final int geneCount;
    private List<Direction> genes = new ArrayList<>();

    private int activeGene;

    public Genome(int geneCount) {
        this.geneCount = geneCount;
        this.activeGene = (int)Math.random() * geneCount;

        for (int i = 0; i < geneCount; i++) {
            genes.add(Direction.random());
        }
    }

    public int getActiveGene() {
        return activeGene;
    }

    public int getAndUpdateActiveGene() {
        int gene = activeGene;
        activeGene = (activeGene + 1) % geneCount;
        return gene;
    }
}
