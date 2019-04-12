package com.jmsilla.crmapitest.domain.entities;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import org.junit.*;
import org.junit.rules.ExpectedException;

import com.jmsilla.crmapitest.domain.exceptions.*;
import com.jmsilla.crmapitest.domain.utils.StringTestUtils;

public class UserCreationTest {
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	@Test
	public void userShouldBeCorrectyCreated() {
		User user = new User("user", false);
		
		assertThat(user.getName(), is(equalTo("user")));
		assertThat(user.isAdmin(), is(false));
	}
	
	@Test
	public void userMustHaveAName() {
		expectedEx.expect(RequiredFieldException.class);
		expectedEx.expectMessage("name");
		
		new User(null, null);
	}
	
	@Test
	public void userCannotBeAnEmptyString() {
		expectedEx.expect(RequiredFieldException.class);
		expectedEx.expectMessage("name");
		
		new User("", null);
	}
	
	@Test
	public void userNameMustNotExceedMaxLength() {
		expectedEx.expect(LengthExceededException.class);
		expectedEx.expectMessage("name");
		
		new User(StringTestUtils.generateStringOfLength(31), null);
	}
}
