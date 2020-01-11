package org.hebrbot.bot.service;

import org.hebrbot.bot.dao.HebrewVerbDao;
import org.hebrbot.bot.model.HebrewVerb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HebrewVerbService {
	@Autowired
	HebrewVerbDao hebrewVerbDao;

	public HebrewVerb getEntity(final Long id) {
		return hebrewVerbDao.getEntity(id);
	}
}
