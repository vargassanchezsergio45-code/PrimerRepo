package com.sergio.curso.springboot.jpa.springboot_jpa;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.sergio.curso.springboot.jpa.springboot_jpa.dto.PersonDto;
import com.sergio.curso.springboot.jpa.springboot_jpa.entities.Person;
import com.sergio.curso.springboot.jpa.springboot_jpa.repositories.PersonRepository;

@SpringBootApplication
public class SpringbootJpaApplication implements CommandLineRunner{

	@Autowired
	private PersonRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaApplication.class, args);
	}

	

	@Override
	public void run(String... args) throws Exception {
	
		subQueries();
	}



	@Transactional(readOnly = true)
public void subQueries(){

}


	@Transactional(readOnly = true)
public void personalizedQueriesBetween(){

System.out.println("Consulta por rangos");
List<Person> persons = repository.findAllBetweenId(2, 5);
persons.forEach(p ->{
	System.out.println(p);
});

persons= repository.findAllBetweenName("J", "Q");
persons.forEach(p->{
	System.out.println(p);
});
}
	
	@Transactional(readOnly = true)
public void personalizedQueriesConcatUpperAndLowerCase(){
System.out.println("============= Consulta nombres y apellidos de personas =============");
List<String> names = repository.findAllFullNameConcat2();
names.forEach(p -> {
	System.out.println(p);
});

System.out.println("============= Consulta nombres y apellidos de personas minuscula =============");
List<String> namesMin = repository.findAllFullNameConcatLower();
namesMin.forEach(p -> {
	System.out.println(p);
});

System.out.println("============= Consulta nombres y apellidos de personas mayuscula=============");
List<String> namesMay = repository.findAllFullNameConcatUpper();
namesMay.forEach(p -> {
	System.out.println(p);
});

System.out.println("============= Consulta nombres y apellidos de personas mayuscula=============");
List<Object[]> regs = repository.findAllPersonDataListCase();
	 regs.forEach(reg ->{
	System.out.println("id= " +reg[0] + ". Nombre= " +reg[1]  + ". Apellido= "+ reg[2] + ". Lenguaje= " + reg[3]);
	 });
}





	@Transactional(readOnly = true)
	public void personalizedQueriesDistinct(){

		System.out.println("Consulta con nombres de personas");
		List<String> names = repository.findAllNames();
		names.forEach(p ->{
			System.out.println(p);
		});
		
		System.out.println("Consulta con nombres de personas unicos");
		List<String> namesDistinct = repository.findAllNamesDistinct();
		namesDistinct.forEach(p ->{
			System.out.println(p);
		});
		
		
		System.out.println("Consulta con lenguajes de progrmaacion unicos");
		List<String> programmingDistinct = repository.findAllLanguageDistinct();
		programmingDistinct.forEach(p ->{
			System.out.println(p);
		});

System.out.println("Contar  lenguajes de progrmaacion unicos");
		int numeroLenguage = repository.countAllLanguageDistinct();
	System.out.println(numeroLenguage);
		
	}

@Transactional(readOnly = true)
public void personalizedQueries2(){

	System.out.println("================= consulta por objeto persona y lenguaje de programacion ================");
	List<Object[]> personsRepo = repository.findAllMixPersonDataFull();

	personsRepo.forEach(per ->{
		System.out.println("programmingLanguage= " + per[1] + " person= " + per[0]);
	});

	System.out.println("consulta que puebla y devuelve objeto entity de una instancia personalizada");

	List<Person> persons = repository.findAllPersonalizedObjectPerson();
	persons.forEach(p ->{
		System.out.println(p);
	});

	System.out.println("consulta que puebla y devuelve objeto dto de una clase");
	List<PersonDto> personsDto = repository.findAllPersonDto();
	personsDto.forEach(p -> {
		System.out.println(p);
	});
}
	
	@Transactional(readOnly = true)
public void personalizedQueries(){
	Scanner scanner = new Scanner(System.in);
	System.out.println("================consulta solo nombre por id ===============");
System.out.println("Introduce el id para obtener su nombre");
	Long id = scanner.nextLong();
	scanner.close();

	String name = repository.getNameById(id);
	System.out.println("Nombre: " + name);

	Long idDb = repository.getIdById(id);
	System.out.println(idDb);

	String fullName = repository.getFullNameById(id);
	System.out.println(fullName);

	System.out.println("Consulta por campos personalizado por el id");
	Object[] personReg = (Object[]) repository.getPersonDataFullById(id);
	System.out.println("id= " +personReg[0] + ". Nombre= " +personReg[1]  + ". Apellido= "+ personReg[2] + ". Lenguaje= " + personReg[3]);

	List<Object[]> regs = repository.getPersonDataFull();
	 regs.forEach(reg ->{
	System.out.println("id= " +reg[0] + ". Nombre= " +reg[1]  + ". Apellido= "+ reg[2] + ". Lenguaje= " + reg[3]);
	 });
}

@Transactional
public void delete(){
	repository.findAll().forEach(p ->{
		System.out.println(p);
	});
	Scanner scanner = new Scanner(System.in);
	System.out.println("Ingrese el id a eliminar: ");
	Long id = scanner.nextLong();
	repository.deleteById(id);

	repository.findAll().forEach(p -> {
		System.out.println(p);
	});

	scanner.close();

}

@Transactional
public void delete2(){
	repository.findAll().forEach(p ->{
		System.out.println(p);
	});
	Scanner scanner = new Scanner(System.in);
	System.out.println("Ingrese el id a eliminar: ");
	Long id = scanner.nextLong();
	
	Optional<Person> optionalPerson = repository.findById(id);
//si no esta , que haga lo otro
	optionalPerson.ifPresentOrElse 
	(person ->repository.delete(person) , 
	() -> System.out.println("Lo sentimos no existe la persona con ese id"));
	repository.deleteById(id);

	repository.findAll().forEach(p -> {
		System.out.println(p);
	});

	scanner.close();

}
@Transactional
public void update(){

	Scanner scanner = new Scanner(System.in);
	System.out.println("Ingrese el id de la persona");
	Long id = scanner.nextLong();

	Optional<Person> optionalPerson = repository.findById(id);

	//optionalPerson.ifPresent(person ->{
	
	if (optionalPerson.isPresent()) {
			Person personDb = optionalPerson.orElseThrow();	

			System.out.println(personDb);
			System.out.println("Ingrese el lenguaje de programacion: ");
		String programmingLanguage = scanner.next();
		personDb.setProgrammingLanguage(programmingLanguage);
		Person personUpdated = repository.save(personDb);
		System.out.println(personUpdated);
		}else{
System.out.println("El usuario no esta presente!");
		}
		

	//});
	
	scanner.close();
}

@Transactional
public void create(){
	Scanner scanner = new Scanner(System.in);
	System.out.println("Ingrese nombre");
	String name = scanner.next();
	System.out.println("Ingrese apellido");
	String lastname= scanner.next();
	System.out.println("Ingrese lenguaje de programacion");
	String programmingLanguage = scanner.next();
	scanner.close();

	Person person = new Person(null , name , lastname , programmingLanguage);
	
	Person personNew = repository.save(person);
	System.out.println(personNew);

	repository.findById(personNew.getId()).ifPresent(p -> System.out.println(p));
}

@Transactional(readOnly = true)
public void findOne(){

	// Person person = null;
	// Optional<Person> optionalPerson = repository.findById(1L);
	// if(optionalPerson.isPresent()){
	// 	person = optionalPerson.get();
	// }
	
	// System.out.println(person);

	repository.findOneName("Pe").ifPresent(person ->System.out.println(person));
}

@Transactional(readOnly = true)
	public void list(){

		//List<Person> persons = (List<Person>) repository.findAll();
		//List<Person> persons = (List<Person>) repository.buscarByProgrammingLanguage("Java" , "Andres");
		List<Person> persons = (List<Person>) repository.findByProgrammingLanguageAndName("Java" , "Andres");

		persons.stream().forEach(person -> { 
			System.out.println(person);
		
		});

			List<Object[]> personValues = repository.obtenerPersonData("Python" , "Pepe");
			personValues.stream().forEach(person -> {
			System.out.println(person[0] + " es experto en  " + person[1]);
			});


	
	}
}


