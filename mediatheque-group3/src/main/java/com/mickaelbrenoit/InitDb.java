package com.mickaelbrenoit;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mickaelbrenoit.business.model.Category;
import com.mickaelbrenoit.business.model.Item;
import com.mickaelbrenoit.business.model.Role;
import com.mickaelbrenoit.business.model.TypeItem;
import com.mickaelbrenoit.business.model.User;
import com.mickaelbrenoit.business.repository.CategoryRepository;
import com.mickaelbrenoit.business.repository.ItemRepository;
import com.mickaelbrenoit.business.repository.RoleRepository;
import com.mickaelbrenoit.business.repository.TypeItemRepository;
import com.mickaelbrenoit.business.repository.UserRepository;
import com.mickaelbrenoit.business.service.CategoryService;
import com.mickaelbrenoit.business.service.ItemService;
import com.mickaelbrenoit.business.service.RoleService;
import com.mickaelbrenoit.business.service.TypeItemService;
import com.mickaelbrenoit.business.service.UserService;
import com.mickaelbrenoit.utils.PasswordUtils;

@Component
public class InitDb implements CommandLineRunner{

	private static final Logger LOGGER = LoggerFactory.getLogger(InitDb.class);

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	CategoryService categoryService;

	@Autowired
	ItemService itemService;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	TypeItemService typeItemService;
	
	@Autowired
	UserService userService;

	@Override
	@Transactional
	public void run(String... arg0) throws Exception {

		
		//Création des roles
		Role roleAdmin = roleService.findByName("ADMIN");
		Role roleEmp = roleService.findByName("EMP");
		Role roleUser = roleService.findByName("USER");
		
		if(roleAdmin==null) {
			roleAdmin = new Role("ADMIN");
			roleService.save(roleAdmin);
			LOGGER.info("ADMIN role created" + roleAdmin);
		}
		
		
		if(roleEmp==null) {
			roleEmp = new Role("EMP");
			roleService.save(roleEmp);
			LOGGER.info("EMP role created");
		}
		
		if(roleUser==null) {
			roleUser = new Role("USER");
			roleService.save(roleUser);
			LOGGER.info("ADMIN role created");
		}
		
		
		//Création des quelques utilisateurs
		User admin = userService.findByLogin("bob");
		
		User emp1 = userService.findByLogin("Framboise");
		User emp2 = userService.findByLogin("Poire");
		
		User user1 = userService.findByLogin("Fraise");
		User user2 = userService.findByLogin("Phoque");
		User user3 = userService.findByLogin("Mirabelle");
		
		if(admin == null) {
			admin = new User("Mickael", "BRENOIT", "brenoit.mickael@gmail.com", "Bob", "aaaaaaaa", roleAdmin);
			userService.save(admin);
		}
		
		if(emp1 == null) {
			emp1 = new User("Laure", "SOLEIL", "soleil.laure@gmail.com", "Framboise", "aaaaaaaa", roleEmp);
			userService.save(emp1);
		}
		
		if(emp2 == null) {
			emp2 = new User("Pierre", "DUPONT", "dupont.pierre@gmail.com", "Poire", "aaaaaaaa", roleEmp);
			userService.save(emp2);
		}
		
		if(user1 == null) {
			user1 = new User("Mathieu", "DUCHAMP", "duchamp.mathieu@gmail.com", "Fraise", "aaaaaaaa", roleUser);
			userService.save(user1);
		}
		
		if(user2 == null) {
			user2 = new User("Sylvain", "ALBASSER", "albasser.sylvain@gmail.com", "Phoque", "aaaaaaaa", roleUser);
			userService.save(user2);
		}
		
		if(user3 == null) {
			user3 = new User("Raphael", "SABBAGH", "raphael.sabbagh@gmail.com", "Mirabelle", "aaaaaaaa", roleUser);
			userService.save(user3);
		}
		
		
		//Création de catégories d'items
		Category information = categoryService.findByNameCategory("Informations");
		Category historique = categoryService.findByNameCategory("Historique");
		Category informatique = categoryService.findByNameCategory("Informatique");
		Category divertissement = categoryService.findByNameCategory("Divertissement");
		
		if(information == null) {
			information = new Category("Informations");
			categoryService.save(information);
		}
		if(historique == null) {
			historique = new Category("Historique");
			categoryService.save(historique);
		}
		if(informatique == null) {
			informatique = new Category("Informatique");
			categoryService.save(informatique);
		}
		if(divertissement == null) {
			divertissement = new Category("Divertissement");
			categoryService.save(divertissement);
		}
		
		
		//Création de types d'items
		TypeItem cd = typeItemService.findByNameItem("CD");
		TypeItem dvd = typeItemService.findByNameItem("DVD");
		TypeItem roman = typeItemService.findByNameItem("Roman");
		
		if(cd == null) {
			cd = new TypeItem("CD");
			typeItemService.save(cd);
		}
		if(dvd == null) {
			dvd = new TypeItem("DVD");
			typeItemService.save(dvd);
		}
		if(roman == null) {
			roman = new TypeItem("Roman");
			typeItemService.save(roman);
		}
		
		
		//Création d'items
		Item harryPotter = itemService.findByUniversalProductCode(100001L);
		Item seigneurDesAnneaux = itemService.findByUniversalProductCode(100002L);
		Item cinquantesNuancesDeGrey = itemService.findByUniversalProductCode(100003L);
		
		Item pnl = itemService.findByUniversalProductCode(100004L);
		Item jul = itemService.findByUniversalProductCode(100005L);
		Item hateBreed = itemService.findByUniversalProductCode(100006L);
		
		Item bethleem = itemService.findByUniversalProductCode(100007L);
		Item laMain = itemService.findByUniversalProductCode(100008L);
		Item leRougeEtLeNoir = itemService.findByUniversalProductCode(100009L);
		
		Item Cplusplus = itemService.findByUniversalProductCode(100010L);
		
		if(harryPotter == null) {
			harryPotter = new Item(100001L, "Harry Potter à l'école des sorciers", "Harry Potter a 10 ans découvre qu'il est un sorcier. Suivez ses aventures au sein de Poudlard, l'école des sorciers.", new Date(2018,01,23), 4,	0, 152, dvd, divertissement);
			itemService.save(harryPotter);
		}
		
		if(seigneurDesAnneaux == null) {
			seigneurDesAnneaux = new Item(100002L, "Le seigneur des anneaux, la communauté de l'anneau", "L'anneau est en possession de Frodon Saquet de la Comté. Il doit l'emmener au Mordor mais il se cherche des amis avant (il a friendzoné Sam  Gamegie car il manquait d'amis).", new Date(2018,01,23), 3, 0, 228, dvd, divertissement);
			itemService.save(seigneurDesAnneaux);
		}
		if(cinquantesNuancesDeGrey == null) {
			cinquantesNuancesDeGrey = new Item(100003L, "Cinquante nuances de Grey", "Anastasia Steele doit interviewer le richissime homme d'affaires Christian Grey. Elle est bientôt séduite par la personnalité de Grey, mais ce dernier va chercher à la dérouter et lui fait d'étranges propositions.", new Date(2018,01,23), 3, 0, 125, dvd, divertissement);
			itemService.save(cinquantesNuancesDeGrey);
		}
		
		
		if(pnl == null) {
			pnl = new Item(100004L, "Dans la légende - PNL", "Ademo et NOS parlent de leurs passé où il visser du taga", new Date(2018,01,23), 3, 0, 67, cd, divertissement);
			itemService.save(pnl);
		}
		
		if(jul == null) {
			jul = new Item(100005L, "My World - JUL", "Les rageux vont maigrir.", new Date(2018,01,23), 3, 0, 71, cd, divertissement);
			itemService.save(jul);
		}
		
		if(hateBreed == null) {
			hateBreed = new Item(100006L, "Supremacy - Hatebreed", "Supremacy est le quatrième  album du groupe de metal américain Hatebreed.", new Date(2018,01,23), 3, 0, 71, cd, divertissement);
			itemService.save(hateBreed);
		}
		
		if(bethleem == null) {
			bethleem = new Item(100007L, "Bethleem - Chateaubriand", "Le classique de chateaubriand", new Date(2018,01,23), 6, 252, 0, roman, divertissement);
			itemService.save(bethleem);
		}
		
		if(laMain == null) {
			laMain = new Item(100008L, "La Main - Guy de Maupassant", "Un roman fantastique écrit par un auteur classique de la littérature française?", new Date(2018,01,23), 6, 178, 0, roman, divertissement);
			itemService.save(laMain);
		}
		
		if(leRougeEtLeNoir == null) {
			leRougeEtLeNoir = new Item(100009L, "Le rouge et le noir - Stendhal", "La base des bases.", new Date(2018,01,23), 6, 322, 0, roman, divertissement);
			itemService.save(leRougeEtLeNoir);
		}
		
		if(Cplusplus == null) {
			Cplusplus = new Item(100010L, "The C++ Programming Language", "The C++ Programming Language est le premier livre d'informatique présentant le langage C++, écrit par l'inventeur du langage, Bjarne Stroustrup. La première édition est parue en 1985", new Date(1985, 01, 01), 0, 1363, 0, roman, informatique);
			itemService.save(Cplusplus);
		}
		
	}

}
