package outils;

public class FabriqueIdentifiant {

    private static final FabriqueIdentifiant instance = new FabriqueIdentifiant();

    int noPage;
    int noNode;

    private FabriqueIdentifiant()
    {
        noNode=0;
        noPage=1;
    }

    /**
     *
     * @return une instance de FabriqueIdentifiant
     */
    static public FabriqueIdentifiant getInstance()
    {
        return instance;
    }

    /**
     * Fournit un identifiant pour page unique
     * @return identifiant de page
     */
    public String getIdentifiantPage()
    {
        String res = "page"+noPage;
        noPage += 1;
        return res;
    }

    /**
     * Fournit un identifiant pour node unique
     * @return identifiant de node
     */
    public String getIdentifiantNode()
    {
        return null;
    }

    /**
     * Remet à zéro le numéro de node
     */
    public void resetNode()
    {
        noNode =0;
    }

    /**
     * Remet à zéro le numéro de page
     */
    public void resetPage()
    {
        noPage = 1;
    }
}
