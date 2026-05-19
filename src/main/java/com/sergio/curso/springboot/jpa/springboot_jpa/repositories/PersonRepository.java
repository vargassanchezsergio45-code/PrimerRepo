package com.sergio.curso.springboot.jpa.springboot_jpa.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sergio.curso.springboot.jpa.springboot_jpa.dto.PersonDto;
import com.sergio.curso.springboot.jpa.springboot_jpa.entities.Person;

public interface PersonRepository extends CrudRepository<Person , Long>{
@Query("select p.name , length(p.name) form Person p where length(p.name)=(select min(length(p.name)) from Person p)")
public List<Object[]> getShorterName();

@Query("select min(p.id) , max (p.id) , sum(p.id) , avg(length(p.name)) , count(p.id) from Person p")
public Object getResumeAggregationFunction();

@Query("select count(p) from Person p")  
public Integer getMinLengthName();

@Query("select count(p) from Person p")  
public Integer getMaxLenghtName();

@Query("select count(p) from Person p")  
public List<Object[]> getPersonNameLength();

@Query("select min(p.id) from Person p")
Long minId();

@Query("select max(p.id) from Person p")
Long maxId();

@Query("select p from Person p order by p.name asc")
List<Person> getAllOrdered();

List<Person> findByIdBetweenOrderByNameDesc(Long id , Long id2);
  
List<Person> findByIdBetween(int c1 , int c2);

List<Person> findByNameBetween(int c1 , int c2);

@Query("select p from Person p where p.name between ?1 and ?2 order by p.name desc.")
List<Person> findAllBetweenName(String c1 , String c2);

@Query("select p from Person p where p.id between ?1 and ?2")
List<Person> findAllBetweenId(int c1 , int c2);

@Query("select p.id , upper(p.name) , lower(p.lastname) , upper(p.programmingLanguage) from Person p ")
List<Object[]> findAllPersonDataListCase();

@Query("select lower(concat(p.name , ' ' , p.lastname))  from Person p ")
List<String> findAllFullNameConcatLower();

@Query("select upper(concat(p.name , ' ' , p.lastname))  from Person p ")
List<String> findAllFullNameConcatUpper();

@Query("select concat(p.name , ' ' , p.lastname)  from Person p ")
List<String> findAllFullNameConcat();

@Query("select p.name || ' ' || p.lastname  from Person p ")
List<String> findAllFullNameConcat2();

 @Query("select p.name from Person p") 
List<String> findAllNames();

 @Query("select distinct(p.name) from Person p") 
List<String> findAllNamesDistinct();

 @Query("select distinct(p.programmingLanguage) from Person p") 
List<String> findAllLanguageDistinct(); 

@Query("select count(distinct(p.programmingLanguage)) from Person p") 
int countAllLanguageDistinct();

 @Query("select new com.sergio.curso.springboot.jpa.springboot_jpa.dto.PersonDto(p.name , p.lastname) from Person p")  
 List<PersonDto> findAllPersonDto();
    
  @Query("select new Person(p.name , p.lastname) from Person p")  
 List<Person> findAllPersonalizedObjectPerson();
    
@Query("select p.name from Person p where p.id=?1")
String getNameById(Long id);

@Query("select p.id from Person p where p.id=?1")
Long getIdById(Long id);

@Query("select p, p.programmingLanguage from Person p ")
List<Object[]> findAllMixPersonDataFull();

@Query("select p.id , p.name , p.lastname , p.programmingLanguage from Person p ")
List<Object[]> getPersonDataFull();

@Query("select p.id , p.name , p.lastname , p.programmingLanguage  from Person p where p.id=?1")
Object getPersonDataFullById(Long id);

@Query("select concat(p.name , ' ' , p.lastname) as fullname from Person p where p.id=?1")
String getFullNameById (Long id);

@Query("select p from Person p where p.id=?1 ")
Optional<Person> findOne(Long id);

@Query("select p from Person p where p.name=?1 ")
Optional<Person> findOneName(String name);

@Query("select p from Person p where p.name like %?1%  ")
Optional<Person> findOneLikeName(String name);

//Este de abajo es lo mismo que el de arriba
Optional<Person> findByNameContaining(String name);

List<Person> findByProgrammingLanguage(String programmingLanguage);

@Query("select p from Person p where p.programmingLanguage=?1 and p.name=?2 ")
List<Person> buscarByProgrammingLanguage(String programmingLanguage , String name );

List<Person> findByProgrammingLanguageAndName(String programmingLanguage , String name );

@Query("select p.name , p.programmingLanguage from Person p ")
List<Object[]> obtenerPersonData();

@Query("select p.name , p.programmingLanguage from Person p where p.name=?1")
List<Object[]> obtenerPersonData(String name);

@Query("select p.name , p.programmingLanguage from Person p where p.programmingLanguage=?1 and p.name=?2")
List<Object[]> obtenerPersonData(String programmingLanguage , String name);


}
