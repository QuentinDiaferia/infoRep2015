package bureau;

import java.util.*;
import java.io.*;

public class Bureau implements Serializable{
    private static ArrayList<Widget> listeWidgets;
	private int nbUtilisateurs;
	private int nbWidgets;
	public static final int nbMaxUtilisateurs = 4;
	public static final int nbMaxWidgets = 6;

	public Bureau(){
		this.nbUtilisateurs = 0;
		this.nbWidgets = 0;
		this.listeWidgets = new ArrayList<Widget>();
	}
	
	public Bureau(Bureau b){
		this.nbUtilisateurs = b.getNbUtilisateurs();
		this.nbWidgets = b.getNbWidgets();
		this.listeWidgets = b.getListeWidgets();
	}

    public void ajouterWidget(Widget widget){
        this.listeWidgets.add(widget);
    }

	public int getNbUtilisateurs(){
		return this.nbUtilisateurs ;
	}

	public int getNbWidgets(){
		return this.nbWidgets ;
	}
	
	public void supprimerWidget(Widget widget){
        this.listeWidgets.remove((Object)widget);
    }

	public int setNbUtilisateurs(int nb){
		this.nbUtilisateurs = nb ;
		return this.nbUtilisateurs ;
	}

	public int setNbWidgets(int nb){
		this.nbWidgets = nb ;
		return this.nbWidgets ;
	}
	
	public ArrayList<Widget> getListeWidgets(){
	    return this.listeWidgets;
	}

	public String toString() {
		String result = new String("Bureau possédant:\n"+ getNbWidgets() +" widgets et dont le nombre maximum est: "+nbMaxWidgets+"\n"+getNbUtilisateurs()+ " utilisateurs et dont le nombre maximum est: "+nbMaxUtilisateurs);
		return result;
	}
}
