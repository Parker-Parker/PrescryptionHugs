public class Lib extends Card {

}
// anything -1 is something to check later

class Stoat extends Card {

    public Stoat() {
        super("Stoat", 1, 3, 1, "Blood", "Canine", null, null, "JavaApp/resources/Portraits/portait_stoat.jpg");
    }

}

class Wolf extends Card {

    public Wolf() {
        super("Wolf", 3, 2, 2, "Blood", "Canine", null, null, "JavaApp/resources/Portraits/portait_wolf.jpg");
    }

}

class WolfCub extends Card {

    public WolfCub() {
        super("Wolf Cub", 1, 1, 1, "Blood", "Canine", "Fledgling", null,
                "JavaApp/resources/Portraits/portait_wolf_cub.jpg");
    }
    @Override
    public Card makeCopy() {
        return makeCopy(new WolfCub());
    }
    @Override
    public Card getEvolution() {
        if(checkSigil(Sigils.Fledgling)){
            this.title = "Wolf" ;
            this.baseHealth += 1;
            this.health += 1;
            this.baseAttack += 2;
            this.attack += 2;
            // this.isFromHand = false;//so we draw over the old card
            this.sigils.put(Sigils.Fledgling, false);
            this.sigils.put(Sigils.PlayedFromHand, false);
        }
        return this;
        
    }

}

class Adder extends Card {

    public Adder() {
        super("Adder", 1, 1, 2, "Blood", "Reptile", "DeathTouch", "Kills Survivors",
                "JavaApp/resources/Portraits/portait_adder.jpg");
    }

}

class Alpha extends Card {
// changed the cost number to 2 and the ctype to blood for the time being/demo
    public Alpha() {
        super("Alpha", 1, 2, 2, "Blood", "Canine", "Leader", null, "JavaApp/resources/Portraits/portait_alpha.jpg");
    }

}

class Amalgam extends Card {

    public Amalgam() {
        super("Amalgam", 3, 3, 2, "Blood", "Avian, Canine, Hooved, Reptile, Insect, Squirrel", null, null,
                "JavaApp/resources/Portraits/portait_amalgam.jpg");
    }

}

class Amoeba extends Card {

    public Amoeba() {
        super("Amoeba", 1, 2, 2, "Bones", null, "Amorphous", null, "JavaApp/resources/Portraits/portait_amoeba.jpg");
    }

}

class WorkerAnt extends Card {

    public WorkerAnt() {
        super("Worker Ant", 2, 1, 1, "Blood", "Insect", null, "Ant",
                "JavaApp/resources/Portraits/portait_worker_ant.jpg");
    }

}

class AntQueen extends Card {

    public AntQueen() {
        super("Ant Queen", 3, 2, 2, "Blood", "Insect", "Ant Spawner", "Ant",
                "JavaApp/resources/Portraits/portait_ant_queen.jpg");
    }

}

class Bat extends Card {

    public Bat() {
        super("Bat", 2, 1, 4, "Bones", null, "Airborne", null, "JavaApp/resources/Portraits/portait_bat.jpg");
    }

}

class Beaver extends Card {

    public Beaver() {
        super("Beaver", 1, 4, 2, "Blood", null, "Dam Builder", null, "JavaApp/resources/Portraits/portait_beaver.jpg");
    }

}

class Bee extends Card {

    public Bee() {
        super("Bee", 1, 1, 0, null, "Insect", "Airborne", null, "JavaApp/resources/Portraits/portait_bee.jpg");
    }

}

class Beehive extends Card {

    public Beehive() {
        super("Beehive", 0, 2, 1, "Blood", "Insect", "Bees Within", null,
                "JavaApp/resources/Portraits/portait_beehive.jpg");
    }

}

class Bloodhound extends Card {

    public Bloodhound() {
        super("Bloodhound", 2, 3, 2, "Blood", "Canine", "Guardian", null,
                "JavaApp/resources/Portraits/portait_bloodhound.jpg");
    }

}

class Bullfrog extends Card {

    public Bullfrog() {
        super("Bullfrog", 1, 2, 1, "Blood", "Reptile", "MightyLeap", null,
                "JavaApp/resources/Portraits/portait_bullfrog.jpg");
    }

}

class CagedWolf extends Card {

    public CagedWolf() {
        super("Caged Wolf", 0, 6, 2, "Blood", "Canine", null, "Terrain, No Deathcard",
                "JavaApp/resources/Portraits/portait_caged_wolf.jpg");
    }

}

class Cat extends Card {

    public Cat() {
        super("Cat", 0, 1, 1, "Blood", null, "Many Lives", null, "JavaApp/resources/Portraits/portait_cat.jpg");
    }

}

class UndeadCat extends Card {

    public UndeadCat() {
        super("Undead Cat", 3, 6, 1, "Blood", null, null, null, "JavaApp/resources/Portraits/portait_undead_cat.jpg");
    }

}

class Cockroach extends Card {

    public Cockroach() {
        super("Cockroach", 1, 1, 4, "Bones", "Insect", "Unkillable", null,
                "JavaApp/resources/Portraits/portait_cockroach.jpg");
    }

}

class Coyote extends Card {

    public Coyote() {
        super("Coyote", 2, 1, 4, "Bones", "Canine", null, null, "JavaApp/resources/Portraits/portait_coyote.jpg");
    }

}

class TheDaus extends Card {

    public TheDaus() {
        super("The Daus", 2, 2, 2, "Blood", null, "Bellist", null, "JavaApp/resources/Portraits/portait_the_daus.jpg");
    }

}

class Tail extends Card {

    public Tail() {
        super("Tail", 0, 2, 0, null, null, null, null, "JavaApp/resources/Portraits/portait_tail.jpg");
    }

}

class Elk extends Card {

    public Elk() {
        super("Elk", 2, 4, 2, "Blood", "Hooved", "Sprinter", null, "JavaApp/resources/Portraits/portait_elk.jpg");
    }

}

class ElkFawn extends Card {

    public ElkFawn() {
        super("Elk Fawn", 1, 1, 1, "Blood", "Hooved", "Sprinter", "Fledgling",
                "JavaApp/resources/Portraits/portait_elk_fawn.jpg");
    }
    @Override
    public Card makeCopy() {
        return makeCopy(new ElkFawn());
    }

    @Override
    public Card getEvolution() {
        if(checkSigil(Sigils.Fledgling)){
            this.title = "Elk" ;
            this.baseHealth += 3;
            this.health += 3;
            this.baseAttack += 1;
            this.attack += 1;
            // this.isFromHand = false;//so we draw over the old card
            this.sigils.put(Sigils.Fledgling, false);
            this.sigils.put(Sigils.PlayedFromHand, false);
        }
        return this;
        
    }

}

class FieldMice extends Card {

    public FieldMice() {
        super("Field Mice", 2, 2, 2, "Blood", null, "Fecundity", null,
                "JavaApp/resources/Portraits/portait_field_mice.jpg");
    }

}

class Geck extends Card {

    public Geck() {
        super("Geck", 1, 1, 0, null, "Reptile", null, null, "JavaApp/resources/Portraits/portait_geck.jpg");
    }

}

class BlackGoat extends Card {

    public BlackGoat() {
        super("Black Goat", 0, 1, 1, "Blood", "Hooved", "Worthy Sacrifice", "Goat",
                "JavaApp/resources/Portraits/portait_black_goat.jpg");
    }

}

class Grizzly extends Card {

    public Grizzly() {
        super("Grizzly", 4, 6, 3, "Blood", null, null, null, "JavaApp/resources/Portraits/portait_grizzly.jpg");
    }

}

class Child13 extends Card {

    public Child13() {
        super("Child 13", 0, 1, 1, "Blood", "Hooved", "Many Lives", null,
                "JavaApp/resources/Portraits/portait_child_13.jpg");
    }

}

class Kingfisher extends Card {

    public Kingfisher() {
        super("Kingfisher", 1, 1, 1, "Blood", "Avian", "Airborne", "Waterborne",
                "JavaApp/resources/Portraits/portait_kingfisher.jpg");
    }

}

class CorpseMaggots extends Card {

    public CorpseMaggots() {
        super("Corpse Maggots", 1, 2, 5, "Bones", "Insect", "Corpse Eater", null,
                "JavaApp/resources/Portraits/portait_corpse_maggots.jpg");
    }

}

class Magpie extends Card {

    public Magpie() {
        super("Magpie", 1, 1, 2, "Blood", "Avian", "Airborne", "Hoarder",
                "JavaApp/resources/Portraits/portait_magpie.jpg");
    }

}

class Mantis extends Card {

    public Mantis() {
        super("Mantis", 1, 1, 1, "Blood", "Insect", "BifurcatedStrike", null,
                "JavaApp/resources/Portraits/portait_mantis.jpg");
    }

}

class MantisGod extends Card {

    public MantisGod() {
        super("Mantis God", 1, 1, 1, "Blood", "Insect", "Trifurcated Strike", null,
                "JavaApp/resources/Portraits/portait_mantis_god.jpg");
    }

}

class Mole extends Card {

    public Mole() {
        super("Mole", 0, 4, 1, "Blood", null, "Burrower", null, "JavaApp/resources/Portraits/portait_mole.jpg");
    }

}

class MoleMan extends Card {

    public MoleMan() {
        super("Mole Man", 0, 6, 1, "Blood", null, "Burrower", "MightyLeap",
                "JavaApp/resources/Portraits/portait_mole_man.jpg");
    }

}

class MooseBuck extends Card {

    public MooseBuck() {
        super("Moose Buck", 3, 7, 3, "Blood", "Hooved", "Hefty", null,
                "JavaApp/resources/Portraits/portait_moose_buck.jpg");
    }

}

class StrangeLarva extends Card {

    public StrangeLarva() {
        super("Strange Larva", 0, 3, 1, "Blood", "Insect", "Fledgling", null,
                "JavaApp/resources/Portraits/portait_strange_larva.jpg");
    }
    @Override
    public Card makeCopy() {
        return makeCopy(new StrangeLarva());
    }

    @Override
    public Card getEvolution() {
        if(checkSigil(Sigils.Fledgling)){
            StrangePupa p =  new StrangePupa();
            p.baseAttack += this.baseAttack;
            p.baseHealth += this.baseHealth-3;
            p.health = this.health;
            p.setField(this.field);
            return p;
        }
        return this;
        
    }

}

class StrangePupa extends Card {

    public StrangePupa() {
        super("Strange Pupa", 0, 3, 1, "Blood", "Insect", "Fledgling", null,
                "JavaApp/resources/Portraits/portait_strange_pupa.jpg");
    }
    @Override
    public Card makeCopy() {
        return makeCopy(new StrangePupa());
    }
    @Override
    public Card getEvolution() {
        if(checkSigil(Sigils.Fledgling)){
            Mothman m =  new Mothman();
            m.baseAttack += this.baseAttack;
            m.baseHealth += this.baseHealth-3;
            m.health = m.baseHealth-(this.baseHealth-this.health);
            
            m.setField(this.field);
            return m;
        }
        return this;
        
    }

}

class Mothman extends Card {

    public Mothman() {
        super("Mothman", 7, 3, 1, "Blood", "Insect", "Airborne", null,
                "JavaApp/resources/Portraits/portait_mothman.jpg");
    }

}

class PackMule extends Card {

    public PackMule() {
        super("Pack Mule", 0, 5, 0, null, "Hooved", "Sprinter", "Uncuttable",
                "JavaApp/resources/Portraits/portait_pack_mule.jpg");
    }

}

class Opossum extends Card {

    public Opossum() {
        super("Opossum", 1, 1, 2, "Bones", null, null, null, "JavaApp/resources/Portraits/portait_opossum.jpg");
    }

}

class RiverOtter extends Card {

    public RiverOtter() {
        super("River Otter", 1, 1, 1, "Blood", null, "Waterborne", null,
                "JavaApp/resources/Portraits/portait_river_otter.jpg");
    }

}

class Ouroboros extends Card {

    public Ouroboros() {
        super("Ouroboros", 1, 1, 2, "Blood", "Reptile", "Unkillable", "Satisfies Ring Trial",
                "JavaApp/resources/Portraits/portait_ouroboros.jpg");
    }

}

class PackRat extends Card {

    public PackRat() {
        super("Pack Rat", 2, 2, 2, "Blood", null, "Trinket Bearer", null,
                "JavaApp/resources/Portraits/portait_pack_rat.jpg");
    }

}

class Porcupine extends Card {

    public Porcupine() {
        super("Porcupine", 1, 2, 1, "Blood", null, "Sharp Quills", null,
                "JavaApp/resources/Portraits/portait_porcupine.jpg");
    }

}

class Pronghorn extends Card {

    public Pronghorn() {
        super("Pronghorn", 1, 3, 2, "Blood", "Hooved", "Sprinter", "BifurcatedStrike",
                "JavaApp/resources/Portraits/portait_pronghorn.jpg");
    }

}

class Rabbit extends Card {

    public Rabbit() {
        super("Rabbit", 0, 1, 0, null, null, null, null, "JavaApp/resources/Portraits/portait_rabbit.jpg");
    }

}

class RatKing extends Card {

    public RatKing() {
        super("Rat King", 2, 1, 2, "Blood", null, "Bone King", null,
                "JavaApp/resources/Portraits/portait_rat_king.jpg");
    }

}

class Rattler extends Card {

    public Rattler() {
        super("Rattler", 3, 1, 6, "Bones", "Reptile", null, null, "JavaApp/resources/Portraits/portait_rattler.jpg");
    }

}

class Raven extends Card {

    public Raven() {
        super("Raven", 2, 3, 2, "Blood", "Avian", "Airborne", null, "JavaApp/resources/Portraits/portait_raven.jpg");
    }

}

class RavenEgg extends Card {

    public RavenEgg() {
        super("Raven Egg", 0, 2, 1, "Blood", "Avian", "Fledgling", null,
                "JavaApp/resources/Portraits/portait_raven_egg.jpg");
    }
    @Override
    public Card getEvolution() {
        if(checkSigil(Sigils.Fledgling)){
            this.title = "Elk" ;
            this.baseHealth += 1;
            this.health += 1;
            this.baseAttack += 2;
            this.attack += 2;
            // this.isFromHand = false;//so we draw over the old card
            this.sigils.put(Sigils.Fledgling, false);
            this.sigils.put(Sigils.PlayedFromHand, false);
            this.sigils.put(Sigils.Airborne, true);
        }
        return this;
        
    }

    
}

class GreatWhite extends Card {

    public GreatWhite() {
        super("Great White", 4, 2, 3, "Blood", null, "Waterborne", null,
                "JavaApp/resources/Portraits/portait_great_white.jpg");
    }

}

class Skink extends Card {

    public Skink() {
        super("Skink", 1, 2, 1, "Blood", "Reptile", "Loose Tail", null,
                "JavaApp/resources/Portraits/portait_skink.jpg");
    }

}

class WrigglingTail extends Card {

    public WrigglingTail() {
        super("Wriggling Tail", 0, 2, 0, null, "Reptile", null, null,
                "JavaApp/resources/Portraits/portait_wriggling_tail.jpg");
    }

}

class Skunk extends Card {

    public Skunk() {
        super("Skunk", 0, 3, 1, "Blood", null, "Stinky", null, "JavaApp/resources/Portraits/portait_skunk.jpg");
    }

}

class RiverSnapper extends Card {

    public RiverSnapper() {
        super("River Snapper", 1, 6, 2, "Blood", "Reptile", null, null,
                "JavaApp/resources/Portraits/portait_river_snapper.jpg");
    }

}

class LongElk extends Card {

    public LongElk() {
        super("Long Elk", 1, 2, 4, "Bones", "Hooved", "Sprinter", "DeathTouch",
                "JavaApp/resources/Portraits/portait_long_elk.jpg");
    }

}

class Sparrow extends Card {

    public Sparrow() {
        super("Sparrow", 1, 2, 1, "Blood", "Avian", "Airborne", null,
                "JavaApp/resources/Portraits/portait_sparrow.jpg");
    }

}

class BellTentacle extends Card {
    // bell ringer
    public BellTentacle() {
        super("Bell Tentacle", -1, 3, 2, "Blood", null, null, null,
                "JavaApp/resources/Portraits/portait_bell_tentacle.jpg");
    }

}

class CardTentacle extends Card {
    // card counter
    public CardTentacle() {
        super("Card Tentacle", -1, 1, 1, "Blood", null, null, null,
                "JavaApp/resources/Portraits/portait_card_tentacle.jpg");
    }

}

class MirrorTentacle extends Card {
    // M!rror r0rriM
    public MirrorTentacle() {
        super("Mirror Tentacle", -1, 3, 1, "Blood", null, null, null,
                "JavaApp/resources/Portraits/portait_mirror_tentacle.jpg");
    }

}

class Squirrel extends Card {

    public Squirrel() {
        super("Squirrel", 0, 1, 0, null, "Squirrel", null, null, "JavaApp/resources/Portraits/portait_squirrel.jpg");
    }

}

class TailFeathers extends Card {

    public TailFeathers() {
        super("Tail Feathers", 0, 2, 0, null, null, null, null,
                "JavaApp/resources/Portraits/portait_tail_feathers.jpg");
    }

}

class FurryTail extends Card {

    public FurryTail() {
        super("Furry Tail", 0, 2, 0, null, null, null, null, "JavaApp/resources/Portraits/portait_furry_tail.jpg");
    }

}

class WrigglingLeg extends Card {

    public WrigglingLeg() {
        super("Wriggling Leg", 0, 2, 0, null, null, null, null,
                "JavaApp/resources/Portraits/portait_wriggling_leg.jpg");
    }

}

class Urayuli extends Card {

    public Urayuli() {
        super("Urayuli", 7, 7, 4, "Blood", null, null, null, "JavaApp/resources/Portraits/portait_urayuli.jpg");
    }

}

class TurkeyVulture extends Card {

    public TurkeyVulture() {
        super("Turkey Vulture", 3, 3, 8, "Bones", "Avian", "Airborne", null,
                "JavaApp/resources/Portraits/portait_turkey_vulture.jpg");
    }

}

class Warren extends Card {

    public Warren() {
        super("Warren", 0, 2, 1, "Blood", null, "Rabbit Hole", null, "JavaApp/resources/Portraits/portait_warren.jpg");
    }

}

class RingWorm extends Card {

    public RingWorm() {
        super("Ring Worm", 0, 1, 1, "Blood", "Insect", null, "Kills Survivors, Satisfies Ring Trial",
                "JavaApp/resources/Portraits/portait_ring_worm.jpg");
    }

}

class GlitchedCard extends Card {

    public GlitchedCard() {
        super("Glitched Card", 0, 0, 0, null, null, null, null,
                "JavaApp/resources/Portraits/portait_glitched_card.jpg");
    }

}

class Stump extends Card {

    public Stump() {
        super("Stump", 0, 3, 0, null, null, null, null, "JavaApp/resources/Portraits/portait_stump.jpg");
    }

}

class Boulder extends Card {

    public Boulder() {
        super("Boulder", 0, 5, 0, null, null, null, null, "JavaApp/resources/Portraits/portait_boulder.jpg");
    }

}

class GrandFir extends Card {

    public GrandFir() {
        super("Grand Fir", 0, 5, 0, null, null, "MightyLeap", null, "JavaApp/resources/Portraits/portait_boulder.jpg");
    }

}

class Dwayne extends Card {

    public Dwayne() {
        super("Dwayne", 0, 69, 0, null, null, "Repulsive", null, "JavaApp/resources/Portraits/portait_boulder.jpg");
    }

}

class Debile extends Card {

    public Debile() {
        super("Debile", 1, 3, 0, null, null, null, null, "JavaApp/resources/Portraits/portait_Debile.jpg");
    }

}
class Carkin extends Card {

    public Carkin() {
        super("Carkin", 2, 1, 1, null, null, null, null, "JavaApp/resources/Portraits/portait_Debile.jpg");
    }

}
class EricCarkin extends Card {

    public EricCarkin() {
        super("Eric Carkin", 3, 3, 2, null, null, "SharpQuills", null, "JavaApp/resources/Portraits/portait_Debile.jpg");
    }

}
class Krakin147 extends Card {

    public Krakin147() {
        super("Krakin 147", 14, 7, 4, null, null, "BifurcatedStrike",null , "JavaApp/resources/Portraits/portait_Debile.jpg");
    }

}
