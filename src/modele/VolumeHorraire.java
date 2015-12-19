package modele;

/**
 * Created by germa on 16/12/2015.
 */
public class VolumeHorraire {

    private int cmVolume;
    private int tdVolume;
    private int tpVolume;
    private int CCVolume;
    private int ETVolume;


    public VolumeHorraire(int cmVolume, int tdVolume, int tpVolume, int CCVolume, int ETVolume) {
        this.cmVolume = cmVolume;
        this.tdVolume = tdVolume;
        this.tpVolume = tpVolume;
        this.CCVolume = CCVolume;
        this.ETVolume = ETVolume;
    }

    public VolumeHorraire() {
        cmVolume = 0;
        tdVolume = 0;
        tpVolume = 0;
        CCVolume = 0;
        ETVolume = 0;
    }

    public int getCmVolume() {
        return cmVolume;
    }

    public int getTdVolume() {
        return tdVolume;
    }

    public int getTpVolume() {
        return tpVolume;
    }

    public int getCCVolume() {
        return CCVolume;
    }

    public int getETVolume() {
        return ETVolume;
    }

    public boolean cmPresent() {
        if (cmVolume > 0) {
            return true;
        }
        return false;

    }

    public boolean tdPresent() {
        if (tdVolume > 0) {
            return true;
        }
        return false;
    }

    public boolean tpPresent() {
        if (tpVolume > 0) {
            return true;
        }
        return false;
    }

    public boolean ccPresent() {
        if (CCVolume > 0) {
            return true;
        }
        return false;
    }

    public boolean etPresent() {
        if (ETVolume > 0) {
            return true;
        }
        return false;
    }

    public int getVariete() {
        int Variete = 0;
        if (cmVolume > 0) {
            Variete++;
        }
        if (tdVolume > 0) {
            Variete++;
        }
        if (tpVolume > 0) {
            Variete++;
        }
        if (CCVolume > 0) {
            Variete++;
        }
        if (ETVolume > 0) {
            Variete++;
        }

        return Variete;
    }

}
