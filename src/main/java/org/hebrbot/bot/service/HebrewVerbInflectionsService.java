package org.hebrbot.bot.service;

import org.hebrbot.bot.model.HebrewVerb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HebrewVerbInflectionsService {

    @Autowired
    HebrewVerbService hebrewVerbService;


}
