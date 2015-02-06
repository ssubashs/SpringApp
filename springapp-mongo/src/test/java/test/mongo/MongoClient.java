package test.mongo;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.app.document.ContactDoc;
import com.app.document.PersonDoc;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/springAppConfig/apiMongodata.xml" })
public class MongoClient {
	
	@Autowired
	private MongoOperations mongoRepo;
	
	//db.person.find( {role:{$in: [ 'Super Admin' ]}} )
	//db.person.distinct('role')
	
	
	@Test
	public void testReadOperation()
	{
		 List<PersonDoc> persons = mongoRepo.findAll(PersonDoc.class);
		 for(PersonDoc person:persons)
		 {			
			 System.out.println("person from mongodb ::: " + person.toString());
		 }
		 
		 
		 
	}
	
	@Test
	public void testWriteOperation()
	{
		 List<PersonDoc> persons = new ArrayList<PersonDoc>();
		 List<String> roles = new ArrayList<String>();		 
		 
		 roles.add("User");
		 roles.add("Admin");
		 PersonDoc personproto =  new PersonDoc();
	     personproto.setFirstname("charlie");
	     personproto.setLastname("harper");
	     personproto.setUsername("charper");
	     personproto.setRole(roles);
	     ContactDoc contact = new ContactDoc();
	     contact.setAddline1("De soto ave");
	     contact.setAddline2("762");
	     contact.setCity("Woodland hills");
	     contact.setEmail("charper@gmail.com");
	     contact.setHphone("3214421343");
	     contact.setState("CA");
	     contact.setZip("91367");
	     personproto.setContact(contact);
	     mongoRepo.save(contact);
	     mongoRepo.save(personproto);
	
	     personproto =  new PersonDoc();	 
	     personproto.setFirstname("Subash");
	     personproto.setLastname("Soundrapandi");
	     personproto.setUsername("ssubashs");
	     roles.add("Super Admin");
	     personproto.setRole(roles);
	     contact = new ContactDoc();
	     contact.setAddline1("21367 Victory blvd");
	     contact.setAddline2("G-302");
	     contact.setCity("Woodland hills");
	     contact.setEmail("ssubahhs@hotmail.com");
	     contact.setHphone("765823922");
	     contact.setState("CA");
	     contact.setZip("91367");
	     personproto.setContact(contact);
	     mongoRepo.save(contact);
	     mongoRepo.save(personproto);
	     
	     personproto =  new PersonDoc();
	     personproto.setFirstname("Karpaga");
	     personproto.setLastname("Rajadurai");
	     personproto.setUsername("arkalpa");
	     roles = new ArrayList<String>();
	     roles.add("Partner");
	     personproto.setRole(roles);	
	     contact = new ContactDoc();
	     contact.setAddline1("21367 Victory blvd");
	     contact.setAddline2("G-302");
	     contact.setCity("Woodland hills");
	     contact.setEmail("bubbles@gmail.com");
	     contact.setHphone("3212141114");
	     contact.setState("CA");
	     contact.setZip("91367");
	     personproto.setContact(contact);
	     mongoRepo.save(contact);
	     mongoRepo.save(personproto);
		 
	}
	
	@Test
	public void testSearch()
	{
		Query search = new Query(Criteria.where("firstname").is("Subash"));
		List<PersonDoc> persons = mongoRepo.find(search, PersonDoc.class);
		 
		 for(PersonDoc person:persons)
		 {			
			 System.out.println("Person matching firstname " + person.toString());
		 }
	}	 

}
