package bureau;

import java.util.*;
import java.io.*;

public class Bureau implements Serializable{
    private final List<Widget> listeWidgets;
	private final List<Integer> listeUtilisateurs;
	public static final int nbMaxUtilisateurs = 4;
	public static final int nbMaxWidgets = 6;

    // Constructeur sans argument
	public Bureau(){
		this.listeWidgets = (List<Widget>)Collections.synchronizedList(new ArrayList<Widget>());
		this.listeUtilisateurs = (List<Integer>)Collections.synchronizedList(new ArrayList<Integer>());
	}

    // Constructeur par recopie
	public Bureau(Bureau b){
		this.listeWidgets = b.getListeWidgets();
		this.listeUtilisateurs = b.getListeUtilisateurs();
	}
	
	// Méthode de copie
	public void copy(Bureau b){
		this.listeWidgets.clear();
		this.listeUtilisateurs.clear();
	    copyListeWidgets(this.listeWidgets,b.getListeWidgets());
	    copyListeUtilisateurs(this.listeUtilisateurs,b.getListeUtilisateurs());
	}

    // Méthodes de gestion des widgets
    public void ajouterWidget(Widget widget){
        this.listeWidgets.add(widget);
    }
    
    public void supprimerWidget(Widget widget){
        this.listeWidgets.remove((Object)widget);
    }
    
    public int getNbWidgets(){
		return this.listeWidgets.size() ;
	}
	
	public int getNbMaxWidgets(){
		return this.nbMaxWidgets ;
	}
	
	public List<Widget> getListeWidgets(){
	    return this.listeWidgets;
	}

    // Méthodes de gestion des utilisateurs
    public void ajouterUtilisateur(Integer ID){
        this.listeUtilisateurs.add(ID);
    }
    
    public void supprimerUtilisateur(Integer ID){
        this.listeUtilisateurs.remove((Object)ID);
    }
    
	public int getNbUtilisateurs(){
		return this.listeUtilisateurs.size() ;
	}
	
	public int getNbMaxUtilisateurs(){
		return this.nbMaxUtilisateurs ;
	}

	public List<Integer> getListeUtilisateurs(){
	    return this.listeUtilisateurs;
	}

    // Méthode toString
	public String toString() {
		String result = new String("Bureau possédant:\n"+ getNbWidgets() +" widgets et dont le nombre maximum est: "+nbMaxWidgets+"\n"+getNbUtilisateurs()+ " utilisateurs et dont le nombre maximum est: "+nbMaxUtilisateurs);
		return result;
	}
	
	// Copie de liste
	private static void copyListeWidgets(List<Widget> destination, List<Widget> source){
        destination.clear();
        for( int i = 0 ; i < source.size() ; i++ ){
            destination.add(source.get(i));
        }
    }
    
    private static void copyListeUtilisateurs(List<Integer> destination, List<Integer> source){
        destination.clear();
        for( int i = 0 ; i < source.size() ; i++ ){
            destination.add(source.get(i));
        }
    }
}
