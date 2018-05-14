package tp;

public class User {
	protected Integer id;
	protected String nom;
	protected Double solde;
	
	/**
	 * Constructeur vide pour le schéma XML
	 */
	public User() { }
	
	/**
	 * Constructeur de la classe utilisateur
	 */
	public User(Integer id, String nom, Double solde) {
		this.id    = id;
		this.nom   = nom;
		this.solde = solde;
	}
	
	/**
	 * Permet de réaliser un transfert (positif ou négatif) d'argent sur
	 * le compte de l'utilisateur.
	 * 
	 * @param montant
	 */
	public void transfert(Double montant) {
		this.solde += montant;
	}

	public String getNom() {
		return this.nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}

	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Double getSolde() {
		return this.solde;
	}
	
	public void setSolde(Double solde) {
		this.solde = solde;
	}
}
