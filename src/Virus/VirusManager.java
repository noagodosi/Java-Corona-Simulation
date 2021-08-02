package Virus;


import java.util.ArrayList;
import java.util.Random;

public enum VirusManager{
    CHINES(new ChineseVariant(),"Chines Variant"),
    BRITISH(new BritishVariant(), "British Variant"),
    SOUTH_AFRICAN(new SouthAfricanVariant(),"South African");
    private IVirus virus;
    private String name;
    private ArrayList<IVirus> developmentVariant;

    VirusManager(IVirus virus,String name){
        this.virus=virus;
        this.name=name;
        this.developmentVariant=new ArrayList<>();
    }

    public static IVirus getRandomDevelopmentOf(IVirus virus){
        if (virus== BRITISH.getVirus())
            return BRITISH.randomVariant();
        if(virus== CHINES.getVirus())
            return CHINES.randomVariant();
        else if(virus==SOUTH_AFRICAN.getVirus())
            return SOUTH_AFRICAN.randomVariant();
        return null;
    }

    public boolean CheckIfExist(IVirus virus){
        if(developmentVariant.contains(virus)){
            return true;
        }
        return false;
    }

    public IVirus getVirus(){return virus;}

    public String getName(){return name;}

    public void addVariant(IVirus virus){
        if(this.developmentVariant.contains(virus)){
            //System.out.println(this.getName()+": "+this.developmentVariant.size());
            return;
        }
        this.developmentVariant.add(virus);
        System.out.println(this.getName()+": "+this.developmentVariant.size());
    }

    public void removeVariant(IVirus virus){
        if(this.developmentVariant.contains(virus)){
            this.developmentVariant.remove(virus);
            System.out.println(this.getName()+": "+this.developmentVariant.size());
            return;
        }
    }

    public IVirus randomVariant() {
        Random randomV=new Random();
        if(this.developmentVariant.size()>0){
            int randomIndex=randomV.nextInt(this.developmentVariant.size());
            return this.developmentVariant.get(randomIndex);
        }
        return null;

    }

}
