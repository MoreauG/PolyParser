package modele;

/**
 * Classe representant la repartition horraire des enseignements et evaluations delivrees par des professeurs
 */
public class VolumeHorraire {

    private int cmVolume;
    private int tdVolume;
    private int tpVolume;
    private int ccVolume;
    private int etVolume;

    /**
     * Unique constructeur, permet d'instancier l'objet avec l'ensemble de ses composants
     *
     * @param cmVolume, le nombre d'heures de CM
     * @param tdVolume, le nombre d'heures de TD
     * @param tpVolume, le nombre d'heures de TP
     * @param ccVolume, le nombre d'heures de CC
     * @param etVolume, le nombre d'heures d'ET
     */
    public VolumeHorraire(int cmVolume, int tdVolume, int tpVolume, int ccVolume, int etVolume) {
        this.cmVolume = cmVolume;
        this.tdVolume = tdVolume;
        this.tpVolume = tpVolume;
        this.ccVolume = ccVolume;
        this.etVolume = etVolume;
    }

    /**
     * fonction permettant de donner le nombre d'heures de cours
     *
     * @return cmVolume, le nombre d'heures de CM
     */
    public int getCmVolume() {
        return cmVolume;
    }

    /**
     * fonction permettant de donner le nombre d'heures de cours
     *
     * @return tdVolume, le nombre d'heures de TD
     */
    public int getTdVolume() {
        return tdVolume;
    }

    /**
     * fonction permettant de donner le nombre d'heures de cours
     *
     * @return tpVolume, le nombre d'heures de TP
     */
    public int getTpVolume() {
        return tpVolume;
    }

    /**
     * fonction permettant de donner le nombre d'heures de controle
     *
     * @return ccVolume, le nombre d'heures de CC
     */
    public int getCcVolume() {
        return ccVolume;
    }

    /**
     * fonction permettant de donner le nombre d'heures de controle
     *
     * @return etVolume, le nombre d'heures d'ET
     */
    public int getEtVolume() {
        return etVolume;
    }


    /**
     * fonction permettant de determiner si le type d'enseignement est present dans la repartition
     *
     * @return true, si la repartition contient des heures de CM
     */
    public boolean cmPresent() {
        if (cmVolume > 0) {
            return true;
        }
        return false;

    }

    /**
     * fonction permettant de determiner si le type d'enseignement est present dans la repartition
     *
     * @return true, si la repartition contient des heures de TD
     */
    public boolean tdPresent() {
        if (tdVolume > 0) {
            return true;
        }
        return false;
    }

    /**
     * fonction permettant de determiner si le type d'enseignement est present dans la repartition
     *
     * @return true, si la repartition contient des heures de TP
     */
    public boolean tpPresent() {
        if (tpVolume > 0) {
            return true;
        }
        return false;
    }

    /**
     * fonction permettant de determiner si le type d'enseignement est present dans la repartition
     *
     * @return true, si la repartition contient des heures de CC
     */
    public boolean ccPresent() {
        if (ccVolume > 0) {
            return true;
        }
        return false;
    }

    /**
     * fonction permettant de determiner si le type d'enseignement est present dans la repartition
     *
     * @return true, si la repartition contient des heures de ET
     */
    public boolean etPresent() {
        if (etVolume > 0) {
            return true;
        }
        return false;
    }

    /**
     * fonction permettant d'obtenir la "Variete" au sein d'une repartition.
     * Si la repartition contient des heures de CM, de TP et une ET la fonction retourna 3
     *
     * @return Variete, le nombre de constituants non nul
     */
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
        if (ccVolume > 0) {
            Variete++;
        }
        if (etVolume > 0) {
            Variete++;
        }

        return Variete;
    }

}
