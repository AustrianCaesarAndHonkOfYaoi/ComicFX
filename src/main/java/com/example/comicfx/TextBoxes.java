package com.example.comicfx;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TextBoxes {

    private ArrayList<String> chat = new ArrayList<>();
    private ArrayList<File> alibi = new ArrayList<>();
    private String globalPath;
    private int position;

    public TextBoxes(String globalPath, int position) {
        this.globalPath = globalPath;
        this.position = position;
    }

    public void getTextContent() throws IOException {
        FileInputStream inputStream = null;
        Scanner sc = null;
        try {
            inputStream = new FileInputStream(globalPath + "\\Input\\Day.txt");
            //inputStream = new FileInputStream("H:\\OnePics\\Single\\SWF\\Comics\\AI\\Input\\Day.txt");
            sc = new Scanner(inputStream, "UTF-8");
            while (sc.hasNextLine()) {
                chat.add(sc.nextLine());

            }
            // note that Scanner suppresses exceptions
            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (sc != null) {
                sc.close();
            }
        }

    }


    public void run() throws IOException {
        Backgrounds c = new Backgrounds(globalPath, position);
        File t = new File(globalPath + "\\Input\\Text");
        //  File t = new File("H:\\OnePics\\Single\\SWF\\Comics\\AI\\Input\\Text");

        c.getAllFiles(t, alibi);

        for (int j = 0; j < alibi.size(); j++) {
            runPress(j);
        }
    }

    public void runPress(int j) throws IOException {
        String partI = "";
        String partII = "";
        Boolean line = false;
        for (int l = 0; l < chat.get(j).length(); l++) {

            if (chat.get(j).charAt(l) == '/') {

                partI = chat.get(j).substring(0, (l - 1));
                partII = chat.get(j).substring((l + 2));
                line = true;


            }


        }

        BufferedImage im = ImageIO.read(alibi.get(j));
        Font font = new Font("Halogen", Font.PLAIN, 33);

        Graphics g = im.getGraphics();
        g.setFont(font);
        if (line) {
            g.drawString(partI, 252, 175);
            g.drawString(partII, 252, 208);
        } else {
            g.drawString(chat.get(j), 252, 175);
        }
        g.dispose();

        String attachment = "";
        if (j < 9) {
            attachment = "0";
        }
        j++;
        ImageIO.write(im, "png", new File(globalPath + "\\Input\\TextOutput\\" + attachment + j + ".png"));
        //   ImageIO.write(im, "png", new File("H:\\OnePics\\Single\\SWF\\Comics\\AI\\Input\\TextOutput\\" + attachment + j + ".png"));
        j--;
    }


}









