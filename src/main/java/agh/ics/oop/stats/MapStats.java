package agh.ics.oop.stats;

import agh.ics.oop.Direction;
import agh.ics.oop.life.Genome;
import agh.ics.oop.map.AbstractMap;
import agh.ics.oop.settings.Settings;

import java.io.*;

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
        if (Float.isFinite(avgEnergy) && !Float.isNaN(avgEnergy))
            return Float.toString(avgEnergy);
        else
            return "-";
    }

    public String getAvgLifespan() {
        if (Float.isFinite(avgLifespan) && !Float.isNaN(avgLifespan))
            return Float.toString(avgLifespan);
        else
            return "-";
    }

    public String getDeadAnimalsCount() {
        return Integer.toString(deadAnimalsCount);
    }

    public String getDominatingGene() {
        return bestGenes.toString();
    }

    public void createSaveFile(String preset) throws IOException {
        Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("src/main/resources/stats/"+preset+".csv", true), "UTF-8"));
        writer.write("Animal count, Grass count, Free fields count, Dominating gene, Avg energy, Avg lifespan\n");
        writer.close();
    }

    public void save(String preset) throws IOException {
        Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("src/main/resources/stats/"+preset+".csv", true), "UTF-8"));
        writer.write(animalCount + "," + grassCount + "," + freeFieldsCount + "," + bestGenes.toString() + "," + avgEnergy + "," + avgLifespan + "\n");
        writer.close();
    }
}
