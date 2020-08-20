package com.tomankiewicz.rafal.revolut_statement_handler;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyCollectionOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import org.apache.commons.cli.Option;
import org.junit.jupiter.api.Test;

import junit.framework.TestCase;

public class OptionsDefinitionTest extends TestCase {

	@Test
	public void testOptionsDefinitionObjectShouldAddOptionsUponObjectCreation() {
		
		OptionsDefinition options = new OptionsDefinition();

		assertNotNull(options.getCreatedOptions());
		assertThat(options.getCreatedOptions().getOptions(), is(not(emptyCollectionOf(Option.class))));
		assertThat(options.getCreatedOptions().getOption("f"), is(instanceOf(Option.class)));
		assertThat(options.getCreatedOptions().getOption("d"), is(instanceOf(Option.class)));
		assertThat(options.getCreatedOptions().getOption("s"), is(instanceOf(Option.class)));
	}
}
