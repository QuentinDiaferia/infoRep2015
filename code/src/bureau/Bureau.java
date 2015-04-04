package bureau;



public class Bureau {

	private int nbUtilisateurs;
	private int nbWidgets;
	public static final int nbMaxUtilisateurs = 4;
	public static final int nbMaxWidgets = 6;



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
		String result = new String("Bureau possédant:\n"+ getnbWidgets() +" widgets et dont le nombre maximum est: "+nbMaxWidgets+"\n"+getnbUtilisateurs()+ "nbUtilisateurs et dont le nombre maximum est: "+nbMaxUtilisateurs);
		return result;
	}
}
