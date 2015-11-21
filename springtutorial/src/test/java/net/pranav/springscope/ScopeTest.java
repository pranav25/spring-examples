package net.pranav.springscope;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ScopeAppConfig.class)
public class ScopeTest {
	@Autowired
	private Queue queue;

	@Autowired
	private Student student;
	private ApplicationContext ctx;

	@Before
	public void setup() {
		ctx = new AnnotationConfigApplicationContext(ScopeAppConfig.class);
		queue = ctx.getBean(Queue.class);
		queue.setId("100");
		queue.getMessage().setId(1);

		student = ctx.getBean(Student.class);
		student.setName("Pranav");
		student.getSubject().setName("BigData");
	}

	@Test
	public void Scope_Singleton_AlwaysSameReference() {
		queue.setId("200");
		queue.getMessage().setId(2);
		Queue queue2 = ctx.getBean(Queue.class);
		System.out.println(" Prior Changing QueueId::" + queue.getId()
				+ " After changing que Id:" + queue2.getId());
		Assert.assertTrue(" Hashcodes are same for singleton objects",
				queue.hashCode() == queue2.hashCode());
		Assert.assertTrue(" Queue Ids are same for singleton object",
				queue.getId() == queue2.getId());

		Assert.assertTrue(" Message Ids are same for singleton object", queue
				.getMessage().getId() == queue2.getMessage().getId());

		Assert.assertTrue(" Queue Ids are same for singleton object", queue
				.getMessage().getId() == queue2.getMessage().getId());
	}

	@Test
	public void Scope_Singleton_AlwaysDifferentReference() {
		System.out.println(" Prior Changing Student::" + student.getName()
				+ " :: Subject::" + student.getSubject().getName());

		student.setName("Mamta");
		student.getSubject().setName("Interior Designing");
		Student student2 = ctx.getBean(Student.class);
		System.out.println(" After Changing Student::" + student2.getName()
				+ " :: Previously Student Name::" + student.getName()
				+ " :: Subject::" + student2.getSubject().getName());
		Assert.assertFalse(" Hashcodes are different for prototype objects",
				student.hashCode() == student2.hashCode());
		Assert.assertTrue(" Hashcodes are same for singleton objects", student
				.getSubject().hashCode() == student2.getSubject().hashCode());

		System.out.println(" Student subject Hashcode:::"
				+ student.getSubject().hashCode()
				+ " student2 subject hashcode"
				+ student2.getSubject().hashCode());

		Assert.assertFalse(" Name is not same for prototype student objects",
				student.getName().equalsIgnoreCase(student2.getName()));

	}

}
