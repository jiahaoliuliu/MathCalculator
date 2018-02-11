#!/usr/bin/env python
# Transifex script to upload the strings from the local computer to the server. Note this is dangerous since it could remove existent strings
# Import the library from third-party https://github.com/jakul/python-transifex
from transifex.api import TransifexAPI
from subprocess import Popen
from subprocess import call
from subprocess import PIPE

# TODO: Optimize this
import sys
import os

SERVER_URL = 'https://www.transifex.com'
USERNAME = 'api'
PASSWORD = ''

def query_yes_no(question, default="yes"):
    """Ask a yes/no question via raw_input() and return their answer.

    "question" is a string that is presented to the user.
    "default" is the presumed answer if the user just hits <Enter>.
        It must be "yes" (the default), "no" or None (meaning
        an answer is required of the user).

    The "answer" return value is True for "yes" or False for "no".
    """
    valid = {"yes": True, "y": True, "ye": True, "no": False, "n": False}
    if default is None:
        prompt = " [y/n] "
    elif default == "yes":
        prompt = " [Y/n] "
    elif default == "no":
        prompt = " [y/N] "
    else:
        raise ValueError("invalid default answer: '%s'" % default)

    while True:
        sys.stdout.write(question + prompt)
        choice = raw_input().lower()
        if default is not None and choice == '':
            return valid[default]
        elif choice in valid:
            return valid[choice]
        else:
            sys.stdout.write("Please respond with 'yes' or 'no' (or 'y' or 'n').\n")

# # Create the basic object 
print 'Uploading strings to the Transifex server'
transifexAPI = TransifexAPI(USERNAME, PASSWORD, SERVER_URL)
if (transifexAPI.ping()):
	print 'Connected to the server'
else:
	print 'Error connecting to the server'

PROJECT_SLUG = 'math-calculator'
RESOURCE_SLUG = 'stringsxml'
LOCAL_STRING_LOCATION = './app/src/main/res/values/strings.xml'
SOURCE_LANGUAGE = 'en'
TEMP_STRINGS = 'strings.temp.xml'

# # 1. Download the english translation
print 'Comparing the local string with the string on the Transifex server'
transifexAPI.get_translation(PROJECT_SLUG, RESOURCE_SLUG, SOURCE_LANGUAGE, TEMP_STRINGS)

# 2. Git diff
# First part to print colourful output
call(["git", "diff", "--no-index", TEMP_STRINGS, LOCAL_STRING_LOCATION])

# 3. If not string will be removed, go to step 6
#	Extra diff needed because this one does not show the colours
diff = Popen(["git", "diff", "--no-index", TEMP_STRINGS, LOCAL_STRING_LOCATION], stdout=PIPE).communicate()[0]
lines = diff.split('\n')
removals = [l for l in lines if l.startswith('- ')]
if not removals:
	# not string removed. Continue
	# Uploading the strings to the server
	print "Uploading the strings to the server"
	print transifexAPI.update_source_translation(PROJECT_SLUG, RESOURCE_SLUG, LOCAL_STRING_LOCATION)
	# Remove the temporal file
	print "Removing the temporal string file"
	os.remove(TEMP_STRINGS)
# 4. If there are any string that is going to be removed, show a warning to the user
else:
	removeStringFromServer = query_yes_no("You are about to remove some translations from the server. Are you sure about it?", "no")
	if removeStringFromServer:
# 5.1 if the user want to continue, go to next step
		print "Uploading the new strings. You are going to remove some strings from the server"
		# Uploading the strings to the server
		print transifexAPI.update_source_translation(PROJECT_SLUG, RESOURCE_SLUG, LOCAL_STRING_LOCATION)
# 5.2 If the user does not want to continue, abort
	else:
		print "Aborting the process"

# 6. delete the downloaded file and continue
	print "Removing the temporal string"
	os.remove(TEMP_STRINGS)