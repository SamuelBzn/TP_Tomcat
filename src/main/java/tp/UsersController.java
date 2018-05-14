package tp;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;

@RestController
public class UsersController {
	@Autowired
	protected UsersService usersService;
    
	/**
	 * Permet de lister tous les utilisateurs existants.
	 * 
	 * Commande de test :
	 * 
	 *     curl -X GET http://localhost:8080/users
	 */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<User>> index() {
    	ArrayList<User> users = usersService.getUsers();
        return new ResponseEntity<ArrayList<User>>(users, HttpStatus.OK);
    }
    
    /**
     * Permet de récupérer les informations d'un seul utilisateur.
     * 
     * Commande de test :
     * 
     *     curl -X GET http://localhost:8080/users/1
     */
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> show(@PathVariable("id") int id) {
    	User user = usersService.findUser(id);
    	
    	if (user != null) {
    		return new ResponseEntity<User>(user, HttpStatus.OK);
    	} else {
    		return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    	}
    }
    
    /**
     * Permet de réaliser le débit sur un compte en accèdant à la route :
     * 
     *     /users/{id}/debit
     *     
     * Et en passant en data à la requête le paramètre `montant`. La valeur
     * passée doit être positive. Si l'utilisateur n'existe pas, une erreur
     * est renvoyée en réponse.
     * 
     * Commande de test :
     * 
     *     curl -X POST http://localhost:8080/users/1/credit --data "montant=30.0"
     */
    @RequestMapping(value = "/users/{id}/credit", method = RequestMethod.POST)
    public ResponseEntity<String> credit(
    		@PathVariable("id") int id,
    		@RequestParam(value = "montant", required = true) double montant
    ) {
    	return transfert(id, montant);
    }
    
    /**
     * Permet de réaliser le débit sur un compte en accèdant à la route :
     * 
     *     /users/{id}/debit
     *     
     * Et en passant en data à la requête le paramètre `montant`. La valeur
     * passée doit être positive. Si l'utilisateur n'existe pas, une erreur
     * est renvoyée en réponse.
     * 
     * Commande de test :
     * 
     *     curl -X POST http://localhost:8080/users/1/debit --data "montant=10.0"
     */
    @RequestMapping(value = "/users/{id}/debit", method = RequestMethod.POST)
    public ResponseEntity<String> debit(
    		@PathVariable("id") int id,
    		@RequestParam(value = "montant", required = true) double montant
    ) {
    	return transfert(id, montant > 0 ? -montant : montant);
    }
    
    /**
     * Méthode privée pour gérer le débit et le crédit.
     */
    private ResponseEntity<String> transfert(int id, double montant) {
    	User user = usersService.findUser(id);
    	
    	// Si on trouve l'utilisateur, on réalise le transfert, sinon, on renvoie
    	// un code d'erreur spécifiant que l'utilisateur n'a pas été trouvé.
    	if (user != null) {
    		user.transfert(-montant);
    		return new ResponseEntity<String>("Ok", HttpStatus.OK);
    	} else {
    		return new ResponseEntity<String>("Utilisateur introuvable", HttpStatus.NOT_FOUND);
    	}
    }
}
