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
	CategoryService categoryRepository;

	@Autowired
	ItemService itemRepository;
	
	@Autowired
	RoleService roleRepository;
	
	@Autowired
	TypeItemService typeItemRepository;
	
	@Autowired
	UserService userRepository;

	@Override
	@Transactional
	public void run(String... arg0) throws Exception {

		
		//Création des roles
		Role roleAdmin = roleRepository.findByName("ADMIN");
		Role roleEmp = roleRepository.findByName("EMP");
		Role roleUser = roleRepository.findByName("USER");
		
		if(roleAdmin==null) {
			roleAdmin = new Role("ADMIN");
			roleRepository.save(roleAdmin);
			LOGGER.info("ADMIN role created" + roleAdmin);
		}
		
		
		if(roleEmp==null) {
			roleEmp = new Role("EMP");
			roleRepository.save(roleEmp);
			LOGGER.info("EMP role created");
		}
		
		if(roleUser==null) {
			roleUser = new Role("USER");
			roleRepository.save(roleUser);
			LOGGER.info("ADMIN role created");
		}
		
		
		//Création des quelques utilisateurs
		User admin = userRepository.findByLogin("bob");
		
		User emp1 = userRepository.findByLogin("framboise");
		User emp2 = userRepository.findByLogin("PierreD");
		
		User user1 = userRepository.findByLogin("Fraise");
		User user2 = userRepository.findByLogin("Phoque");
		User user3 = userRepository.findByLogin("Mirabelle");
		
		if(admin == null) {
			admin = new User("bob", PasswordUtils.hashPassword("aaaaaaaa"), "Mickael", "BRENOIT", "brenoit.mickael@gmail.com", roleAdmin);
			userRepository.save(admin);
		}
		
		if(emp1 == null) {
			emp1 = new User("framboise", PasswordUtils.hashPassword("aaaaaaaa"), "Mickael", "BRENOIT", "brenoit.mickael@gmail.com", roleEmp);
			userRepository.save(emp1);
		}
		
		if(emp1 == null) {
			emp1 = new User("PierreD", PasswordUtils.hashPassword("aaaaaaaa"), "Pierre", "Dupont", "brenoit.mickael@gmail.com", roleEmp);
			userRepository.save(emp1);
		}
		
		if(user1 == null) {
			user1 = new User("Fraise", PasswordUtils.hashPassword("aaaaaaaa"), "Mickael", "BRENOIT", "brenoit.mickael@gmail.com", roleUser);
			userRepository.save(user1);
		}
		
		if(user2 == null) {
			emp1 = new User("Phoque", PasswordUtils.hashPassword("aaaaaaaa"), "Sylvain", "ALB", "sylalb@gmail.com", roleUser);
			userRepository.save(emp1);
		}
		
		if(user3 == null) {
			emp1 = new User("Mirabelle", PasswordUtils.hashPassword("aaaaaaaa"), "Raphael", "SABBAGH", "raphael.sabbagh@gmail.com", roleUser);
			userRepository.save(emp1);
		}
		
		
		//Création de catégories d'items
		Category information = categoryRepository.findByNameCategory("Informations");
		Category historique = categoryRepository.findByNameCategory("Historique");
		Category informatique = categoryRepository.findByNameCategory("Informatique");
		Category divertissement = categoryRepository.findByNameCategory("Divertissement");
		
		if(information == null) {
			information = new Category("Informations");
			categoryRepository.save(information);
		}
		if(historique == null) {
			historique = new Category("Historique");
			categoryRepository.save(historique);
		}
		if(informatique == null) {
			informatique = new Category("Informatique");
			categoryRepository.save(informatique);
		}
		if(divertissement == null) {
			divertissement = new Category("Divertissement");
			categoryRepository.save(divertissement);
		}
		
		
		//Création de types d'items
		TypeItem cd = typeItemRepository.findByNameItem("CD");
		TypeItem dvd = typeItemRepository.findByNameItem("DVD");
		TypeItem roman = typeItemRepository.findByNameItem("Roman");
		
		if(cd == null) {
			cd = new TypeItem("CD");
			typeItemRepository.save(cd);
		}
		if(dvd == null) {
			dvd = new TypeItem("DVD");
			typeItemRepository.save(dvd);
		}
		if(roman == null) {
			roman = new TypeItem("Roman");
			typeItemRepository.save(roman);
		}
		
		
		//Création d'items
		Item harryPotter = itemRepository.findByUniversalProductCode(100001L);
		Item seigneurDesAnneaux = itemRepository.findByUniversalProductCode(100002L);
		Item cinquantesNuancesDeGrey = itemRepository.findByUniversalProductCode(100003L);
		
		Item pnl = itemRepository.findByUniversalProductCode(100004L);
		Item jul = itemRepository.findByUniversalProductCode(100005L);
		Item hateBreed = itemRepository.findByUniversalProductCode(100006L);
		
		Item bethleem = itemRepository.findByUniversalProductCode(100007L);
		Item laMain = itemRepository.findByUniversalProductCode(100008L);
		Item leRougeEtLeNoir = itemRepository.findByUniversalProductCode(100009L);
		
		if(harryPotter == null) {
			harryPotter = new Item(100001L, "Harry Potter à l'école des sorciers", "Harry Potter a 10 ans découvre qu'il est un sorcier. Suivez ses aventures au sein de Poudlard, l'école des sorciers.", new Date(2018,01,23), 4,	0, 152, dvd, divertissement);
			itemRepository.save(harryPotter);
		}
		
		if(seigneurDesAnneaux == null) {
			seigneurDesAnneaux = new Item(100002L, "Le seigneur des anneaux, la communauté de l'anneau", "L'anneau est en possession de Frodon Saquet de la Comté. Il doit l'emmener au Mordor mais il se cherche des amis avant (il a friendzoné Sam  Gamegie car il manquait d'amis).", new Date(2018,01,23), 3, 0, 228, dvd, divertissement);
			itemRepository.save(seigneurDesAnneaux);
		}
		if(cinquantesNuancesDeGrey == null) {
			cinquantesNuancesDeGrey = new Item(100003L, "Cinquante nuances de Grey", "Anastasia Steele doit interviewer le richissime homme d'affaires Christian Grey. Elle est bientôt séduite par la personnalité de Grey, mais ce dernier va chercher à la dérouter et lui fait d'étranges propositions.", new Date(2018,01,23), 3, 0, 125, dvd, divertissement);
			itemRepository.save(cinquantesNuancesDeGrey);
		}
		
		
		if(pnl == null) {
			pnl = new Item(100004L, "Dans la légende - PNL", "Ademo et NOS parlent de leurs passé où il visser du taga", new Date(2018,01,23), 3, 0, 67, cd, divertissement);
			itemRepository.save(pnl);
		}
		
		if(jul == null) {
			jul = new Item(100005L, "My World - JUL", "Les rageux vont maigrir.", new Date(2018,01,23), 3, 0, 71, cd, divertissement);
			itemRepository.save(jul);
		}
		
		if(hateBreed == null) {
			hateBreed = new Item(100006L, "Supremacy - Hatebreed", "Supremacy est le quatrième  album du groupe de metal américain Hatebreed.", new Date(2018,01,23), 3, 0, 71, cd, divertissement);
			itemRepository.save(hateBreed);
		}
		
		if(bethleem == null) {
			bethleem = new Item(100007L, "Bethleem - Chateaubriand", "Le classique de chateaubriand", new Date(2018,01,23), 6, 252, 0, roman, divertissement);
			itemRepository.save(bethleem);
		}
		
		if(laMain == null) {
			laMain = new Item(100008L, "La Main - Guy de Maupassant", "Un roman fantastique écrit par un auteur classique de la littérature française?", new Date(2018,01,23), 6, 178, 0, roman, divertissement);
			itemRepository.save(laMain);
		}
		
		if(leRougeEtLeNoir == null) {
			bethleem = new Item(100009L, "Le rouge et le noir - Stendhal", "La base des bases.", new Date(2018,01,23), 6, 322, 0, roman, divertissement);
			itemRepository.save(bethleem);
		}
		
		
		
	}

}
