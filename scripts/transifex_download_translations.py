#!/usr/bin/env python
# Transifex script to upload the strings from the local computer to the server. Note this is dangerous since it could remove existent strings
# Import the library from third-party https://github.com/jakul/python-transifex
from transifex.api import TransifexAPI

SERVER_URL = 'https://www.transifex.com'
USERNAME = 'api'
PASSWORD = '1/c11bfc77d7c6d1ea3ab72108235b3ea9ba63f2ad'

# Create the basic object 
transifexAPI = TransifexAPI(USERNAME, PASSWORD, SERVER_URL)

print 'Is it connected? ' + str(transifexAPI.ping())

print 'Downloading strings from the Transifex server'

PROJECT_SLUG = 'math-calculator'
RESOURCE_SLUG = 'stringsxml'

LANGUAGES = ['en', 'zh'] # English, Arabic, French, Turkish and Urdu

# Download new strings
for language in LANGUAGES:
	print "Downloading translations for " + language
	if language == 'en':
		transifexAPI.get_translation(PROJECT_SLUG, RESOURCE_SLUG, language, './app/src/main/res/values/strings.xml')	
	else:
		transifexAPI.get_translation(PROJECT_SLUG, RESOURCE_SLUG, language, './app/src/main/res/values-' + language + '/strings.xml')