package com.example.comicfx;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Backgrounds {

    private int position;
    private String globalPath;
    private int n = 0;

    public Backgrounds(String globalPath, int position) {
        this.globalPath = globalPath;
        this.position = position;
    }

    public void run(int numberOfCharacters, int currentTextPosition, int distanceBetweenCharacters,int positionSave) throws IOException {

        File background = new File(globalPath + "\\Input\\background.jpg");
        //File background = new File("H:\\OnePics\\Single\\SWF\\Comics\\AI\\Input\\background.jpg");
        BufferedImage im = ImageIO.read(background);

        ArrayList<File> text = new ArrayList<>();
        File textD = new File(globalPath + "\\Input\\TextOutput");
        //File textD = new File("H:\\OnePics\\Single\\SWF\\Comics\\AI\\Input\\TextOutput");
        getAllFiles(textD, text);

        ArrayList<File> charactersF = new ArrayList<>();
        File characters = new File(globalPath + "\\Input\\Characters");
        //File characters = new File("H:\\OnePics\\Single\\SWF\\Comics\\AI\\Input\\Characters");
        getAllFiles(characters, charactersF);

        for (int i = 0; i < numberOfCharacters; i++) {
            im = stanz(im, charactersF.get(n), position * distanceBetweenCharacters, 70);
            position++;
            n++;
        }


        im = stanz(im, text.get(currentTextPosition), 0, 391);

        ImageIO.write(im, "png", new File(globalPath + "\\Output\\" + n / numberOfCharacters + ".png"));
        position = positionSave;
        //  ImageIO.write(im, "png", new File("H:\\OnePics\\Single\\SWF\\Comics\\AI\\Output\\" + n/numberOfCharacters + ".png"));
    }

    private BufferedImage stanz(BufferedImage im, File put, int distance, int heightDistance) throws IOException {
        try {
            BufferedImage im2 = ImageIO.read(put);
            Graphics2D g = im.createGraphics();
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
            g.drawImage(im2, (im.getWidth() - im2.getWidth()) / 2 - distance,
                    (im.getHeight() - im2.getHeight()) / 2 + heightDistance, null);
            g.dispose();
        } catch (NullPointerException ignored) {

        }
        return im;
    }

    public void getAllFiles(File file, ArrayList<File> list) throws IOException {
        if (file.isDirectory()) {
            File[] subFiles = file.listFiles();
            if (subFiles == null) {
                return;

            }

            for (File subFile : subFiles) {
                getAllFiles(subFile, list);
            }
        } else {
            list.add(file);

        }
    }
}