package bureau;

import java.io.*;

public class Bureau implements Serializable{

	private int nbUtilisateurs;
	private int nbWidgets;
	public static final int nbMaxUtilisateurs = 4;
	public static final int nbMaxWidgets = 6;

	public Bureau(){
		this.nbUtilisateurs = 0;
		this.nbWidgets = 0;
	}

	public int getnbUtilisateurs(){
		return this.nbUtilisateurs ;
	}

	public int getnbWidgets(){
		return this.nbWidgets ;
	}

	public int setnbUtilisateurs(int nb){
		this.nbUtilisateurs = nb ;
		return this.nbUtilisateurs ;
	}

	public int setnbWidgets(int nb){
		this.nbWidgets = nb ;
		return this.nbWidgets ;
	}

	public String toString() {
		String result = new String("Bureau possédant:\n"+ getnbWidgets() +" widgets et dont le nombre maximum est: "+nbMaxWidgets+"\n"+getnbUtilisateurs()+ " utilisateurs et dont le nombre maximum est: "+nbMaxUtilisateurs);
		return result;
	}
}
