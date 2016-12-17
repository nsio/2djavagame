package br.com.expurgacao.riverblaze.database;

/**
 * Created by NysioFontes on 18/01/2016.
 */
public class DataBaseAccess {

    private static final DataBaseAccess instance = new DataBaseAccess();
    private DataBaseHelper dh;

    private DataBaseAccess(){
        if(instance != null){
            throw new IllegalStateException("Control instanciado, use o método getInstance");
        }
    }

    public static DataBaseAccess getInstance() {
        return instance;
    }

    public void setDb(DataBaseHelper dh){
        if(this.dh != null){
            this.dh.close();
            this.dh = null;
        }
        this.dh=dh;
    }

}
