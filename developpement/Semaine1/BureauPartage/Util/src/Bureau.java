package bureau;

import java.util.*;
import java.io.*;

public class Bureau implements Serializable{
    private static List<Widget> listeWidgets;
	private int nbUtilisateurs;
	private int nbWidgets;
	public static final int nbMaxUtilisateurs = 4;
	public static final int nbMaxWidgets = 6;

	public Bureau(){
		this.nbUtilisateurs = 0;
		this.nbWidgets = 0;
		this.listeWidgets = new ArrayList<Widget>();
	}

    public void ajouterWidget(Widget widget){
        this.listeWidgets.add(widget);
    }

	public int getnbUtilisateurs(){
		return this.nbUtilisateurs ;
	}

	public int getnbWidgets(){
		return this.nbWidgets ;
	}
	
	public void supprimerWidget(Widget widget){
        this.listeWidgets.remove((Object)widget);
    }

	public int setnbUtilisateurs(int nb){
		this.nbUtilisateurs = nb ;
		return this.nbUtilisateurs ;
	}

	public int setnbWidgets(int nb){
		this.nbWidgets = nb ;
		return this.nbWidgets ;
	}
	
	public List<Widget> getListeWidgets(){
	    return this.listeWidgets;
	}

	public String toString() {
		String result = new String("Bureau possédant:\n"+ getnbWidgets() +" widgets et dont le nombre maximum est: "+nbMaxWidgets+"\n"+getnbUtilisateurs()+ " utilisateurs et dont le nombre maximum est: "+nbMaxUtilisateurs);
		return result;
	}
}
