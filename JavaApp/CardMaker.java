
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.*;



public class CardMaker extends JPanel {
    String defaultPath = "JavaApp/Build/Card_renders";

    public static void main(String argv[]) throws Exception {
        CardMaker cm = new CardMaker();
        for(Card c : cm.allCards){
            cm.exportCard(c);
        }
    }


    LinkedList<Card> allCards=    new LinkedList<Card>(Arrays.asList(
            new Stoat(),
            new Wolf(),
            new WolfCub(),
            new Adder(),
            new Alpha(),
            new Amalgam(),
            new Amoeba(),
            new WorkerAnt(),
            new AntQueen(),
            new Bat(),
            new Beaver(),
            new Bee(),
            new Beehive(),
            new Bloodhound(),
            new Bullfrog(),
            new CagedWolf(),
            new Cat(),
            new UndeadCat(),
            new Cockroach(),
            new Coyote(),
            new TheDaus(),
            new Tail(),
            new Elk(),
            new ElkFawn(),
            new FieldMice(),
            new Geck(),
            new BlackGoat(),
            new Grizzly(),
            new Child13(),
            new Kingfisher(),
            new CorpseMaggots(),
            new Magpie(),
            new Mantis(),
            new MantisGod(),
            new Mole(),
            new MoleMan(),
            new MooseBuck(),
            new StrangeLarva(),
            new StrangePupa(),
            new Mothman(),
            new PackMule(),
            new Opossum(),
            new RiverOtter(),
            new Ouroboros(),
            new PackRat(),
            new Porcupine(),
            new Pronghorn(),
            new Rabbit(),
            new RatKing(),
            new Rattler(),
            new Raven(),
            new RavenEgg(),
            new GreatWhite(),
            new Skink(),
            new WrigglingTail(),
            new Skunk(),
            new RiverSnapper(),
            new LongElk(),
            new Sparrow(),
            new BellTentacle(),
            new CardTentacle(),
            new MirrorTentacle(),
            new Squirrel(),
            new TailFeathers(),
            new FurryTail(),
            new WrigglingLeg(),
            new Urayuli(),
            new TurkeyVulture(),
            new Warren(),
            new RingWorm(),
            new GlitchedCard(),
            new Stump(),
            new Boulder(),
            new GrandFir(),
            new Dwayne(),
            new Debile()
            ));


       
    
            static final int CARD_WIDTH = 125;

            static final int CARD_HEIGHT = 190;



    private BufferedImage emptyImage = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);

    HashMap<String, BufferedImage> cardPortraits = new HashMap<>();
    BufferedImage cardBase = new BufferedImage(CARD_WIDTH, CARD_HEIGHT, BufferedImage.TYPE_INT_ARGB);
    BufferedImage cardWhite = new BufferedImage(CARD_WIDTH, CARD_HEIGHT, BufferedImage.TYPE_INT_ARGB);
    BufferedImage[] costs = { emptyImage,
            emptyImage,
            emptyImage,
            emptyImage,
            emptyImage };

    EnumMap<Sigils, BufferedImage> sigils = new EnumMap<>(Sigils.class);


    Font fontHeavyWeight;
    Font fontHeavyWeight_Stat;

    public void exportCard(Card c){
        try {
                System.out.println("Exporting: "+c.getTitle());
                File outputfile = new File(defaultPath+c.getTitle()+".png");
                ImageIO.write(renderCard(c), "png", outputfile);

        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    CardMaker() {

        
        
        try {
            File font_file = new File("JavaApp/resources/HEAVYWEI.TTF");
            fontHeavyWeight = Font.createFont(Font.TRUETYPE_FONT, font_file).deriveFont(27f);
        } catch (Exception e) {
            e.printStackTrace();
            fontHeavyWeight = Font.getFont("Serif");
        }
        fontHeavyWeight_Stat = fontHeavyWeight.deriveFont(42f);

        Graphics2D tempGR = cardWhite.createGraphics();
        tempGR.setColor(Color.decode("#F0A966"));
        tempGR.drawRect(0, 0, CARD_WIDTH, CARD_HEIGHT);

        try {
            cardBase = ImageIO.read(new File("JavaApp/resources/card_empty.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        

        importPortraits("JavaApp/resources/Portraits");
        importCosts("JavaApp/resources/Costs");
        importSigils("JavaApp/resources/Sigils");


    }


    Color maroon = new Color(120, 30, 0);
    Color greenatk = new Color(40, 100, 50);// seafoam

    public BufferedImage renderCard(Card c) {

        BufferedImage cardImage = new BufferedImage(CARD_WIDTH, CARD_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D) cardImage.getGraphics();
        if (c == null) {
            return cardImage;
        } else {

            

            FontMetrics metrics = getFontMetrics(fontHeavyWeight);
            FontMetrics metrics2 = getFontMetrics(fontHeavyWeight_Stat);

            g.drawImage(cardBase, null, 0, 0);// draw background

            g.setColor(Color.BLACK);
            g.setFont(fontHeavyWeight);

            if ((CARD_WIDTH * 9 / 10 < metrics.stringWidth(c.getTitle()))) {
                Font newfont = fontHeavyWeight.deriveFont((22f * CARD_WIDTH) / metrics.stringWidth(c.getTitle()));
                metrics = getFontMetrics(newfont);
                g.setFont(newfont);
            }
            // g.drawString(c.getTitle(), (CARD_WIDTH - metrics.stringWidth(c.getTitle())) /
            // 2, metrics.getHeight());//draw title
            // g.drawString(c.getTitle(), (CARD_WIDTH - metrics.stringWidth(c.getTitle())) /
            // 2, (CARD_HEIGHT*42)/190-metrics.getHeight()/2);//draw title
            g.drawString(c.getTitle(), (CARD_WIDTH - metrics.stringWidth(c.getTitle())) / 2,
                    (CARD_HEIGHT * 14) / 190 + metrics.getHeight() / 2);// draw title
            // System.out.println(metrics.getHeight()/2+":"+(CARD_HEIGHT*42)/190);

            BufferedImage portrait = getCardPortrait(c.getTitle().replaceAll("Elder ", ""));
            g.drawImage(portrait, null, CARD_WIDTH / 2 - portrait.getWidth() / 2,
                    CARD_HEIGHT * 8 / 19 - portrait.getHeight() / 2);// draw portrait

            BufferedImage cost = getCostIndicator(c.cost);
            g.drawImage(cost, null, (CARD_WIDTH - cost.getWidth()), CARD_HEIGHT / 13);// draw cost indicator
            // g.drawImage(cost,null,0,0);//draw cost indicator

            BufferedImage sigil = getSigilImage(c.sigils);
            g.drawImage(sigil, null, (CARD_WIDTH - sigil.getWidth()) / 2,
                    (CARD_HEIGHT * 25 / 30) - sigil.getHeight() / 2);// draw cost indicator
            // g.drawImage(cost,null,0,0);//draw cost indicator

            // opacity = 0.5f;
            // g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));

            // if((HAND_CARDS_DRAW_MODE/2)%2== 1&&c.isFromHand()){
            //     // g.drawImage(cardWhite,null,0,0);//draw background

            //     g.setColor(Color.decode("#F0A966"));
            //     g.fillRect(0, 0, CARD_WIDTH, CARD_HEIGHT);

            // }
            // if(HAND_CARDS_DRAW_MODE%2==0&&c.isFromHand()){
            //     BufferedImage cardImage2 = new BufferedImage(cardImage.getWidth(), cardImage.getHeight(), BufferedImage.TYPE_INT_RGB);
            //     Graphics2D g2 = (Graphics2D) cardImage2.getGraphics();
            //     g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, HAND_CARD_OPACITY));
            //     g2.drawImage(cardImage, null, 0, 0);
            //     g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));

            //     cardImage = cardImage2;
            //     g = g2;
            // }
            g.setFont(fontHeavyWeight_Stat);
            g.setColor(Color.BLACK);
            if (c.getAttack() != c.getBaseAttack()) {
                g.setColor(greenatk);
            }
            g.drawString("" + c.getAttack(), CARD_WIDTH * 20 / 120 - metrics2.stringWidth(c.getAttack() + "") / 2, CARD_HEIGHT * 91 / 120 + metrics2.getHeight() / 2);// draw attack

            g.setColor(Color.BLACK);
            if (c.getHealth() != c.getBaseHealth()) {
                g.setColor(maroon);
            }
            g.drawString(c.getHealth() + "", (CARD_WIDTH * 100 / 120 - metrics2.stringWidth(c.getHealth() + "") / 2),CARD_HEIGHT * 5 / 6 + metrics2.getHeight() / 2);// draw health

            return cardImage;
        }

    }

    public BufferedImage getSigilImage(Sigils sig) {
        BufferedImage sigil = sigils.get(sig);
        return sigil == null ? emptyImage : sigil;
    }

    public BufferedImage getSigilImage(EnumMap<Sigils, Boolean> sigs) {
        BufferedImage sigil = null;
        for (Sigils s : sigs.keySet()) {
            if (sigs.get(s)) {
                sigil = sigils.get(s);
            }
        }
        return sigil == null ? emptyImage : sigil;
    }

    public BufferedImage getCardPortrait(String name) {
        BufferedImage portrait = cardPortraits.get(name.toUpperCase());
        return portrait == null ? emptyImage : portrait;
    }

    public BufferedImage getCostIndicator(int cost) {
        if (cost < costs.length) {
            return costs[cost];
        }
        return emptyImage;
    }

    public void importPortraits(String folderPath) {

        try {
            File folder = new File(folderPath);
            File[] listOfFiles = folder.listFiles();

            for (int i = 0; i < listOfFiles.length; i++) {
                try {
                    if (listOfFiles[i].isFile()) {
                        String fileName = listOfFiles[i].getName();
                        fileName = (String) fileName.subSequence("portait_".length() + 1,
                                fileName.length() - ".png".length());

                        BufferedImage portrait = null;
                        try {
                            portrait = ImageIO.read(listOfFiles[i]);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        cardPortraits.put(fileName.toUpperCase(), portrait);

                    } else if (listOfFiles[i].isDirectory()) {
                        System.out.println("Directory " + listOfFiles[i].getName());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void importSigils(String folderPath) {

        try {
            File folder = new File(folderPath);
            File[] listOfFiles = folder.listFiles();

            for (int i = 0; i < listOfFiles.length; i++) {
                try {
                    if (listOfFiles[i].isFile()) {
                        String fileName = listOfFiles[i].getName();
                        fileName = (String) fileName.subSequence("ability_".length(),
                                fileName.length() - ".png".length());

                        BufferedImage sigil = null;
                        try {
                            sigil = ImageIO.read(listOfFiles[i]);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        System.out.println("Importing file: " + fileName);
                        for (Sigils s : Sigils.values()) {
                            System.out.println("       checking: " + s.name());
                            if (s.name().equalsIgnoreCase(fileName)) {
                                sigils.put(s, sigil);

                                System.out.println("       SUCCEEDED");

                            }
                        }

                    } else if (listOfFiles[i].isDirectory()) {
                        System.out.println("Directory " + listOfFiles[i].getName());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void importCosts(String folderPath) {

        try {
            File folder = new File(folderPath);
            File[] listOfFiles = folder.listFiles();

            for (int i = 0; i < listOfFiles.length; i++) {
                try {
                    if (listOfFiles[i].isFile()) {
                        String fileName = listOfFiles[i].getName();

                        fileName = (String) fileName.subSequence("cost_".length(), "cost_".length() + 1);

                        BufferedImage portrait = null;
                        try {
                            int cost = Integer.parseInt(fileName);
                            portrait = ImageIO.read(listOfFiles[i]);
                            costs[cost] = portrait;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (listOfFiles[i].isDirectory()) {
                        System.out.println("Directory " + listOfFiles[i].getName());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}