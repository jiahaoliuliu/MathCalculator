#!/usr/bin/env python
# Transifex script to upload the strings from the local computer to the server. Note this is dangerous since it could remove existent strings
# Import the library from third-party https://github.com/jakul/python-transifex
from transifex.api import TransifexAPI
from subprocess import call

SERVER_URL = 'https://www.transifex.com'
USERNAME = 'api'
PASSWORD = '1/c11bfc77d7c6d1ea3ab72108235b3ea9ba63f2ad'

# Create the basic object 
transifexAPI = TransifexAPI(USERNAME, PASSWORD, SERVER_URL)

print 'Is it connected? ' + str(transifexAPI.ping())

print 'Uploading strings to the Transifex server'

PROJECT_SLUG = 'math-calculator'
RESOURCE_SLUG = 'stringsxml'
ORIGINAL_STRING_LOCATION = './scripts/original_resource.xml'

# Restore the source
print 'Restoring the server strings'
print transifexAPI.update_source_translation(PROJECT_SLUG, RESOURCE_SLUG, ORIGINAL_STRING_LOCATION)