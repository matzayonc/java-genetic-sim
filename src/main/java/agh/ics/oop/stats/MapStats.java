package agh.ics.oop.stats;

import agh.ics.oop.Direction;
import agh.ics.oop.life.Genome;
import agh.ics.oop.map.AbstractMap;

public class MapStats {
    int animalCount;
    int grassCount;
    int freeFieldsCount;
    float avgEnergy;
    float avgLifespan;
    int deadAnimalsCount;
    Genome bestGenes;

    public void update(AbstractMap map) {
        animalCount = map.getAnimalCount();
        grassCount = map.getGrassCount();
        freeFieldsCount = map.getEmpty();
        avgEnergy = (float)map.getTotalEnergy() / (float)animalCount;
        avgLifespan = (float)map.totalLifespan() / (float)deadAnimalsCount;
        deadAnimalsCount = map.deadAnimals();
        bestGenes = map.getDominantGene();
    }

    public String getAnimalCount() {
        return Integer.toString(animalCount);
    }

    public String getGrassCount() {
        return Integer.toString(grassCount);
    }

    public String getFreeFieldsCount() {
        return Integer.toString(freeFieldsCount);
    }

    public String getAvgEnergy() {
        return Float.toString(avgEnergy);
    }

    public String getAvgLifespan() {
        return Float.toString(avgLifespan);
    }

    public String getDeadAnimalsCount() {
        return Integer.toString(deadAnimalsCount);
    }

    public String getDominatingGene() {
        return bestGenes.toString();
    }
}
