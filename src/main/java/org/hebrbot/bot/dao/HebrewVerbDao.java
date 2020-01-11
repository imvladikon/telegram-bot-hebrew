package org.hebrbot.bot.dao;

import org.hebrbot.bot.model.HebrewVerb;
import org.hibernate.LockMode;

public interface HebrewVerbDao {

	HebrewVerb getEntity(final Long id);

	HebrewVerb getEntityDetached(Long id);

	HebrewVerb getEntityDetached(Long id, LockMode lock);

    HebrewVerb getHebrewVerbById(Long id);

    HebrewVerb getByName(final String name);

}
